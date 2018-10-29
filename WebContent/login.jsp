<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="styles/main.css" type="text/css">
</head>
<body>
<h3> ${name}</h3>
<div class="adminlink"><a href="Admin.jsp"></a></div> 
<div class="container">
	<form action="User?name=login" method="post">
	<h1>Login</h1>
<label for="emailid"><b>Email Id</b></label>
 <input class="input" type="text" name="emailid" placeholder="abc@example.com" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/>
<label for="pwd"><b>Password</b></label>
<input class="input" type="password" placeholder="password" name="pwd" required/>
<button class="loginbutton" type="submit">Login</button>	
</form>
<div class="signuplink"> 
	<a href="/admin.jsp">Admin? Login here</a>
	</div>
<div class="signuplink"> 
	<a href="/index.jsp">New User? Click to SignUp</a>
	</div>
</div>
</body>
</html>