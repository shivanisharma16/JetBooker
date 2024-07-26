<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset = "UTF-8">
        <meta name = "viewport"  content="width=device-width,initial-scale=1.0">
        <title>Login Form</title>
        <link rel="stylesheet" href="CssFiles/style.css">
    </head>
    <body>
        
        <div class ="container">
            <form action="loginuser" class ="form" method="post">
                <h2>Login</h2>
                <input type="email" name="email"class="box"placeholder="Enter Email">
                <input type = "password" name="password"class="box"
                placeholder="Enter Password">
                <input type = "submit" value="LOGIN" id="submit">
                <div style="margin: auto;">
                <a href="#">Forget Password?</a>
                <a href="register.jsp">Register here</a>
                </div>
            </form>
            <div class ="side">
                <img src="images/loginPage.jpg" alt="">
            </div>

        </div>
    </body>
</html>