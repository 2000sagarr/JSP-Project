package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CustomerLoginServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String email =request.getParameter("email");
		String password =request.getParameter("password");
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection
			("jdbc:mysql://localhost/Project", "root", "Sagar@2000");
			
			PreparedStatement ps=con.prepareStatement
			("select * from customerDetail where Email=? and Password=?");
					ps.setString(1, email);
					ps.setString(2, password);
					
					ResultSet rs=ps.executeQuery();
					
					if(rs.next())
					{
						
						
						HttpSession session=request.getSession();
						session.setAttribute("email", email);
						
						RequestDispatcher rd=request.getRequestDispatcher("ClientHomeServlet");
						rd.forward(request, response);
					}
					else
					{
						out.println("sorry username or password Error!!!!<br>Please Try Again");
						RequestDispatcher rd=request.getRequestDispatcher("CustomerLogin.html");
						rd.include(request, response);
					}
		}catch(Exception e2){
			System.out.println(e2);
		}
	}

}
