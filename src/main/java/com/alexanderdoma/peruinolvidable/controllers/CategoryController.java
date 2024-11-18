package com.alexanderdoma.peruinolvidable.controllers;

import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.mysql.ProductDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CategoryController", urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/category":
                showProductsByCategory(request, response);
                break;
                
            default:
                throw new AssertionError();
        }
    }

    private void showProductsByCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int category_id = Integer.parseInt(request.getParameter("category_id"));
        try {
            request.setAttribute("products", new ProductDAO().getActiveProductsByCategory(category_id));
            request.getRequestDispatcher("products.jsp").forward(request, response);
        } catch (DAOException ex) {
            Logger.getLogger(CategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
