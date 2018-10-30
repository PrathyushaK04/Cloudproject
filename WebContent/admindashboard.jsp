<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import ="java.util.ArrayList"%>
    <%@ page import ="com.cloudproject.model.UserModel" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
<link rel="stylesheet" href="styles/main.css" type="text/css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="dashboardcontainer">
 <%
 String username= (String)request.getAttribute("username");
  %>
<div class="maindashboard">
<h3 class="welcome">Welcome <%=username %></h3><br />
<div class="logout">
<form action="User?name=logout" method="post">
 <input type="submit" name="logout" value="Logout">
</form>
</div>
</div>
 <% 
 if(request.getAttribute("files") != null)
 {
 ArrayList<UserModel> a = (ArrayList<UserModel>)request.getAttribute("files"); 
 System.out.println(a); 
 %>
 <table class="dashboardtable">
					<thead class="dthead">
					   <th>User Name</th>
						<th>File Name</th>
						<th>Description</th>
						<th>File Size</th>
						<th>File Upload Time</th>
						<th>File Updated Time</th>
						<th>Download</th>
						<th>Delete</th>
					</thead>
<tbody class="dtr">
<tr>
<% 

for(UserModel usermodel : a)
 { %>
 <td><%out.println(usermodel.getEmailId()); %></td> 
	<td><%out.println(usermodel.getFileName()); %></td> 
	<td><% out.println(usermodel.getDescription());%></td>
	<td><% out.println(usermodel.getFileSize());%>KB</td>
	<td> <%out.println(usermodel.getCreatedTime());%></td>
	 <td> <%out.println(usermodel.getUpdatedTime()); %></td>
	<td><button><a href="https://s3-us-west-2.amazonaws.com/cloudstorebucket/<%=usermodel.getEmailId()%>/<%=usermodel.getFileName()%>"><span class="glyphicon glyphicon-cloud-download"></span></a></button></td>
	<form action="User?name=admindelete" method="post">
	<input type="hidden" name="emailid" value="<%=usermodel.getEmailId() %>">
		<input type="hidden" name="filename" value="<%=usermodel.getFileName() %>">
		<input type="hidden" name="username" value="<%=(String)request.getAttribute("username") %>">
<td><button ><span class="glyphicon glyphicon-trash" type="submit"></span></button></td>
	</form>										
</tbody>

 <% } %>
</table>
 <% }

 else
 {
	out.println("no files"); 
 } %>
 	</div>	
</body>
</html>