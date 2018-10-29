package com.cloudproject.dao;

import java.io.File;
//import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

//import org.apache.commons.io.FileUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
//import com.amazonaws.services.s3.model.S3Object;
import com.cloudproject.connection.GetConnection;
import com.cloudproject.model.UserModel;

public class UserDAO {
	GetConnection gc= new GetConnection();
	UserModel u = null;
	String bucketname = "cloudstorebucket";
	FileOutputStream fos = null;
	final String SUFFIX = "/";	
	final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
	public boolean storeUserDetails(String fname,String lname,String emailid, String pwd)
	{
		System.out.println("hi");
		String sql="insert into User(firstname,lastname,emailid,password) values (?,?,?,?)";
		System.out.println("hi");
		GetConnection gc= new GetConnection();
		try {
			System.out.println("hi");
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			System.out.println("hi2");
			gc.ps.setString(1,fname);
			gc.ps.setString(2, lname);
			gc.ps.setString(3, emailid);
			gc.ps.setString(4, pwd);
			int i= gc.ps.executeUpdate();
			if(i !=0 ) {
				System.out.println("hi");
			return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}	
	// create a client connection based on credentials	

	public boolean validateUserDetails(String emailid, String pwd)
	{
		String sql="select * from User where emailid=? and password=?";
		GetConnection gc= new GetConnection();
		try {
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			gc.ps.setString(1, emailid);
			gc.ps.setString(2, pwd);
			gc.rs= gc.ps.executeQuery();			
			return gc.rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	public String retrieveName(String emailid) {
		String sql = "select firstname, lastname from User where emailid=?";
		String username = null;
		try {
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			gc.ps.setString(1, emailid);
			gc.rs= gc.ps.executeQuery();
			while(gc.rs.next())
			{
            username = gc.rs.getString("firstname") + " "+ gc.rs.getString("lastname");
            return username;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return username;
		
	}
	
	public ArrayList<UserModel> retrieveFiles(String emailid)
	{
		ArrayList<UserModel> a = new ArrayList<UserModel>();
		String sql="select filename, description, fileSize, createdtime, updatedtime,emailid from Files where emailid=?	";		
		try {
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			gc.ps.setString(1, emailid);
			gc.rs= gc.ps.executeQuery();
			int count = 0;
			
			while(gc.rs.next())
			{		
				u = new UserModel();
				u.setFileName(gc.rs.getString("filename"));
				u.setDescription(gc.rs.getString("description"));
				u.setFileSize(gc.rs.getString("fileSize"));
				u.setCreatedTime(gc.rs.getTimestamp("createdtime"));
				u.setUpdatedTime(gc.rs.getTimestamp("updatedtime"));	
				u.setEmailId(gc.rs.getString("emailid"));
				a.add(u);				
				count = count+1;
			}
			if (count != 0)
			{
				return a;
			}
			else
			{
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	public ArrayList<UserModel> retrieveAdminFiles()
	{
		ArrayList<UserModel> a = new ArrayList<UserModel>();
		String sql="select filename, description, fileSize, createdtime, updatedtime, emailid from Files";		
		try {
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			gc.rs= gc.ps.executeQuery();
			int count = 0;
			
			while(gc.rs.next())
			{		
				u = new UserModel();
				u.setFileName(gc.rs.getString("filename"));
				u.setDescription(gc.rs.getString("description"));
				u.setFileSize(gc.rs.getString("fileSize"));
				u.setCreatedTime(gc.rs.getTimestamp("createdtime"));
				u.setUpdatedTime(gc.rs.getTimestamp("updatedtime"));	
				u.setEmailId(gc.rs.getString("emailid"));
				a.add(u);				
				count = count+1;
			}
			if (count != 0)
			{
				return a;
			}
			else
			{
				return null;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return null;
	}
	public boolean uploadFile(String filename,String description,Long filesize,String emailid)
	{
		try {
		String sql="update Files SET description=?,fileSize=? where filename=? && emailid=?";		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		GetConnection gc= new GetConnection();
		System.out.println(timestamp);
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			gc.ps.setString(1,description);
			gc.ps.setLong(2, filesize);
			gc.ps.setString(3,filename);
			gc.ps.setString(4, emailid);
			int i= gc.ps.executeUpdate();
			if(i == 0 ) {
				String sql1="insert into Files(filename,description,fileSize,createdtime,emailid) values (?,?,?,?,?)";
				GetConnection gc1= new GetConnection();
				try {
			 gc1.ps1= GetConnection.getMySqlConnection().prepareStatement(sql1);
			 gc1.ps1.setString(1,filename);
			 gc1.ps1.setString(2, description);
			 gc1.ps1.setLong(3, filesize);
			 gc1.ps1.setTimestamp(4, timestamp);
			 gc1.ps1.setString(5, emailid);
			 int i1= gc1.ps1.executeUpdate();
			 if(i1 !=0 ) {																																																																																																																																																																																			
			 return true;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
			else {
				 return true;																																																																																																																																																																																																																																																														
			}
		}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
		
	}
	public boolean uploadObject(String fileName, Part part, InputStream inputStream, String emailid) {	
	//	final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
		// create meta-data for your folder and set content-length to 0
		try {
			System.out.println(emailid);
			ObjectMetadata metadata = new ObjectMetadata();
	
			System.out.println("b");
		metadata.setContentLength(part.getSize());
		metadata.setContentLength(Long.valueOf(part.getInputStream().available()));
		//String ext = FilenameUtils.getExtension(part.getName());
		//String keyName = fileName + '.' + ext;
		String keyName = emailid + SUFFIX + fileName;		
		// create empty content
		// create a PutObjectRequest passing the folder name suffixed by /	
		// send request to S3 to create folder	
		//request.setMetadata(metadata);
		System.out.println("b1");
		s3Client.putObject(new PutObjectRequest(bucketname , keyName, part.getInputStream(),metadata));
		System.out.println("b2");
		return true;
		}
		
	catch(AmazonServiceException e) {
           // The call was transmitted successfully, but Amazon S3 couldn't process 
          //  it, so it returned an error response.
           e.printStackTrace();
      }
	catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public File downloadObject(String filename,String emailid) {
		
		try {
			String keyName = emailid + SUFFIX + filename;
		    S3Object object = s3Client.getObject(bucketname, keyName);
		    File file = new File(filename);
		    FileUtils.copyInputStreamToFile(object.getObjectContent(), file);
		    return file;
		
		}  
		catch(AmazonServiceException e) {
	           // The call was transmitted successfully, but Amazon S3 couldn't process 
	          //  it, so it returned an error response.
	           e.printStackTrace();
	      }catch (IOException e) {
		    System.err.println(e.getMessage());
		    System.exit(1);
		}
		return null;
	}
	public boolean deleteObject(String filename,String emailid) {
		//final AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();

		try {
			String keyName = emailid + SUFFIX + filename;
			s3Client.deleteObject(new DeleteObjectRequest(bucketname, keyName));
			return true;
		}
		catch(AmazonServiceException e) {
	           // The call was transmitted successfully, but Amazon S3 couldn't process 
	          //  it, so it returned an error response.
	           e.printStackTrace();
	      }
		
		return false;
		
	}
	public boolean deleteFile(String filename,String emailid) {
		String sql = "delete from Files where filename=? && emailid=?";
		System.out.println("delete");
		GetConnection gc= new GetConnection();
		try {
			gc.ps= GetConnection.getMySqlConnection().prepareStatement(sql);
			System.out.println("delete");
			gc.ps.setString(1, filename);
			gc.ps.setString(2, emailid);
						
			int i= gc.ps.executeUpdate();
			if(i !=0 ) {
			return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}
	
	
}
