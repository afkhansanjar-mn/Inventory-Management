package com.modeln;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modeln.dao.ProductDao;
import com.modeln.dao.ProductDaoImp;
import com.modeln.exceptions.NotValidException;
import com.modeln.model.Product;


public class GetProductById extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Integer id = null;
		try {
		id = Integer.parseInt(request.getParameter("id"));
		}
		catch(NumberFormatException ex) {
			out.println("ID should not Contains Character");
		}
		ProductDao pd = new ProductDaoImp();
			
			Product product = null;
			try {
				product = pd.getProductById(id);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(product != null) {
			out.println(product.getName());
			}
			else {
				out.println("Product Not Found With Given ID");
			}
			
	}
}
