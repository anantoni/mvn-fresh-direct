/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anantoni.freshdirect.store_servlets;


import com.anantoni.freshdirect.database_api.AccountHandler;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Random;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Lelouch
 */
public class uploadImageServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                HttpSession session = request.getSession( false );
                PrintWriter out = response.getWriter();
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                String error = null;
                int session_id = -1;
                
                /****************** Elegxos an o xrhsths einai logged in ********************/
                if ( session != null ) {
                        if ( session.getAttribute( "username" ) != null && session.getAttribute( "user_id" ) != null ) 
                                session_id = Integer.parseInt( session.getAttribute( "user_id" ).toString() );
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
        
                
		
		if (!isMultipart) {
			out.println("File Not Uploaded");
		} else {
                        
                        AccountHandler ah = null;
                        try {
                            ah = new AccountHandler();
                        } catch ( Exception ex ) {
                            out.println( ex.toString() );
                        }
                        Random generator = new Random( System.currentTimeMillis() );
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			List items = null;

			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException ex) {
				out.println( ex.toString() );
			}
                        
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
                            
				FileItem item = (FileItem) itr.next();
				if ( !(item.isFormField() ) ) {
					
					try {
						String itemName = item.getName();
						int r = Math.abs(generator.nextInt());

						String reg = "[.*]";
						String replacingtext = "";
						Pattern pattern = Pattern.compile(reg);
						Matcher matcher = pattern.matcher(itemName);
						StringBuffer buffer = new StringBuffer();

						while ( matcher.find() ) {
							matcher.appendReplacement(buffer, replacingtext);
						}
						int IndexOf = itemName.indexOf("."); 
						String domainName = itemName.substring(IndexOf);

						String finalimage = buffer.toString()+"_"+r+domainName;
                                                int folder = generator.nextInt(20) + 1;
                                                
                                                /**************** Save File *********/
                                                ah.updateProfilePicture( "./user_images/Folder"+folder+"/"+finalimage, session_id );
						File savedFile = new File( "C:/Users/Johnny/Desktop/ΤΕΔ/TED Final/TED/build/web/user_images/Folder" + folder + "/" + finalimage );
                                                out.println(savedFile.getAbsolutePath()); 
						item.write(savedFile);
                                                
                                                String redirectURL = response.encodeRedirectURL( "getUserProfileServlet?user_id="+session_id );
                                                response.sendRedirect(redirectURL);
                                                return;
						
					} catch (Exception ex) {
						RequestDispatcher rd = request.getRequestDispatcher( response.encodeURL( "getUserProfileServlet?user_id=" + session_id ) );
                                                error = ex.toString();
                                                request.setAttribute( "error", error );
                                                rd.forward(request, response);
                                                return;
					}
				}
			}
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
