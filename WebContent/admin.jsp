<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login</title>
<link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<h3> ${name}</h3>
	<div class="container">
	
	<form action="User?name=adminlogin" method="post">
	<h3>Admin Login</h3>
<label for="emailid"><b>Email Id</b></label>
 <input class="input" type="text" name="emailid" placeholder="eg:abc@example.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/>
<label for="pwd"><b>Password</b></label>
<input class="input" type="password" placeholder="password" name="pwd" required/>
<button class="loginbutton" type="submit">Login</button>	
</form>
<div class="goback"><a href="/login.jsp">Go back to user login</a></div>
</div>
</body>
</html>