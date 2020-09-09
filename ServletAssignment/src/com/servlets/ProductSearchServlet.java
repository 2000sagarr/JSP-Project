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


public class ProductSearchServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String productName =request.getParameter("product");

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection
			("jdbc:mysql://localhost/Project", "root", "Sagar@2000");
			PreparedStatement ps=con.prepareStatement
			("select * from productDetail where name=?");
			ps.setString(1, productName);
			ResultSet rs=ps.executeQuery();

			out.println("<table>");
			if(rs.next())
			{
				out.println("<tr><td>Product Id</td><td>"+rs.getInt("productId")+"</td></tr>");
				out.println("<tr><td>Name</td><td>"+rs.getString("name")+"</td></tr>");
				out.println("<tr><td>Brand</td><td>"+rs.getString("brand")+"</td></tr>");
				out.println("<tr><td>Made In</td><td>"+rs.getString("madeIn")+"</td></tr>");
				out.println("<tr><td>Price</td><td>"+rs.getString("price")+"</td></tr>");	
				out.println("<tr><td>Count</td><td>"+rs.getString("Count")+"</td></tr>");	
				
				out.println("<tr><td><br>To search other product <a href='ProductSearch.html'>click here</a></td></tr>");					


			}
			out.println("</table>");
			
	
			
		}catch(Exception e3){
			System.out.println(e3);
		}
	}

}
