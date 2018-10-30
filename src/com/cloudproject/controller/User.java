package com.cloudproject.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.cloudproject.dao.UserDAO;
import com.cloudproject.model.UserModel;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
@MultipartConfig
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 if(request.getParameter("name").equals("signup")) {
		    	String fname=request.getParameter("fname");
		    	String lname=request.getParameter("lname");
				String emailid=request.getParameter("emailid");
				String pwd=request.getParameter("pwd");
				if(new UserDAO().storeUserDetails(fname,lname,emailid,pwd)) {
					request.setAttribute("name", "success");
					request.getRequestDispatcher("login.jsp").forward(request, response);							
				}
				else {
					request.setAttribute("name", "user already exists!!!");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}	
		    }
		    else if(request.getParameter("name").equals("login")) {
				String emailid=request.getParameter("emailid");
				String pwd=request.getParameter("pwd");
				if(new UserDAO().validateUserDetails(emailid,pwd)) {
					String username = new UserDAO().retrieveName(emailid);
					ArrayList<UserModel> files = new UserDAO().retrieveFiles(emailid);
					if(files != null) {		
						request.setAttribute("emailid",emailid);
					request.setAttribute("files",files);
					request.setAttribute("username",username);
					request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				    }
					else
					{
						request.setAttribute("emailid",emailid);
						request.setAttribute("username",username);
						request.setAttribute("files", null);
						request.getRequestDispatcher("dashboard.jsp").forward(request, response);
					 }
				}
				else {
					request.setAttribute("name", "Login failed! Email or password is incorrect...");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}   		
		    }
		    else if(request.getParameter("name").equals("adminlogin")) {
				String emailid=request.getParameter("emailid");
				String pwd=request.getParameter("pwd");
				if((emailid.equals("admin@sjsu.com")) && pwd.equals("adminpassword")) {	
					String username = "Admin";
					ArrayList<UserModel> files = new UserDAO().retrieveAdminFiles();
					if(files != null) {		
						request.setAttribute("emailid",emailid);
					request.setAttribute("files",files);
					request.setAttribute("username",username);
					request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
				    }
					else
					{
						request.setAttribute("emailid",emailid);
						request.setAttribute("username",username);
						request.setAttribute("files", null);
						request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
					 }
				}
				else {
					request.setAttribute("name", "Login failed! Email or password is incorrect...");
					request.getRequestDispatcher("admin.jsp").forward(request, response);
				}   		
		    }
		    else if(request.getParameter("name").equals("googlelogin")) {
		  		String emailid=request.getParameter("emailid");
		  		String googleusername=request.getParameter("googleusername");
		  			ArrayList<UserModel> files = new UserDAO().retrieveFiles(emailid);
		  			if(files != null) {		
		  				request.setAttribute("emailid",emailid);
		  			request.setAttribute("files",files);
		  			request.setAttribute("username",googleusername);
		  			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		  		    }
		  			else
		  			{
		  				request.setAttribute("emailid",emailid);
		  				request.setAttribute("username",googleusername);
		  				request.setAttribute("files", null);
		  				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		  			 }  		
		      }
		    else if(request.getParameter("name").equals("upload")) {
		    	String emailid=request.getParameter("emailid");
				String description=request.getParameter("desc");
				Part filePart = request.getPart("fileName");
				InputStream inputStream = null;
				 String username = new UserDAO().retrieveName(emailid);
				ArrayList<UserModel> files1 = new UserDAO().retrieveFiles(emailid);
				if (filePart != null) {
					 String filename = getFileName(filePart);			
		           //String documentType = filePart.getContentType();
		             Long filesize = filePart.getSize() / 1024 ;
		             System.out.println(filesize);
		             inputStream = filePart.getInputStream(); 
		             if(new UserDAO().uploadObject(filename,filePart,inputStream,emailid)) {
				     if(new UserDAO().uploadFile(filename,description,filesize,emailid)) {	

					 ArrayList<UserModel> files = new UserDAO().retrieveFiles(emailid);	
					 if(files != null) {
							request.setAttribute("name", "success");
					    request.setAttribute("files",files);
					    request.setAttribute("username",username);
					    request.setAttribute("emailid",emailid);
					    request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				     }
					 else
					 {
					    request.setAttribute("username",username);
					    request.setAttribute("files", null);
						request.getRequestDispatcher("dashboard.jsp").forward(request, response);
					 }
				     }
				     else {
					 System.out.println("database fail");
					 request.setAttribute("name", "upload failed");
	    			 request.setAttribute("username",username);
					    request.setAttribute("files", files1);
						request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				     }		
		             }
		             else {
		    			System.out.println("s3 fail");
		    			request.setAttribute("name", "upload failed");
		    			 request.setAttribute("username",username);
						    request.setAttribute("files", files1);
							request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		    		 }    		
		       }   	
				else
				{
					 request.setAttribute("username",username);
					    request.setAttribute("files", files1);
						request.getRequestDispatcher("dashboard.jsp").forward(request, response);
				}
			}
		    else if(request.getParameter("name").equals("delete")) {
		    	 String emailid = request.getParameter("emailid"); 
		    	 String filename = request.getParameter("filename");
		    	 ArrayList<UserModel> files1 = new UserDAO().retrieveFiles(emailid);
		    	 String username = new UserDAO().retrieveName(emailid);
		    	 if(new UserDAO().deleteObject(filename,emailid)) {
		    	 if(new UserDAO().deleteFile(filename,emailid)) {
		 			ArrayList<UserModel> files = new UserDAO().retrieveFiles(emailid);
		 			if(files != null) {		
		 				request.setAttribute("emailid",emailid);
		 			request.setAttribute("files",files);
		 			request.setAttribute("username",username);
		 			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		 		     }
		 			else
		 			{
		 				request.setAttribute("emailid",emailid);
		 				request.setAttribute("username",username);
		 				request.setAttribute("files", null);
		 				request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		 			 }
		    	 }
		    	 else {
		    		 System.out.println("delete database fail");
		    		 request.setAttribute("files",files1);
		    		 request.setAttribute("name", "delete failed");
			 			request.setAttribute("username",username);
			 			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		    	 }
		    	 }
		    	 else {
		    		 System.out.println("delete s3 fail");
		    		 request.setAttribute("files",files1);
		    		 request.setAttribute("name", "delete failed");
			 			request.setAttribute("username",username);
			 			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		    	 }
		    }
		    else if(request.getParameter("name").equals("admindelete")) {
		    	 String emailid = request.getParameter("emailid"); 
		    	 String filename = request.getParameter("filename");
		    	 String username = request.getParameter("username");
		    	 ArrayList<UserModel> files1 = new UserDAO().retrieveAdminFiles();
		    	 if(new UserDAO().deleteObject(filename,emailid)) {
		    	 if(new UserDAO().deleteFile(filename,emailid)) {
		 			ArrayList<UserModel> files = new UserDAO().retrieveAdminFiles();
		 			if(files != null) {		
		 				request.setAttribute("emailid",emailid);
		 			request.setAttribute("files",files);
		 			request.setAttribute("username",username);
		 			request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
		 		     }
		 			else
		 			{
		 				request.setAttribute("emailid",emailid);
		 				request.setAttribute("username",username);
		 				request.setAttribute("files", null);
		 				request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
		 			 }
		    	 }
		    	 else {
		    		 System.out.println("delete database fail");
		    		 request.setAttribute("files",files1);
		    		 request.setAttribute("name", "failed to delete");
			 			request.setAttribute("username",username);
			 			request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
		    	 }
		    	 }
		    	 else {
		    		 System.out.println("delete s3 fail");
		    		 request.setAttribute("files",files1);
		    		 request.setAttribute("name", "failed to delete in s3");
			 			request.setAttribute("username",username);
			 			request.getRequestDispatcher("admindashboard.jsp").forward(request, response);
		    	 }
		    }
		    else if(request.getParameter("name").equals("logout")) {
		    	HttpSession session = request.getSession(false);
		    	if(session != null)
		    	    session.invalidate();
		    	request.getRequestDispatcher("/login.jsp").forward(request,response);
		    }

		}
			public String getFileName(final Part part) {
			    for (String content : part.getHeader("content-disposition").split(";")) {
			        if (content.trim().startsWith("filename")) {
			            return content.substring(
			                    content.indexOf('=') + 1).trim().replace("\"", "");
			        }
			    }
			    return null;
			}
			
			
		}