package com.modeln;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.modeln.model.Product;
import com.modeln.service.ProductService;
import com.modeln.service.ProductServiceImp;


public class UpdateProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Integer id = null;
		String name = request.getParameter("name");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate manufacturingDate = LocalDate.parse(request.getParameter("manufacturingdate"),formatter);
		Integer usebeforemonths = 0;
//		LocalDate contractEndDate = LocalDate.parse(request.getParameter("contractenddate"),formatter);
		Integer price = 0;
		try {
			 id = request.getParameter("id").equals("")?null:Integer.parseInt(request.getParameter("id"));
			 usebeforemonths = request.getParameter("usebeforemonths").equals("")?null:Integer.parseInt(request.getParameter("usebeforemonths"));
			 price = request.getParameter("price").equals("")?null:Integer.parseInt(request.getParameter("price"));
		}
		catch(NumberFormatException ex) {
			out.println("Integer Field Should not contains Characters");
			return;
		}
		
		Product product = new Product();
		product.setPrice(price);
		product.setName(name);
		product.setManufacturingDate(manufacturingDate);
		product.setUseBeforeMonths(usebeforemonths);
//		employee.setContractEndDate(contractEndDate);
		product.setPrice(price);

		try {
			ProductService ps = new ProductServiceImp();
			List<Product> list = new ArrayList<>();
			list = ps.getProducts();

			String str = " Product not found with given Id <br>";
			boolean flag = false;
			Iterator<Product> it = list.iterator();
			while (it.hasNext()) {
				Product p = (Product) it.next();
				if ( p.getProductId().equals(id)) {
					flag = true;
					out.print("ID found");
					Product result = ps.update(id, product);
					if (result != null) {
						out.println("Employee Updated Successfully <br>");
					}
				}
			}
			if(!flag) {
			out.println(str);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
