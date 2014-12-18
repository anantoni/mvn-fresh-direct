/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.database_api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author anantoni
 */
public class LoginController extends AbstractController {
    
    public LoginController() {
    }
    
    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView model = new ModelAndView("login");
 
		return model;
    }
    
}
