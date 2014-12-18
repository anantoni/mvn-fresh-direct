<%-- 
    Document   : uploadProfilePicture
    Created on : 3 Οκτ 2011, 6:01:17 μμ
    Author     : Lelouch
--%>
<%@page import="controller.AccountHandler" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Upload </title>

        <%
            AccountHandler ah = new AccountHandler();
            int session_id = -1;

            try {
                if (request.getSession(false) == null) {
                    String redirectURL = "index.jsp";
                    response.sendRedirect(redirectURL);
                } else {
                    if (session.getAttribute("username") == null || session.getAttribute("user_id") == null) {
                        String redirectURL = "index.jsp";
                        response.sendRedirect(redirectURL);
                    } else {
                        session_id = Integer.parseInt(session.getAttribute("user_id").toString());
                        if (ah.createProfileStatus(session_id) == 0) {
                            String redirectURL = response.encodeRedirectURL("create_profile.jsp?");
                            response.sendRedirect(redirectURL);
                        }
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                out.println("Exception caught: " + ex.toString());
            }
        %>        

        <%@ include file="layout/header.jsp" %>
    <div id="mainContainer" align="center">
        <div id="uploadPanel" >

            <div id="panelHeader" align="center"> 
                <h2> Upload or Link Profile Picture </h2>
                <hr>
            </div>

            <%= "<form action=\"" + response.encodeURL("uploadImageServlet") + "\" method=\"post\" enctype=\"multipart/form-data\" name=\"productForm\" id=\"productForm\">"%>
            <label class="uploadImageLabel"> Choose image to upload: </label> <input type="file" name="file" id="file"/> <br>
            <button id="uploadImageButton" style="margin-bottom: 60px;" type="submit" name="Submit" value="Submit"> Upload Image </button>
            </form>
            <%= "<form action=\"" + response.encodeURL("linkImageServlet") + "\" method=\"post\">"%>
            <label class="uploadImageLabel"> Enter external link to image: </label> <input type="text" name="image-link" autocomplete="off" id="image-link"/> <br>
            <button id="uploadImageButton" type="submit" name="Submit" value="Submit"> Link Image </button>
            </form>
            <%= "<a style=\"margin-left: 700px;\" id=\"uploadImageButton\" href=\"" + response.encodeURL("getUserProfileServlet?user_id=" + session_id) + "\"> Skip to Profile Page </a>"%>

        </div>
    </div>
    <%@ include file="layout/footer.jsp" %>
</body>
</html>
