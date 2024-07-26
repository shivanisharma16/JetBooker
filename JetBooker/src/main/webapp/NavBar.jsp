<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.html" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ARS</title>
<link rel="icon" type="image/png" href="images/favicon.png">
<link rel="stylesheet" href="CssFiles/HomePage.css">
</head>
<body>
<div class="topnav" id="myTopnav">
<%
String Name = null;
String Access = null;
Cookie[] ck = request.getCookies();

if (ck != null) {
    for (Cookie ckName : ck) {
        if (ckName.getName().equals("Name")) {
            Name = ckName.getValue();
            // do something with the cookie value
           
        }
    }
}
if (ck != null) {
    for (Cookie ckName : ck) {
        if (ckName.getName().equals("Access")) {
            Access = ckName.getValue();
            // do something with the cookie value
           
        }
    }
}
%>
<%!
	public String link(String Name){
	if(Name == null){
		return "login.jsp";
	}
	else{
		return "Profile";
	}
}
%>
	<a href="<%= link(Name) %>" class="active"><%
	if(Name == null){
		out.print("Login");
	}
	else{
		out.print(Name);
	}
	%></a></li> 
   <a href="MyTickets">My Tickets</a></li>
   <a href="AboutUs.html">About Us</a></li>
   <a href="ContactUs">Contact Us</a></li>
   <%
   
 	if(Access != null){
 		if(Access.equals("ADMIN")){
 		   out.print("<a href=\"Schedule\">Add Flight</a></li>");
 		   out.print("<a href=\"UserList\">Users</a></li>");
 	   }
 	}
   
   %>
  
   <img src="images/Logo.png" height="50" width="50" Style="margin-left:10px; margin-top:5px;" onclick="redirectToHomepage()" >
   <script>
  function redirectToHomepage() {
    window.location.href = "Home";
  }
</script>
    
   
</div>
</body>
</html>