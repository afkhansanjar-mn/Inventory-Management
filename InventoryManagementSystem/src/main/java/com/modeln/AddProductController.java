package com.modeln;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modeln.exceptions.NotValidException;
import com.modeln.model.Product;
import com.modeln.service.ProductService;
import com.modeln.service.ProductServiceImp;
import com.modeln.validation.Validate;

public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Integer id = null;
		String name = request.getParameter("name");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate manufacturingDate = LocalDate.parse(request.getParameter("manufacturingdate"),formatter);
		Integer usebeforeMonths = null;
//		LocalDate contractEndDate = LocalDate.parse(request.getParameter("contractenddate"),formatter);
		Integer price = null;
		try {
			 id = request.getParameter("id").equals("")?null:Integer.parseInt(request.getParameter("id"));
			 usebeforeMonths = Integer.parseInt(request.getParameter("usebeforemonths"));
			 price = Integer.parseInt(request.getParameter("price"));
		}
		catch(NumberFormatException ex) {
			out.println("Integer Field Should not contains Characters");
			return;
		}
		Product product = new Product();
		product.setProductId(id);
		product.setName(name);
		product.setManufacturingDate(manufacturingDate);
		product.setUseBeforeMonths(usebeforeMonths);
		product.setPrice(price);
		try {
			ProductService proServ = new ProductServiceImp();
			boolean f = proServ.add(product);
			if(f) {
				out.println("Product Added Successfully");
				out.println(product.getProductId()); 
				out.println(product.getName()); 
				out.println(product.getManufacturingDate()); 
				out.println(product.getUseBeforeMonths()); 
				out.println(product.getExpireDate()); 
				out.println(product.getPrice()); 
			}
			else {
				out.println("Product Not Added Because "+Validate.validateName(product));
			}
			
		}
		catch (NotValidException e) {
			// TODO Auto-generated catch block
			out.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
