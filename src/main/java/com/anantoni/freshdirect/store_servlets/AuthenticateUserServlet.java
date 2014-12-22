package com.anantoni.freshdirect.store_servlets;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.anantoni.freshdirect.database_api.SecurityFunctions;
import com.anantoni.freshdirect.beans.UserProfile;
import com.anantoni.freshdirect. beans.Cart;
import com.anantoni.freshdirect.database_api.DatabaseManager;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Lelouch
 */
public class AuthenticateUserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("niaou");
        try {
            SecurityFunctions sfunctions = new SecurityFunctions();

            if (request.getParameter("username") == null || request.getParameter("password") == null) {
                String error = "Bad Request";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.html"));
                rd.forward(request, response);
            } 

            /**
             * Valid login request
             */
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (sfunctions.checkCredentialsValidity(username) == -1 || sfunctions.checkCredentialsValidity(password) == -1) {
                String error = "Authentication Failed";
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("login.html"));
                rd.forward(request, response);
            }

            try {
                DatabaseManager dbManager = new DatabaseManager();
                /**
                 * On successful login redirect
                 */
                UserProfile userProfile;
                if ((userProfile = dbManager.login(username, password)) != null) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("shoppingCart", new Cart());
                    
                    userProfile.setSupplierList(dbManager.getSuppliers());
                    userProfile.setSalesHistory(dbManager.getSalesHistory(userProfile.getUserID()));
                    session.setAttribute("userProfile", userProfile);
                    String redirectURL = response.encodeRedirectURL("user_profile.html");
                    response.sendRedirect(redirectURL);

                } else {
                    String error = "Authentication Failed";
                    request.setAttribute("error", error);
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("login.html"));
                    rd.forward(request, response);
                }

            } catch (SQLException ex) {
                String error = ex.toString();
                request.setAttribute("error", error);
                RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.html"));
                rd.forward(request, response);
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ServletException | IOException | ClassNotFoundException | SQLException ex) {
            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.html"));
            String error = ex.toString();
            request.setAttribute("error", error);
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
