package com.alexanderdoma.peruinolvidable.controllers;

import com.alexanderdoma.peruinolvidable.model.DAOException;

import com.alexanderdoma.peruinolvidable.model.mysql.ProductDAO;
import com.alexanderdoma.peruinolvidable.model.entity.Product;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductController", urlPatterns = {"/products", "/product"})
public class ProductController extends HttpServlet {
    private final ProductDAO objProductDAO = new ProductDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/products":
                sendProductsPage(request, response);
                break;
                
            case "/product":
                sendProductPage(request, response);
                break;
        }
    }
    
    private void sendProductsPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        loadProducts(request);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
    
    private void loadProducts(HttpServletRequest request){
        try {
            request.setAttribute("products", objProductDAO.getActiveProducts());
        } catch (DAOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendProductPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Product objProduct = objProductDAO.getById(id);
            if(objProduct == null) {
                request.getRequestDispatcher("error.jsp").forward(request, response);
                return;
            }
            request.setAttribute("product", objProduct);
            request.getRequestDispatcher("product.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
