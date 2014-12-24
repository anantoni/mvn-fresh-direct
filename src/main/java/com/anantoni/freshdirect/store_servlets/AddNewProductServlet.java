/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.beans.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.anantoni.freshdirect.database_api.DatabaseManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anantoni
 */
public class AddNewProductServlet extends HttpServlet {

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
            String productName = request.getParameter("product_name");
            String productDescription = request.getParameter("product_description");
            int productCategory = Integer.parseInt(request.getParameter("product_category"));
            int productQuantity = Integer.parseInt(request.getParameter("product_quantity"));
            int productProcurementLevel = Integer.parseInt(request.getParameter("product_procurement_level"));
            int productProcurementQuantity = Integer.parseInt(request.getParameter("product_procurement_quantity"));
            int listPrice = Integer.parseInt(request.getParameter("list_price"));
            int supplierID = Integer.parseInt(request.getParameter("supplier_name"));
            
            Product product = new Product();
            product.setName(productName);
            product.setDescription(productDescription);
            product.setProductGroupID(productCategory);
            product.setAvailableQuantity(productQuantity);
            product.setProcurementQuantity(productProcurementQuantity);
            product.setProcurementLevel(productProcurementLevel);
            product.setProcurementQuantity(productProcurementQuantity);
            product.setListPrice(listPrice);
            
            
            DatabaseManager dbManager = new DatabaseManager();
            if (dbManager.insertNewProduct(product, supplierID) == false)
                out.println("error");
            String redirectURL = response.encodeRedirectURL("new_product.html");
            response.sendRedirect(redirectURL);
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
            Logger.getLogger(AddNewProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddNewProductServlet.class.getName()).log(Level.SEVERE, null, ex);
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
