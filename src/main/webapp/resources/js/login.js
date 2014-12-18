
(document).ready(function () {

    $("#loginPassword").keyup(function (event) {
        if (event.keyCode === 13)
            $("#loginButton").click();

    });

});

//function parse_status(xml){
//	var status = null;
//        
//	$(xml).find("status").each( function() {
//                status = $(this).text();
//			
//	});
//	if( status == "success" || status == "SUCCESS" )
//		return 0;
//	else 
//                return 1;
//}

//function login() { 
//		
//
//                $( "#loginResult" ).html("<img class='loading' src='images/loadingLogin9.gif'>");                  
//                
//                
//		$.post( "ajax/authenticateUser.jsp", { username: $("#loginUsername").val() , password: $("#loginPassword").val() }, function(xml) {
////                        alert( xml );
//			if( xml.length > 0 ){
//                                var status = parse_status(xml);
//                                
//                                if( status == 0 ) {
//                                    
//                                        var user_id = $(xml).find("id").text();
//                                        var session_id = $(xml).find("session_id").text();
//                                        $( "#loginResult" ).html( "<b>Login successful</b>" );   
//                                        window.location = "./user_profile.jsp;jsessionid="+session_id+"?user_id="+user_id;
//                                        
//                                }
//				else {
//                                        $( "#loginResult" ).html( "<b>Authentication failed</b>" );
//                                }
//			}
//			else 
//                                        $( "#loginResult" ).html( "<div id=\"loginError\">An error has occured</div>" );
//			
//		});
//}

