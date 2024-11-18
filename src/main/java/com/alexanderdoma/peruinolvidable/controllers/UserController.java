package com.alexanderdoma.peruinolvidable.controllers;

import com.alexanderdoma.peruinolvidable.model.DAOException;
import com.alexanderdoma.peruinolvidable.model.mysql.UserDAO;
import com.alexanderdoma.peruinolvidable.model.entity.User;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UserController", urlPatterns = {"/profile", "/login", "/register", "/session", "/logout", "/update", "/insert", "/delete"})
@MultipartConfig
public class UserController extends HttpServlet{
    
    private final UserDAO objUserDAO = new UserDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/login":
                sendLoginPage(request, response);
                break;
                
            case "/profile":
                sendProfilePage(request, response);
                break;
                
            case "/register":
                sendRegisterPage(request, response);
                break;
        }
    }
    
    private void sendLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isLoggedIn(request)){
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    
    private void sendProfilePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isLoggedIn(request) != true){
           response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
    
    private  void sendRegisterPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(isLoggedIn(request)){
            response.sendRedirect(request.getContextPath());
            return;
        }
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    
    private boolean isLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user_id") != null;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        switch (action) {
            case "/session":
                singin(request, response);
                break;
            
            case "/logout":
                logoutAccount(request, response);
                break;
                
            case "/insert":
                singup(request, response);
                break;
        }
    }
    
    private void logoutAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (isLoggedIn(request) != true) {
            sendResponse(response, "error", "No hay una sesión iniciada");
            return;
        }
        request.getSession().removeAttribute("user_id");
        sendResponse(response, "success", "Cierre de sesión exitoso");
    }
    
    private void singin(HttpServletRequest request, HttpServletResponse response) {
        if(isLoggedIn(request)){
            sendResponse(response, "error", "Hay una sesión iniciada");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            User objUser = objUserDAO.login(username, password);
            if(objUser == null){
                sendResponse(response, "error", "Credenciales inválidas");
                return;
            }
            request.getSession().setAttribute("user_id", objUser.getId());
            sendResponse(response, "success", "Bienvendio " + objUser.getName());
        } catch (DAOException ex) {
            sendResponse(response, "error", "Error al iniciar sesión", ex.getMessage());
        }
    }
    
    private void singup(HttpServletRequest request, HttpServletResponse response){
        try {
            objUserDAO.add(getObjectUser(request));
            sendResponse(response, "success", "Cuenta creada correctamente");
        } catch (DAOException ex) {
            sendResponse(response, "error", "Error al crear cuenta", ex.getMessage());
        }
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        switch (action) {
            case "/update":
                updateProfile(request, response);
        }
    }
    
    private void updateProfile(HttpServletRequest request, HttpServletResponse response){
        if(isLoggedIn(request) != true){
            sendResponse(response, "error", "No hay una sessión iniciada para actualizar");
            return;
        }
        
        try {
            objUserDAO.update(getObjectUser(request, request.getSession()));
            sendResponse(response, "success", "Información de usuario actualizada correctamente");
        } catch (DAOException ex) {
            sendResponse(response, "error", "Erro al actualizar información de usuario", ex.getMessage());
        }
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/delete":
            {
                deleteAccount(request, response);
            }
            break;
        }
    }
    
    private void deleteAccount(HttpServletRequest request, HttpServletResponse response){
        if(isLoggedIn(request) != true){
            sendResponse(response, "error", "No hay sesión iniciada");
            return;
        }
        
        try {
            objUserDAO.delete((int) request.getSession().getAttribute("user_id"));
            request.getSession().removeAttribute("user_id");
            sendResponse(response, "success", "Cuenta eliminada correctamente");
        } catch (DAOException ex) {
            sendResponse(response, "error", "Error al eliminar cuenta", ex.getMessage());
        }
    }
    
    //Return a object for a new account
    private User getObjectUser(HttpServletRequest request){
        User objUser = new User();
        objUser.setName(request.getParameter("name"));
        objUser.setLastname(request.getParameter("lastname"));
        objUser.setUsername(request.getParameter("username"));
        objUser.setPassword(request.getParameter("password"));
        objUser.setEmail(request.getParameter("email"));
        return objUser;
    }
    
    //Return a logged in object user
    private User getObjectUser(HttpServletRequest request, HttpSession session){
        User objUser = new User();
        objUser.setId((int) session.getAttribute("user_id"));
        objUser.setName(request.getParameter("name"));
        objUser.setLastname(request.getParameter("lastname"));
        objUser.setUsername(request.getParameter("username"));
        objUser.setPassword(request.getParameter("password"));
        objUser.setEmail(request.getParameter("email"));
        return objUser;
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
