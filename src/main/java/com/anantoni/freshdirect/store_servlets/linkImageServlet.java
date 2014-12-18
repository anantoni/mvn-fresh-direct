/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.database_api.AccountHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import java.sql.*;

/**
 *
 * @author Lelouch
 */
public class linkImageServlet extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession( false );
        PrintWriter out = response.getWriter();
        String error = null;
        int session_id = -1;
        
        //Elegxos an uparxei to session
        try {
            if ( session != null ) {
                if ( session.getAttribute( "username" ) != null && session.getAttribute( "user_id" ) != null ) {
                        session_id = Integer.parseInt( session.getAttribute( "user_id" ).toString() );
                }
                else {
                        RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "index.jsp" ) );
                        error = "Unauthorized Access";
                        request.setAttribute( "error", error );
                        rd.forward(request, response);
                        return;
                    
                }
            }
            else {
                        RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "index.jsp" ) );
                        error = "Unauthorized Access";
                        request.setAttribute( "error", error );
                        rd.forward(request, response);
                        return;
            }
            //Elegxos tou request parameter
            if ( request.getParameter( "image-link" ) != null ) {
                    String link = request.getParameter( "image-link" );
                    AccountHandler ah = new AccountHandler();
                    ah.updateProfilePicture(link, session_id);                                              //Update to profile picture tou xrhsth
                    
                    String redirectURL = response.encodeRedirectURL( "getUserProfileServlet?user_id=" + session_id );
                    response.sendRedirect(redirectURL);
                    return;
            }
            else {
                    RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + session_id ) );
                    error = "Bad Request";
                    request.setAttribute( "error", error );
                    rd.forward(request, response);
                    return;
                    
            }                
        } finally {            
            out.close();
        }
    }


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
            
            RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "index.jsp" ) );
            String error = ex.toString();
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
