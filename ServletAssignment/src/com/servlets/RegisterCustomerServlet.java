package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterCustomerServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name =request.getParameter("name");
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		String address =request.getParameter("address");
		String phoneNo =request.getParameter("phoneNo");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection
			("jdbc:mysql://localhost/Project", "root", "Sagar@2000");
			
			PreparedStatement ps=con.prepareStatement
					("insert into CustomerDetail values(?,?,?,?,?,?)");
					ps.setString(1, null);
					ps.setString(2, name);
					ps.setString(3, email);
					ps.setString(4, password);
					ps.setString(5, address);
					ps.setString(6, phoneNo);
					
					int i=ps.executeUpdate();
					if(i == 1){
						out.println("Registration Successfully Done!!!<br>");
						RequestDispatcher rd=request.getRequestDispatcher("CustomerLogin.html");
						rd.include(request, response);
					}
					else{
						out.println("Product Not Added!!!!<br>Please Try Again");
						RequestDispatcher rd=request.getRequestDispatcher("AddProduct.html");
						rd.include(request, response);
					}
		}catch(Exception e2){
			System.out.println(e2);
		}
		
		

	}

}
