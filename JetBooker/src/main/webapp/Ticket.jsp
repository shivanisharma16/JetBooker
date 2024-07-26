<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.*" %>
<%@ page errorPage="ErrorPage.html" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Airline Ticket</title>
    <link rel="stylesheet" href="CssFiles/ticket.css">
</head>

<body>
<%
Connection con = null;
Statement stat = null;
ResultSet result = null;
String url = "jdbc:mysql://localhost:3306/ars";
String user = "root";
String password = "root";


  
  int age = 0;
  
  String Name = null;
  String Email = null;
  String id = request.getParameter("Id");
  String NClass = null;
	String Seat = null;
	String Date = null;
	String Time = null;
	String Flight = null;
	String From = null;
	String To = null;
	String TID = null;
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
  
	Email = CheckEmail;
	
	
  
  String QUERY = "SELECT * FROM Tickets WHERE id = '"+id+"'";
  // Create a connection to the database
  con = DriverManager.getConnection(url, user, password);
  stat = con.createStatement();
  
  stat.executeQuery(QUERY);
  result = stat.executeQuery(QUERY);
  // Do something with the connection here...
  if(result.next()){
	  
	  TID = result.getString(1);
	  Name = result.getString(2);
	  NClass = result.getString(3);
	  Seat = result.getString(4);
	  Date = result.getString(5);
	  Time = result.getString(6);
	  Flight = result.getString(7);
	  From = result.getString(8);
	  To = result.getString(9);
  }

}
catch (Exception e) {
	
	e.printStackTrace();
}
	
%>
       
    <div class="ticket">
        <div class="card card-left">
            <h1>AIR<span>TICKET</span></h1>
            <h2>Passenger Details</h2>
            <div class="title2">
            </div>
            <div class="head">
                <p> Passenger name:</p>
                <h3><% out.print(Name); %></h3>
            </div>
                <div class="head">
                    <p>Flight:</p>
                    <h3><% out.print(Flight); %></h3>

                </div>
            <div class="head">
                <p>class</p>
                <h3><% out.print(NClass); %></h3>
            </div>
            <div class="head">
                <p>seat</p>
                <h3><% out.print(Seat); %></h3>
            </div>
            <div class="head">
                <p>From:</p>
                <h3><% out.print(From); %></h3>
            </div>
            <div class="head">
                <p>Destination</p>
                <h3><% out.print(To); %></h3>
            </div>
            <div class="head">
                <p>Date:</p>
                <h3><% out.print(Date); %></h3>

            </div>
                <div class="head">
                    <p>Time:</p>
                    <h3><% out.print(Time); %></h3>
                </div>
                <div class="rotate">
                    <p>Ticket id: <% out.print(TID); %></p>
                </div>
        </div>
        <div class="card card-right">
            <h2>Bording Pass</h2>
            <br><br>
            <div class="head">
                <p> Passenger name:</p>
                <h3><% out.print(Name); %></h3>
            </div>
            <div class="head">
                <p>Flight:</p>
                <h3><% out.print(Flight); %></h3>
            </div>
            <div class="head">
                <p>class:</p>
                <h3><% out.print(NClass); %></h3>
            </div>
            <div class="head">
                <p>seat:</p>
                <h3><% out.print(Seat); %></h3>
            </div>
            <div class="head">
                <p>From:</p>
                <h3><% out.print(From); %></h3>
            </div>
            <div class="head">
                <p>Destination:</p>
                <h3><% out.print(To); %></h3>
            </div>
            <div class="head">
                <p>Date:</p>
                <h3><% out.print(Date); %></h3>
            </div>
            <div class="head">
                <p>Time:</p>
                <h3><% out.print(Time); %></h3>
            </div>
            <div class="rotate">
                <p>Ticket id: <% out.print(TID); %></p>
            </div>

        </div>
        
    </div>
        <button style="margin-left: 50%;" type="button" class="button" onclick="window.print()">Print</button>

</body>

</html>