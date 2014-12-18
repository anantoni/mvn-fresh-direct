/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.database_api.SecurityFunctions;
import com.anantoni.freshdirect.database_api.AccountHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

/**
 *
 * @author Lelouch
 */
public class updateProfileServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        SecurityFunctions sf = new SecurityFunctions();
        HttpSession session = request.getSession( false );
        String error = null;
        
        /*********************************** Elegxos an o xrhsths einai logged in ******************************************/
        if ( session.getAttribute( "username" ) != null || session.getAttribute( "user_id" ) == null ) {
            
                try {
                        /*********************************** Elegxos an uparxoun ta aparaithta request parameters ******************************************/
                        if ( request.getParameter( "user_id" ) == null || request.getParameter("firstname") == null || request.getParameter("lastname") == null || request.getParameter("gender") == null || request.getParameter("welcome") == null || request.getParameter("day") == null || request.getParameter("month") == null || request.getParameter("year") == null || request.getParameter("gender_visibility") == null || request.getParameter("birthday_visibility") == null || request.getParameter("interests_visibility") == null || request.getParameter("wall_visibility") == null || request.getParameter( "interestsString" ) == null || request.getParameter( "oldInterestsString" ) == null  ) { 
                                RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "index.jsp" ) );
                                error = "Bad Request";
                                request.setAttribute( "error", error );
                                rd.forward(request, response);
                                return;
                        }
                        else {

                                int user_id = Integer.parseInt( request.getParameter( "user_id" ) );
                                String firstname = request.getParameter( "firstname" );
                                String lastname = request.getParameter( "lastname" );
                                
                                String birthday = request.getParameter( "year" ) + "-" + request.getParameter( "month" ) + "-" + request.getParameter( "day" );
                                String gender = request.getParameter( "gender" );
                                String welcome = sf.sanitizeUserInput( request.getParameter( "welcome" ) );
                                int birthday_visibility = Integer.parseInt( request.getParameter( "birthday_visibility" ) );
                                int interests_visibility =  Integer.parseInt( request.getParameter( "interests_visibility" ) );
                                int gender_visibility = Integer.parseInt( request.getParameter( "gender_visibility" ) );
                                int wall_visibility = Integer.parseInt( request.getParameter( "wall_visibility" ) );
                                String interestsString = sf.sanitizeUserInput( request.getParameter( "interestsString" ) );
                                String[] interestsArray = interestsString.split(",");
                                String[] oldInterestsArray = request.getParameter( "oldInterestsString" ).split(",");

                                if ( sf.checkCredentialsValidity( firstname ) == -1 ) {                      //Check validity gia to firstname
                                        RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                        error = "Invalid Firstname";
                                        request.setAttribute( "error", error );
                                        rd.forward(request, response);
                                        return;
                                }

                                if ( sf.checkCredentialsValidity( lastname ) == -1 ) {                       //Check validity gia to lastname
                                        RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                        error = "Invalid Lastname";
                                        request.setAttribute( "error", error );
                                        rd.forward(request, response);
                                        return;
                                }
                                
                                if ( sf.checkInterestsValidity( interestsString ) == -1 ) {                       //Check validity gia to lastname
                                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                    error = "Invalid Interests";
                                    request.setAttribute( "error", error );
                                    rd.forward(request, response);
                                    return;
                                }
                                
                                if ( birthday_visibility != 0 && birthday_visibility != 1 && birthday_visibility != 2 ) {
                                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                    error = "Invalid birthday visibiility";
                                    request.setAttribute( "error", error );
                                    rd.forward(request, response);
                                    return;
                                }
                                
                                if ( gender_visibility != 0 && gender_visibility != 1 && gender_visibility != 2 ) {
                                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                    error = "Invalid gender visibiility";
                                    request.setAttribute( "error", error );
                                    rd.forward(request, response);
                                    return;
                                }
                                
                                if ( interests_visibility != 0 && interests_visibility != 1 && interests_visibility != 2 ) {
                                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                    error = "Invalid interests visibiility";
                                    request.setAttribute( "error", error );
                                    rd.forward(request, response);
                                    return;
                                }
                                
                                if ( wall_visibility != 0 && wall_visibility != 1 && wall_visibility != 2 ) {
                                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                    error = "Invalid wall visibiility";
                                    request.setAttribute( "error", error );
                                    rd.forward(request, response);
                                    return;
                                }
                                
                                if ( !( gender.equalsIgnoreCase( "male" ) ) && !( gender.equalsIgnoreCase( "female" ) ) ) {
                                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + user_id ) );
                                    error = "Invalid Gender";
                                    request.setAttribute( "error", error );
                                    rd.forward(request, response);
                                    return;
                            }
                            
                                
                                AccountHandler ah = new AccountHandler();

                                try {
                                        /*********************************** Update Profile ******************************************/
                                        ah.updatePersonalInfo(firstname, lastname, birthday, gender, gender_visibility, birthday_visibility, interests_visibility, wall_visibility ,welcome, user_id );
                                        ah.updateInterests( interestsArray, oldInterestsArray, user_id );
                                        String redirectURL = response.encodeRedirectURL( "getUserProfileServlet?user_id="+user_id );
                                        response.sendRedirect(redirectURL);
                                        return;
                                        
                                } catch ( Exception ex ) {
                                        RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id="+user_id ) );
                                        error = ex.toString();
                                        request.setAttribute( "error", error );
                                        rd.forward(request, response);
                                        return;
                                }
                        }
                } finally {            
                    out.close();
                }
            }
            else {
                        String redirectURL = response.encodeRedirectURL( "index.jsp" );
                        response.sendRedirect(redirectURL);
                        return;
            }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /** 
     * Handles the HTTP <code>POST</code> method.
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
            } catch ( Exception ex ) {
                String error = null;
            
                RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "index.jsp" ) );
                error = ex.toString();
                request.setAttribute( "error", error );
                rd.forward(request, response);
                return;
            }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
