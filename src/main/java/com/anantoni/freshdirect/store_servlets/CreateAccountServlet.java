/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.database_api.SecurityFunctions;
import com.anantoni.freshdirect.database_api.DatabaseManager;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Lelouch
 */
@WebServlet(name = "createAccountServlet", urlPatterns = {"/createAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(false);
        out.println("Debugging message");

        try {
            int id;

            if (request.getParameter("username") == null || request.getParameter("email") == null 
                    || request.getParameter("password") == null) 
            {
                RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.html"));
                String error = "Bad Request";
                request.setAttribute("error", error);
                rd.forward(request, response);

            } else {
                SecurityFunctions sfunctions = new SecurityFunctions();
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String email = request.getParameter("email");
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String town = request.getParameter("town");
                String street = request.getParameter("street");
                String streetNumber = request.getParameter("streetnumber");
                String postCode = request.getParameter("postcode");

                DatabaseManager dbManager = new DatabaseManager();

//            
//                if (session.getAttribute("username") != null && session.getAttribute("user_id") != null) {
//
//                    int session_id = Integer.parseInt(session.getAttribute("user_id").toString());
//                    if (dbManager.checkUserExistence(session_id) == 1) {
//
//                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.html"));
//                        error = "You already have an account";
//                        request.setAttribute("error", error);
//                        rd.forward(request, response);
//                        return;
//                    }
//                }
//
                if (sfunctions.checkCredentialsValidity(username) == -1) {                 //Elegxos validity username
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                    String error = "Invalid Username";
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                    return;
                }
                if (sfunctions.checkCredentialsValidity(password) == -1) {                 //Elegxos validity password
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                    String error = "Invalid Password";
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                    return;
                }

                if (sfunctions.checkEmailValidity(email) == -1) {                          //Elegxos validity email
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                    String error = "Invalid Email";
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                    return;
                }

                if (dbManager.checkUsernameAvailability(username) == false) {
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                    String error = "Username taken";
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                    return;
                }

                if (dbManager.checkEmailAvailability(email) == false) {
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                    String error = "Email taken";
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                    return;
                }
                
                
                try {

                    out.println("Debugging message #2");
                    if ((id = dbManager.createAccount(username, password, email, firstname, lastname, town, street, streetNumber, postCode)) != -1) {
                        out.println("Debugging message #3");

                        session.setAttribute("username", username);
                        session.setAttribute("user_id", id);
                        String redirectURL = response.encodeRedirectURL("user_profile.html");
                        response.sendRedirect(redirectURL);
                    } 
                    else {
                        out.println("Debugging message #4");

                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                        String error = "An error has occured";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                    }

                } catch (SQLException ex) {
                    out.println("Debugging message #5");
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("register.html"));
                    String error = ex.toString();
                    out.println(error);
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                }
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
            throws ServletException, IOException 
    {

        try {
            processRequest(request, response);
        } catch (ServletException | IOException | SQLException | ClassNotFoundException ex) {
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
