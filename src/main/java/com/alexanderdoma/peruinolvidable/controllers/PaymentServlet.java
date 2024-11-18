    package com.alexanderdoma.peruinolvidable.controllers;

import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.entity.Order;
import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
import com.alexanderdoma.peruinolvidable.model.entity.User;
import com.alexanderdoma.peruinolvidable.model.mysql.OrderDAO;
import com.alexanderdoma.peruinolvidable.model.mysql.UserDAO;
import com.alexanderdoma.peruinolvidable.services.PaymentService;
import com.google.gson.JsonObject;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.PayPalRESTException;
import com.alexanderdoma.peruinolvidable.utilies.ResendKeysManager;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PaymentServlet", urlPatterns = {"/checkout", "/authorize_payment", "/execute_payment", "/review_payment"})
public class PaymentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/checkout":
                sendCheckoutPage(request, response);
                break;
            case "/review_payment":
                sendReviewPaymentPage(request, response);
                break;
        }
    }
    
    private void sendCheckoutPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        if(isLoggedIn(request) != true){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        if(doesHaveOrders(request) != true){
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
    
    private void sendReviewPaymentPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        PaymentService paymentServices = new PaymentService();
        Payment payment;
        try {
            payment = paymentServices.getPaymentDetails(paymentId);
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            request.setAttribute("shippingAddress", shippingAddress);
            String url = "review.jsp?paymentId=" + paymentId + "&PayerID=" + payerId;
            request.getRequestDispatcher(url).forward(request, response);
        } catch (PayPalRESTException ex) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }        
    }
    
    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user_id") != null;
    }
    
    private boolean doesHaveOrders(HttpServletRequest request){
        if(request.getSession().getAttribute("orderlines") == null){
            return false;
        }
        List<Orderline> orderlines = (List<Orderline>) request.getSession().getAttribute("orderlines");
        return !orderlines.isEmpty();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        HttpSession session = request.getSession();
        switch (action){
            case "/authorize_payment":
                authorizePayment(request, response);
                break;
            
            case "/execute_payment":
                executePayment(request, response);
                break;
        }
    }
    
    private void authorizePayment(HttpServletRequest request, HttpServletResponse response){
        List<Orderline> orderlines = getOrderlines(request);
        if(orderlines == null || getOrderlines(request).size() <= 0){
            sendResponse(response, "error", "No hay pedidos en el carrito");
            return;
        }
        PaymentService paymentServices = new PaymentService();
        try {
            if(getUserObject(request) == null){
                sendResponse(response, "error", "Debe iniciar sesión");
                return;
            }
            String approvalLink = paymentServices.authorizatePayment(getOrderlines(request), getUserObject(request));
            sendResponse(response, "success", "Pedido generado correctamente", approvalLink);
        } catch (PayPalRESTException | DAOException ex) {
            sendResponse(response,"error", "Error al procesar pago", ex.getMessage());
        }
    }
    
    private void executePayment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String paymentId = request.getParameter("paymentId");
            String payerId = request.getParameter("PayerID");
            PaymentService paymentServices = new PaymentService();
            Payment payment = paymentServices.executePayment(paymentId, payerId);
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);
            createOrder(request);
            sendEmailNotification("Pedido generado correctamente");
            request.getRequestDispatcher("receipt.jsp").forward(request, response);
        } catch (DAOException | PayPalRESTException ex) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    private void createOrder(HttpServletRequest request) throws DAOException{
        OrderDAO objOrderDAO = new OrderDAO();
        HttpSession session = request.getSession();
        List<Orderline> orderlines = (List<Orderline>) session.getAttribute("orderlines");
        int user_id = (int) session.getAttribute("user_id");
        Order objOrder = new Order();
        objOrder.setUser(new UserDAO().getById(user_id));
        objOrder.setSubtotal((double) session.getAttribute("total"));
        objOrder.setTotal((double) session.getAttribute("total"));
        objOrder.setDate(Date.valueOf(LocalDate.now()));
        objOrder.setStatus("PENDING");
        objOrder.setPayment_id(request.getParameter("paymentId"));
        objOrderDAO.add(objOrder, orderlines);
    }
    
    private User getUserObject(HttpServletRequest request) throws DAOException {
        Object user_id = request.getSession().getAttribute("user_id");
        if(user_id == null){
            return null;
        }
        return new UserDAO().getById(Integer.parseInt(user_id.toString()));
    }
    
    //Send a json object as response
    private void sendResponse(HttpServletResponse response, String type, String tittle) {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("type", type);
            json.addProperty("tittle", tittle);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Send a json object as response
    private void sendResponse(HttpServletResponse response, String type, String tittle, String message) {
        try {
            JsonObject json = new JsonObject();
            json.addProperty("type", type);
            json.addProperty("tittle", tittle);
            json.addProperty("message", message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
        } catch (IOException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Orderline> getOrderlines(HttpServletRequest request){
        List<Orderline> orderlines = (List<Orderline>) request.getSession().getAttribute("orderlines");
        if (orderlines == null) {
            return null;
        }
        return orderlines;
    }
    
    private void sendEmailNotification(String message){
        Resend resend = new Resend(ResendKeysManager.getProperty("CLIENT.ID"));

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Perú Inolvidable <onboarding@resend.dev>")
                .to("alexanderdoma.personal@gmail.com")
                .subject("Hubo una modificación en tu pedido")
                .html("<strong>" + message + "</strong>")
                .build();

         try {
             CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
