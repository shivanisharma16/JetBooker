<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SearchBox</title>
<link rel="stylesheet" href="CssFiles/HomePage.css">
</head>
<body>
<form class="searchbox" action="SearchFlight" method="get">
           <div class="inputBox">
            
              <p>FROM</p>
              <input type="text" name="FromCity" placeholder="Search City" required>
           </div>
           <div class="inputBox">
              <p>TO</p>
              <input type="text" name="ToCity" placeholder="Search City" required>
           </div>
           <div class="inputBox">
              <p>DEPARTURE</p>  
              <input type="date" class="date" name="DDate" placeholder="Select Date" min="<%= LocalDate.now() %>" required>
           </div>
           
           <div class="inputBox">
               <p>CLASS:</p>
            <select name="Class" id="cars">
              <option value="Economy">Economy</option>
              <option value="Bussiness">Bussiness</option>
              <option value="Premium">Premium </option>
              <option value="First-Class">FirstClass</option>
            </select>
           </div>
           	<div class="inputBox">
         		<p>TRAVELERS</p>
         		<input name ="pass" type="Number" class="pass" min="1" max="5" placeholder="Total" required>
      		</div>
           <div class="inputBox">
            <p class="white"> </p>
            <input type="submit" value="   Search   ">
         </div>
        </form>
</body>
</html>