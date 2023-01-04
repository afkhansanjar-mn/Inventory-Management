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

public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Integer id = null;
		try {
		 id = Integer.parseInt(request.getParameter("id"));
		}
		catch(NumberFormatException ex) {
			out.println("Id should be Number");
			return;
		}
		try {
			ProductService productService = new ProductServiceImp();
			boolean result = productService.delete(id);
			if(result) {
				out.println("Employee Deleted Successfully");
			}
			else {
				out.println("Employee with id not Found");
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
