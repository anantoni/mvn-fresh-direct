/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.database_api.SecurityFunctions;
import com.anantoni.freshdirect.database_api.AccountHandler;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Lelouch
 */
public class createProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        String error = null;

        /**
         * *************************** Elegxos an einai logged in o xrhsths ***********************************
         */
        if (session.getAttribute("username") != null || session.getAttribute("user_id") != null) {

            try {
                if (request.getParameter("user_id") == null || request.getParameter("firstname") == null || request.getParameter("lastname") == null || request.getParameter("gender") == null || request.getParameter("welcome") == null || request.getParameter("day") == null || request.getParameter("month") == null || request.getParameter("year") == null || request.getParameter("gender_visibility") == null || request.getParameter("birthday_visibility") == null || request.getParameter("interests_visibility") == null || request.getParameter("interests") == null || request.getParameter("wall_visibility") == null) {
                    RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("index.html"));
                    error = "Bad Request";
                    request.setAttribute("error", error);
                    rd.forward(request, response);
                } else {
                    SecurityFunctions sfunctions = new SecurityFunctions();
                    AccountHandler ah = new AccountHandler();
                    int user_id = Integer.parseInt(session.getAttribute("user_id").toString());

                    if (ah.createProfileStatus(user_id) == 1) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("getUserProfileServlet?user_id=" + user_id));
                        error = "Profile already created";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    String firstname = request.getParameter("firstname");
                    String lastname = request.getParameter("lastname");
                    String welcome = sfunctions.sanitizeUserInput(request.getParameter("welcome"));
                    String birthday_visibility = request.getParameter("birthday_visibility");
                    String interests_visibility = request.getParameter("interests_visibility");
                    String gender_visibility = request.getParameter("gender_visibility");
                    String wall_visibility = request.getParameter("wall_visibility");
                    String interests = sfunctions.sanitizeUserInput(request.getParameter("interests"));

                    if (sfunctions.checkCredentialsValidity(firstname) == -1) {                      //Check validity gia to firstname
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid Firstname";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    if (sfunctions.checkCredentialsValidity(lastname) == -1) {                       //Check validity gia to lastname
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid Lastname";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    if (sfunctions.checkInterestsValidity(interests) == -1) {                       //Check validity gia to lastname
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid Interests";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    String birthday = request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day");
                    String gender = request.getParameter("gender");

                    if (!(gender.equalsIgnoreCase("male")) && !(gender.equalsIgnoreCase("female"))) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid Gender";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    if (!birthday_visibility.equals("0") && !birthday_visibility.equals("1") && !birthday_visibility.equals("2")) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid birthday visibiility";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    if (!gender_visibility.equals("0") && !gender_visibility.equals("1") && !gender_visibility.equals("2")) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid gender visibiility";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    if (!interests_visibility.equals("0") && !interests_visibility.equals("1") && !interests_visibility.equals("2")) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid interests visibiility";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    if (!wall_visibility.equals("0") && !wall_visibility.equals("1") && !wall_visibility.equals("2")) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = "Invalid wall visibiility";
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }

                    String[] interestsArray = interests.split(",");

                    /**
                     * *********** Elegxos an uparxei 2 fores to idio interest ston pinaka ****************
                     */
                    for (int i = 0; i < interestsArray.length; i++) {
                        for (int j = 0; j < interestsArray.length; j++) {
                            if (i != j && interestsArray[i].equals(interestsArray[j])) {

                                RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                                error = "Duplicate Interest Found";
                                request.setAttribute("error", error);
                                rd.forward(request, response);
                                return;

                            }

                        }
                    }
                    try {
                        /**
                         * ************* Klhsh sunarthshs dhmiourgias neou profile tou AccountHandler ************************
                         */
                        if (ah.insertProfileData(user_id, firstname, lastname, gender, birthday, welcome, gender_visibility, birthday_visibility, interests_visibility, wall_visibility) != 0) {
                            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                            error = "Error in profile creation";
                            request.setAttribute("error", error);
                            rd.forward(request, response);
                            return;
                        }

                        /**
                         * *************** Eisagwgh twn interests sth vash *******************
                         */
                        for (int i = 0; i < interestsArray.length; i++) {
                            if (ah.insertInterest(interestsArray[i], user_id) != 0) {
                                RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                                error = "Error in interest addition";
                                request.setAttribute("error", error);
                                rd.forward(request, response);
                                return;
                            }
                        }
                        /**
                         * ******** Enhmerwsh ths vashs oti o xrhsths exei ftia3ei profile ************
                         */
                        ah.changeCreateProfileStatus(user_id);
                        String redirectURL = response.encodeRedirectURL("uploadProfilePicture.html");
                        response.sendRedirect(redirectURL);
                        return;

                    } catch (Exception ex) {
                        RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("create_profile.html?user_id=" + user_id));
                        error = ex.toString();
                        request.setAttribute("error", error);
                        rd.forward(request, response);
                        return;
                    }
                }
            } finally {
                out.close();
            }
        } else {
            String redirectURL = "./index.html";
            response.sendRedirect(redirectURL);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * /**
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
        } catch (Exception ex) {
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
