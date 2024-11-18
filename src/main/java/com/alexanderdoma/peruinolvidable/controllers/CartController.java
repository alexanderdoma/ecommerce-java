package com.alexanderdoma.peruinolvidable.controllers;

import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
import com.alexanderdoma.peruinolvidable.model.mysql.ProductDAO;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CartController", urlPatterns = {"/cart","/cart/add","/cart/remove", "/cart/update"})
@MultipartConfig
public class CartController extends HttpServlet {
    
    ProductDAO objProductDAO = new ProductDAO();

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/cart":
                showCartPage(request, response);
                break;
        }
    }
    
    private void showCartPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadCartShopping(request);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        switch (action) {
            case "/cart/add":
                addProductToCart(request, response);
                break;
        }
    }
    
    private void addProductToCart(HttpServletRequest request, HttpServletResponse response){
        loadCartShopping(request);
        HttpSession session = request.getSession();
        List<Orderline> orderlines = (List<Orderline>) session.getAttribute("orderlines");
        int id_orderline = Integer.parseInt(request.getParameter("product_id"));
        if(searchOrderlineFromOrderlines(orderlines, id_orderline)) {
            sendResponse(response, "error", "Este producto ya esta en el carrito");
            return;
        }
        Orderline orderline = new Orderline();
        orderline.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        try {
            if(objProductDAO.getById(id_orderline) == null){
                sendResponse(response, "error", "Este producto no existe");
                return;
            }
            orderlines.add(getOrderlineObject(request));
            session.setAttribute("orderlines", orderlines);
            sendResponse(response, "success", "Producto agregado al carrito");
        } catch (DAOException ex) {
            sendResponse(response, "error", "Error al agregar producto al carrito", ex.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/cart/remove":
                removeItemFromCart(request, response);
                break;
        }
    }
    
    private void removeItemFromCart(HttpServletRequest request, HttpServletResponse response){
        loadCartShopping(request);
        HttpSession session = request.getSession();
        List<Orderline> orderlines = (List<Orderline>) session.getAttribute("orderlines");
        int id = Integer.parseInt(request.getParameter("orderline_id"));
        if(searchOrderlineFromOrderlines(orderlines, id) != true){
            sendResponse(response, "error", "El producto no esta en el carrito");
            return;
        }
        session.setAttribute("orderlines", removeOrderlineFromOrderlines(orderlines, id));
        sendResponse(response, "success", "Producto removido del carrito");
    }
    
    private List<Orderline> removeOrderlineFromOrderlines(List<Orderline> orderlines, int id){
        Iterator<Orderline> iterator =  orderlines.iterator();
        while (iterator.hasNext()){
            Orderline orderline = iterator.next();
            if(orderline.getProduct().getId() == id){
                iterator.remove();
            }
        }
        return orderlines;
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        switch (action) {
            case "/cart/update":
                updateItemFromCart(request, response);
                break;
        }
    }
    
    private void updateItemFromCart(HttpServletRequest request, HttpServletResponse response){
        loadCartShopping(request);
        HttpSession session = request.getSession();
        List<Orderline> orderlines = (List<Orderline>) session.getAttribute("orderlines");
        int id = Integer.parseInt(request.getParameter("orderline_id"));
        if(searchOrderlineFromOrderlines(orderlines, id) != true){
            sendResponse(response, "error", "El producto no esta en el carrito");
            return;
        }
        try {
            session.setAttribute("orderlines", updateOrderlineFromOrderlines(request, orderlines, id));
            sendResponse(response, "success", "Pedido actualizado correctamente");
        } catch (DAOException ex) {
            sendResponse(response, "error", "Error al actualizar pedido", ex.getMessage());
        }
    }
    
    private List<Orderline> updateOrderlineFromOrderlines(HttpServletRequest request, List<Orderline> orderlines, int id) throws DAOException {
            for(Orderline orderline : orderlines){
                if(orderline.getProduct().getId() == id){
                    orderline.setQuantity(Integer.parseInt(request.getParameter("quantity")));
                }
            }
        return orderlines;
    }
    
    private boolean searchOrderlineFromOrderlines(List<Orderline> orderlines, int id){
        Iterator<Orderline> iterator =  orderlines.iterator();
        while (iterator.hasNext()){
            Orderline orderline = iterator.next();
            if(orderline.getProduct().getId() == id){
                return true;
            }
        }
        return false;
    }
    
    private Orderline getOrderlineObject(HttpServletRequest request) throws DAOException {
        Orderline orderline = new Orderline();
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        orderline.setQuantity(quantity);
        orderline.setProduct(objProductDAO.getById(product_id));
        orderline.setTotal(quantity * orderline.getProduct().getPrice());
        return orderline;
    }
    
    private void loadCartShopping(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("orderlines") == null) {
            List<Orderline> orderlines = new ArrayList<>();
            session.setAttribute("orderlines", orderlines); 
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
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
