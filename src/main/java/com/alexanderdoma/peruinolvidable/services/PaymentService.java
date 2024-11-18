package com.alexanderdoma.peruinolvidable.services;

import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
import com.alexanderdoma.peruinolvidable.model.entity.User;
import com.alexanderdoma.peruinolvidable.utilies.PaypalKeysManager;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;



public class PaymentService {
    private static final String CLIENT_ID = PaypalKeysManager.getProperty("CLIENT.ID");
    private static final String CLIENT_SECRET = PaypalKeysManager.getProperty("CLIENT.SECRET");;
    private static final String MODE = PaypalKeysManager.getProperty("MODE");;
    
    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }
    
    public Payment executePayment(String paymentId, String payerId)
            throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);

        Payment payment = new Payment().setId(paymentId);

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);

        return payment.execute(apiContext, paymentExecution);
    }
    
    public String authorizatePayment(List<Orderline> orderlines, User objUser)
            throws PayPalRESTException {
        Payment requestPayment = new Payment();
        RedirectUrls redirectUrls = getRedirectURLs();
        requestPayment.setTransactions(getTransactionInformation(orderlines));
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(getPayer(objUser));
        requestPayment.setIntent("authorize");
        
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext); 
        return getApprovalLink(approvedPayment);
    }
    
    private List<Transaction> getTransactionInformation(List<Orderline> orderlines){
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        double total = 0;
        
        for (Orderline orderline : orderlines){
            Item item = new Item();
            item.setCurrency("USD");
            item.setName(orderline.getProduct().getName());
            item.setPrice(orderline.getProduct().getPrice().toString());
            item.setQuantity(orderline.getQuantity() + "");
            items.add(item);
            total += orderline.getQuantity() * orderline.getProduct().getPrice();
        }
        Details details = new Details();
        details.setSubtotal(total + "");
        details.setTax("0");
        details.setShipping("0");
        
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(total + "");
        amount.setDetails(details);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        
        transaction.setDescription("Peru Inolvidable");
        itemList.setItems(items);
        transaction.setItemList(itemList);
        
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);
        return listTransaction;
    }
    
    private RedirectUrls getRedirectURLs(){
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/peruinolvidable/");
        redirectUrls.setReturnUrl("http://localhost:8080/peruinolvidable/review_payment");
        return redirectUrls;
    }

    private Payer getPayer(User objUser){
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName(objUser.getName())
                 .setLastName(objUser.getLastname())
                 .setEmail(objUser.getEmail());
        
        payer.setPayerInfo(payerInfo);
        return payer;
    }
    
    private String getApprovalLink(Payment approvedPayment){
        List<Links> links = approvedPayment.getLinks();
        String approvaLink = null;
        
        for (Links link : links){
            if(link.getRel().equalsIgnoreCase("approval_url")){
                approvaLink = link.getHref();
                break;
            }
        }
        return approvaLink;
    }
}
