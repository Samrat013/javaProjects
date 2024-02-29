package com.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@MultipartConfig
public class register extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		 String name = request.getParameter("user_name");
		 String password = request.getParameter("user_password");
		 String email = request.getParameter("user_email");
		 
		 out.println(name);
		 out.println(password);
		 out.println(email);
		 
		 try{
			//load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//create a connection

			String url = "jdbc:mysql://localhost:3306/register1";
			String username = "root";
			String Password = "Samrat";

			Connection con = DriverManager.getConnection(url,username,Password);

			//create a query

			String q = "insert into user(name,password,email) values(?,?,?)";

			//get the preparedStatement object 

			PreparedStatement pstm = con.prepareStatement(q);
			
			pstm.setString(0, name);
			pstm.setString(1, password);
			pstm.setString(2, email);
			
			pstm.executeUpdate();

			out.println("<h2>Inserted...<h2>");
			

//			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("<h1>Error...<h1>");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("user_name");
		String password = request.getParameter("user_password");
		String email = request.getParameter("user_email");
		Part part = request.getPart("image");
		String fileName = part.getSubmittedFileName();
		
//		out.println(fileName);
//		out.println(name);
//		out.println(password);
//		out.println(email);
		
		try{
			Thread.sleep(3000);
			//load the driver
			Class.forName("com.mysql.cj.jdbc.Driver");

			//create a connection

			String url = "jdbc:mysql://localhost:3306/register1";
			String username = "root";
			String Password = "Samrat";

			Connection con = DriverManager.getConnection(url,username,Password);

			//create a query

			String q = "insert into user(name,password,email,imageName) values(?,?,?,?)";

			//get the preparedStatement object 

			PreparedStatement pstm = con.prepareStatement(q);
			
			pstm.setString(1, name);
			pstm.setString(2, password);
			pstm.setString(3, email);
			pstm.setString(4, fileName);
			
			pstm.executeUpdate();
			
			//upload file...
			InputStream is = part.getInputStream();
			byte[] data = new byte[is.available()];
		
			is.read(data);
			@SuppressWarnings("deprecation")
			String path = request.getRealPath("/")+"image"+File.separator+fileName;
			out.println(path);
			
			FileOutputStream fos = new FileOutputStream(path);
			
			fos.write(data);
			fos.close();
			
			out.println("Inserted");
			
//			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("Error");
		}
	}

}
