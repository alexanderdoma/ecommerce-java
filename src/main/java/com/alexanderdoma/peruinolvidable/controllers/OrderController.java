package com.alexanderdoma.peruinolvidable.controllers;

import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.mysql.OrderDAO;
import com.alexanderdoma.peruinolvidable.model.mysql.OrderlineDAO;
import com.alexanderdoma.peruinolvidable.model.entity.Orderline;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "OrderController", urlPatterns = {"/orders", "/order"})
public class OrderController extends HttpServlet {

    OrderDAO orderService = new OrderDAO();
    OrderlineDAO orderlineService = new OrderlineDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch(action){
            case "/orders":
                showOrdersPage(request, response);
                break;
            
            case "/order":
                showOrderPage(request, response);
                break;
        }
    }
            
    private void showOrdersPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isLoggedIn(request) != true){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        loadOrders(request);
        request.getRequestDispatcher("orders.jsp").forward(request, response);
    }
    
    private void loadOrders(HttpServletRequest request){
        try {
            int user_id = (int) request.getSession().getAttribute("user_id");
            request.setAttribute("orders", orderService.getOrdersByUser(user_id));
        } catch (DAOException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showOrderPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isLoggedIn(request) != true){
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        int order_id = Integer.parseInt(request.getParameter("id").toString());
        
        try {
            List<Orderline> orderlines = (List<Orderline>) orderlineService.getOrderlineByOrder(order_id);
            if(orderlines == null){
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
            request.setAttribute("orderlines", orderlines);
            request.getRequestDispatcher("order.jsp").forward(request, response);
        } catch (DAOException ex) {
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
    
    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user_id") != null;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
