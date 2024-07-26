<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page errorPage="ErrorPage.html" %>
<%@ page import="java.sql.PreparedStatement" %>
<!DOCTYPE html>
<html>
<head>
    <style>
 * {
        
         font-family: 'Poppins', sans-serif;

      }
      .Profile{
        width: 100%;
        height: 100%;
      }

      .popup {
         align-items:end;
         width: 350px;
         height: 450px;
         background: #222;
         border-radius: 20px;
         
         margin-top: 100px;
         margin-left: auto;
         margin-right: auto;
    
         text-align: center;
         padding: 0 30px 30px;
         color: white;
         
        
      }
      .btn{
       height: 20px;
       width: 60px;
       background:#ff1500;
       border:0;
       color: aliceblue;
       outline:none;
       cursor:pointer;
       font-size:17px;
       font-weight:500;
       border-radius:30px;
   }
      .open-popup {
         visibility: visible;
         top: 40%;
         margin-left: 85%;
         transform: translate(-50%, -50%) scale(1);
      }

      .popup img {
         width: 100px;
         margin-top: -50px;
         border-radius: 50%;
         box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);


      }

      .popup h2 {
         font-size: 38px;
         font-weight: 500px;
         margin: 30px 0 10px;
      }

      .popup .button {
         width: 120px;
         margin-top: 10px;
         margin-left: 5px;
         margin-right: 5px;
         padding: 20px 10px 20px 10px;
         background: #ff1500;
         color: white;
         border: 0;
         outline: none;
         font-size: 18px;
         border-radius: 20px;
         cursor: pointer;
         
         box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
      }
      .button .lable{
      margin-top:-10px;
      }
      .button:hover {
  			cursor: pointer;
  			background-color: rgba(0, 162, 255, 0.507);
	  }
      p{
        font-size: large;
        margin-top: 0px;
      }
      .box{
        width: 90%;
        height: 30px;
        text-align: left;
        padding: 9px 5px 5px 5px;
        border: 1px solid #4d4d4d;
        margin-left: auto;
        margin-right: auto;
        margin-top: 5px;
       
      }
     
    </style>
</head>
<body>
<%
   Connection con = null;
	Statement stat = null;
	ResultSet result = null;
   String url = "jdbc:mysql://localhost:3306/ars";
   String user = "root";
   String password = "root";
   
   
      String FName = null;
	  String LName = null;
	  String DOB = null;
	  int age = 0;
	  String Mob = null;
	  String Gen = null;
	  String Access = null;
      String Name = null;
      String Email = null;
   try {

      // Load the JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      
      
  	//Check login
		Cookie[] cookies = request.getCookies();
		String CheckEmail = null;
      	if (cookies != null) {
              // loop through the cookies and find the one with the name "myCookie"
              for (Cookie cookie : cookies) {
                  if (cookie.getName().equals("Email")) {
                      // do something with the cookie's value
                      CheckEmail = cookie.getValue();
                  }
                  
              }
          }
      	if(CheckEmail == null) {
      		response.sendRedirect("login.jsp");
      	}
      

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
    	        if (ckName.getName().equals("Email")) {
    	            Email = ckName.getValue();
    	            // do something with the cookie value
    	           
    	        }
    	    }
    	}
      String QUERY = "SELECT * FROM USER WHERE First_Name = '"+Name+"' AND Email = '"+Email+"'";
      // Create a connection to the database
      con = DriverManager.getConnection(url, user, password);
      stat = con.createStatement();
      
      stat.executeQuery(QUERY);
      result = stat.executeQuery(QUERY);
      // Do something with the connection here...
      if(result.next()){
    	  FName = result.getString(1);
    	  LName = result.getString(2);
    	  DOB = result.getString(3);
    	  age = result.getInt(4);
    	  Mob = result.getString(5);
    	  Gen = result.getString(7);
    	  Access = result.getString(9);
      }
      else{
    	  out.println("No Result");
      }

   } catch (SQLException e) {
      out.println("SQLException: " + e.getMessage());
   } catch (ClassNotFoundException e) {
      out.println("ClassNotFoundException: " + e.getMessage());
   } finally {
      // Close the connection
      if (con != null) {
         try {
            con.close();
         } catch (SQLException e) {
            out.println("SQLException: " + e.getMessage());
         }
      }
   }
%>

<div class="Profile">
    <div class="popup" id="popup">
        <image src="images/DPM.png" width="50%">
           <h2><% out.println(FName +" "+ LName); %></h2>
           <p></p>
           <div class="box">
               <p><i class="fa-fa-envelope info"></i>Email : <% out.println(Email); %> </p>
            
           </div>
           <div class="box">
               <p><i class="fa fa-phone info"></i>Phone No. : <% out.println(Mob); %></p>

           </div>
           <div class="box">
               <p><i class="fa fa-date info"></i>DOB :<% out.println(DOB); %> </p>

           </div>
           <div class="box">
               <p><i class="fa fa-Age info"></i>Age :<% out.println(age); %> </p>

           </div>
           <div class="box">
               <p><i class="fa fa-Gender info"></i>Gender :<% out.println(Gen); %> </p>

           </div>
          
        <form action="DeletCookie">
        <button type="submit" class="button"  ><div class="lable">Log out</div></button>
        </form>
        
     </div>
     </div>
</body>
</html>