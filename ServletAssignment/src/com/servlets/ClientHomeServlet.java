package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ClientHomeServlet extends HttpServlet {
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		HttpSession session =request.getSession(false);
		String email=(String) session.getAttribute("email");
		String customerName;
		
		
		//To get name of customer from data
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection
			("jdbc:mysql://localhost/Project", "root", "Sagar@2000");
			PreparedStatement ps=con.prepareStatement
			("select * from customerDetail where Email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();

			if(rs.next())
			{
				customerName=rs.getString("name");
				out.println("Welcome "+customerName);					
			}
			
			out.println("<br>To search product<a href='ProductSearch.html'> Click here</a>");
			
			

			
			
		}catch(Exception e3){
			System.out.println(e3);
		}
		
		
		
		
		
	}

}
