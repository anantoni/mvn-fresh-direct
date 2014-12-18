/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.beans.UserProfile;
import com.anantoni.freshdirect.database_api.AccountHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.sql.*;

/**
 *
 * @author Lelouch
 */
public class getUserProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        int session_id = -1;
        int target_id = -1;
        int friendship_exists = -1;
        UserProfile profile = null;
        AccountHandler ah = new AccountHandler();
        String error = null;

        try {
           
            session = request.getSession(false);
            if (session != null) {
                if (session.getAttribute("username") == null || session.getAttribute("user_id") == null) {
                    String redirectURL = "index.jsp";
                    response.sendRedirect(redirectURL);
                    return;
                } else {
                    session_id = Integer.parseInt(session.getAttribute("user_id").toString());
                }
            } else {
                String redirectURL = "index.jsp";
                response.sendRedirect(redirectURL);
                return;
            }

//            if (request.getParameter("user_id") == null) {
//                String redirectURL = response.encodeRedirectURL("index.jsp");
//                response.sendRedirect(redirectURL);
//                return;
//            } else {
//                target_id = Integer.parseInt(request.getParameter("user_id"));
//            }
//
//            if (ah.checkUserExistence(target_id) == 0) {
//                String redirectURL = response.encodeRedirectURL("getUserProfileServlet?user_id=" + session_id);
//                response.sendRedirect(redirectURL);
//                return;
//            }
//
//            if (session_id == target_id) {
//                if (ah.createProfileStatus(session_id) == 0) {
//                    String redirectURL = response.encodeRedirectURL("create_profile.jsp?user_id=" + session_id);
//                    response.sendRedirect(redirectURL);
//                    return;
//                }
//            } else {
//                if (ah.createProfileStatus(target_id) == 0) {
//                    String redirectURL = response.encodeRedirectURL("getUserProfileServlet?user_id=" + session_id);
//                    response.sendRedirect(redirectURL);
//                    return;
//                }
//            }

            if (request.getAttribute("error") != null) {
                error = request.getAttribute("error").toString();
            }

            profile = ah.getProfileData(target_id);
            UserProfile userProfile = new UserProfile();

            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("user_profile.jsp?user_id=" + target_id));
            if (error != null) {
                request.setAttribute("error", error);
            }
            request.setAttribute("profileView", userProfile);
            rd.forward(request, response);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //PrintWriter out = response.getWriter();

        try {
            processRequest(request, response);
        } catch (ServletException ex) {
            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.jsp"));
            String error = ex.toString();
            request.setAttribute("error", error);
            rd.forward(request, response);

        } catch (IOException ex) {
            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.jsp"));
            String error = ex.toString();
            request.setAttribute("error", error);
            rd.forward(request, response);
        } catch (ClassNotFoundException ex) {            
            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.jsp"));
            String error = ex.toString();
            request.setAttribute("error", error);
            rd.forward(request, response);
        } catch (SQLException ex) {
            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.jsp"));
            String error = ex.toString();
            request.setAttribute("error", error);
            rd.forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @return 
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
