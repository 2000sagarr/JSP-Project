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


public class AddProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String productId =request.getParameter("productId");
		String productName =request.getParameter("productName");
		String brand =request.getParameter("brand");
		String madeIn =request.getParameter("madeIn");
		String price =request.getParameter("price");
		String count =request.getParameter("count");
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection
			("jdbc:mysql://localhost/Project", "root", "Sagar@2000");
			
			PreparedStatement ps=con.prepareStatement
			("insert into productDetail values(?,?,?,?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, productId);
			ps.setString(3,productName);
			ps.setString(4, brand);
			ps.setString(5, madeIn);
			ps.setString(6, price);
			ps.setString(7, count);
			
			int i=ps.executeUpdate();
			if(i == 1){
				out.println("Product Added Successfully!!!<br>");
				out.println("Add <a href='AddProduct.html'>product</a><br>");
				out.println("Go to <a href='CustomerLogin.html'>Login Page</a>");
			}
			else{
				out.println("Product Not Added!!!!<br>Please Try Again");
				RequestDispatcher rd=request.getRequestDispatcher("AddProduct.html");
				rd.include(request, response);
			}

		}catch(Exception e1){
			System.out.println(e1);
		}

		
	}

}
