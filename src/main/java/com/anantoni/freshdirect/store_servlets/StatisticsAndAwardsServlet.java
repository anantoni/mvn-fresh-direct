/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.beans.Product;
import com.anantoni.freshdirect.beans.Supplier;
import com.anantoni.freshdirect.beans.UserProfile;
import com.anantoni.freshdirect.database_api.DatabaseManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author anantoni
 */
public class StatisticsAndAwardsServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            String mostPopularProductsLimit = request.getParameter("popularProductsLimit");
            String mostPopularSuppliersLimit = request.getParameter("popularSuppliersLimit");
            String mostPopularPostCodesLimit = request.getParameter("popularPostCodesLimit");
            String topClientsLimit = request.getParameter("topClientsLimit");
            
            DatabaseManager dbManager = new DatabaseManager();
            if (mostPopularProductsLimit != null && !mostPopularProductsLimit.equals("")) {
                int limit = Integer.parseInt(mostPopularProductsLimit);
                List<Product> productList = dbManager.mostPopularProducts(limit);
                request.setAttribute("productList", productList);
            }
            else if (mostPopularSuppliersLimit != null && !mostPopularSuppliersLimit.equals("")) {
                int limit = Integer.parseInt(mostPopularSuppliersLimit);
                List<Supplier> supplierList = dbManager.mostPopularSuppliers(limit);
                request.setAttribute("supplierList", supplierList);
            }
            else if (mostPopularPostCodesLimit != null && !mostPopularPostCodesLimit.equals("")) {
                int limit = Integer.parseInt(mostPopularPostCodesLimit);
                List<Integer> postCodeList = dbManager.mostPopularPostCodes(limit);
                request.setAttribute("postCodeList", postCodeList);
            }
            else if (topClientsLimit != null && !topClientsLimit.equals("")) {
                int limit = Integer.parseInt(topClientsLimit);
                List<UserProfile> clientList = dbManager.topClients(limit);
                request.setAttribute("clientList", clientList);
            }
            RequestDispatcher rd = request.getRequestDispatcher(response.encodeURL("statistics_and_awards_results.html"));
            rd.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticsAndAwardsServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatisticsAndAwardsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
