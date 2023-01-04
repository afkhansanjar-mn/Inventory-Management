package com.modeln;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.modeln.model.Product;
import com.modeln.service.ProductService;
import com.modeln.service.ProductServiceImp;

/**
 * Servlet implementation class GetProductsController
 */
public class GetProductsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
			List<Product> plist=new ArrayList<>();
			
			ProductService ps=new ProductServiceImp();
			out.println("<table align='center' border='2'>  ");
			try {
				plist= ps.getProducts();
				for( Product e: plist) {
				out.println("<tr><td>"+e.getProductId()+"</td><td>"+e.getName()+"</td><td>"+e.getManufacturingDate()+"</td><td>"+e.getExpireDate()+"</td><td>"+e.getUseBeforeMonths()+"</td><td>"+e.getPrice()+"</td></tr>");
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			out.println("</table>");
	}

}
