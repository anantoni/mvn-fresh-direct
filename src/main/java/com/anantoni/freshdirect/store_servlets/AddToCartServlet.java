/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;

import com.anantoni.freshdirect.beans.OrderedProduct;
import com.anantoni.freshdirect.beans.Cart;
import com.anantoni.freshdirect.beans.Product;
import com.anantoni.freshdirect.database_api.DatabaseManager;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author anantoni
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        
        String name = request.getParameter("product_name");
        int productID = Integer.parseInt(request.getParameter("product_id"));
        int listPrice = Integer.parseInt(request.getParameter("product_list_price"));
        int quantity = Integer.parseInt(request.getParameter("product_quantity"));
        
        OrderedProduct product = new OrderedProduct();
        product.setName(name);
        product.setProductID(productID);
        product.setPrice(listPrice);
        product.setQuantity(quantity);
        
        HttpSession session = request.getSession(true);                                                     
        if (session.getAttribute("shoppingCart") == null) {
            Cart cart = new Cart();
            cart.addProduct(product);
            session.setAttribute("shoppingCart", name);
        }
        else {
            Cart cart = (Cart)session.getAttribute("shoppingCart");
            cart.addProduct(product);
            session.setAttribute("shoppingCart", cart);
        }
        
        DatabaseManager dbManager = new DatabaseManager();
        List<Product> suggestionList = dbManager.suggestProducts(productID);
        session.setAttribute("suggestionList", suggestionList);
//        RequestDispatcher rd = request.getRequestDispatcher(response.encodeRedirectURL(request.getHeader("Referer")));
//        rd.forward(request, response);
        
        String redirectURL = response.encodeRedirectURL(request.getHeader("Referer"));
        response.sendRedirect(redirectURL);
        
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
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AddToCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
