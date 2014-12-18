<%-- 
    Document   : logout.jsp
    Created on : 4 Αυγ 2011, 9:18:54 μμ
    Author     : anantoni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%

    if (request.getSession(false) == null) {
        String redirectURL = "./index.jsp";
        response.sendRedirect(redirectURL);
    } else {
        if (session.getAttribute("userProfile") == null) {
            String redirectURL = "./index.jsp";
            response.sendRedirect(redirectURL);
        } else {
            session.invalidate();
            String redirectURL = "./index.jsp";
            response.sendRedirect(redirectURL);
        }
    }

%>