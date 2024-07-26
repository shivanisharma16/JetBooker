<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="ErrorPage.html" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
   <link rel="stylesheet" href="HomePage.css">
   
    
</head>
<body>
<%
		String Flight = request.getParameter("Flight");
		String Date = request.getParameter("Date");
		String pass = request.getParameter("pass");
		String Class = request.getParameter("Class");
		int n = Integer.parseInt(pass);
		int[] npass = new int[n];
%>
    <div class="box">
        <form class="passbox" action="Tickets" method="post"> 
           <h2>Enter Passenger Details</h2>
           <input type="hidden" name="Flight" value="<%=Flight%>">
           <input type="hidden" name="Date" value="<%=Date%>">
           <input type="hidden" name="pass" value="<%=pass%>">
           <input type="hidden" name="Class" value="<%=Class%>">
           <% for(int i = 0;i<n;i++) {
           out.println("<div class=\"inputBox\">");
           out.println("<input class=\"element\" type=\"text\" name=\"travler"+i+"\" placeholder=\""+(i+1)+". Passenger name\" required>");
           out.println("<select name=\"agegroup"+i+"\">");
           out.println("<option>Adult</option>");
           out.println("<option>Child</option>");
           out.println("</select>");
           out.println("</div>");
           }%>
            
            <button type="submit">Next</button>
        </form>
    </div>
</body>
</html>