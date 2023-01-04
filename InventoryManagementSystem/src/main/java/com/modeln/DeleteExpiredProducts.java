package com.modeln;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modeln.service.ProductService;
import com.modeln.service.ProductServiceImp;


public class DeleteExpiredProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService ps = new ProductServiceImp();
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		try {
			if(ps.deleteProductWhoSDateExpired()) {
				out.println("Deleted All Products Which are Expired");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
