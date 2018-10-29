<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign Up!!!</title>
<link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<h3> ${name}</h3>
<div class="container">
<form action="User?name=signup" method="post">	

<h1>Sign Up</h1>
    <p>Please fill in this form to create an account.</p>
     <label for="fname"><b>First Name</b></label>
	 <input class="input" type="text" name="fname" placeholder="firstname" required/><br/>
	 <label for="lname"><b>Last Name</b></label>
	 <input class="input" type="text" name="lname" placeholder="lastname" required/><br />
	 <label for="emailid"><b>Email Id</b></label>
	 <input class="input" type="text" name="emailid" id="signupemailid" placeholder="abc@example.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/><br />
	 <label for="pwd"><b>Password</b></label>
	 <input class="input" type="password" name="pwd" placeholder="password" required/>	<br />
	<button class="signupbutton" type="submit">SignUp</button>	
	
	
</form>
<div class="logins">
<p>Already a User?</p>
	<div class="btnlogin" type="submit"><a href="/login.jsp">Login</a></div>
<div class="or-wrapper">
<div class="or-line"></div>
<div class="or">or</div>
</div>
	<div class="btngooglelogin" type="submit"><a href="/OpenAuth.jsp">Login with google</a></div>
</div>
</div>
	
		
</body>
</html>