<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compactible" content="IE-edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CONTACT US</title>
    <link rel="stylesheet" href="CssFiles/register.css">

   
</head>

<body>
   
    <div class="hero">
        <div class="container">
            <form action="registeruser" method="post">
                <h2>Create Account</h2>
                <div class="row">
                    <div class="input-group">
                        <input type="text" name="fname" id="name"  required>
                        <label class="input" for="name"><i class="fa-solid fa-user"></i> First Name:</label>
                    </div>
                    <div class="input-group">
                        <input type="text" name="lname" id="number" required>
                        <label class="input" for="number"> Last Name:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-group">
                        <input type="text" name="dob" id="name" onfocus="(this.type='date')" onblur="(this.type='text')" required>
                        <label class="input" for="name"><i class="fa-solid fa-calendar"></i> Dob:</label>
                    </div>
                    <div class="input-group">
                        <input type="number" name="age" id="number" required>
                        <label class="input" for="number"><i class="fa-solid fa-user"></i> Age:</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-group">
                        <input type="email" name="email" id="email" required>
                        <label class="input" for="name"><i class="fa-solid fa-envelope"></i> Email:</label>
                    </div>
                    <div class="input-group">
                        <input type="number" name="phone" id="number" required>
                        <label class="input" for="number"><i class="fa-solid fa-phone"></i> PHONE NO.</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-group">
                        <input type="password" name="password" id="password" required>
                        <label class="input" for="password"><i class="fa-solid fa-lock"></i> Password</label>
                    </div>
                    <div class="input-group">
                        <input type="password"  name="co-password" id="password" required>
                        <label class="input" for="password"><i class="fa-solid fa-key"></i> Confirm Password</label>
                    </div>
                </div>
                <br>
                <div class="gender" id="9">
                    <label>Gender:</label>
                    <input type="radio" name="gender" value="male" id="male" required>
                    <span id="male">  Male</span>
                    <input type="radio" name="gender" value="female" id="female">
                    <span id="female">  Female</span>
                </div>
                <div class="center">
                    <input type="submit" value="Create" id="submit">
                </div>
                <div class="center" style="font-size: 15px;">
                    <p>Already have an Account?  - <a href="login.jsp">Login here</a></p>
                </div>
            </form>

        </div>
    </div>
</body>

</html>