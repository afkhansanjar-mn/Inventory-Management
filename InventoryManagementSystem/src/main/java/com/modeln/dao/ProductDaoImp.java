package com.modeln.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.modeln.model.Product;
import com.modeln.util.DbConnection;

public class ProductDaoImp implements ProductDao{

	public ProductDaoImp() {}
	@Override
	public boolean addProduct(Product product) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = DbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into product values(?,?,?,?,?,?)");
		ps.setInt(1, product.getProductId());
		ps.setString(2, product.getName());
		ps.setDate(3, java.sql.Date.valueOf(product.getManufacturingDate()));
		ps.setInt(4, product.getUseBeforeMonths());
		ps.setDate(5,null);
		ps.setInt(6,product.getPrice());
		ps.execute();
		return true;
	}

	@Override
	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con = DbConnection.getConnection();
		List<Product> products = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement("select * from product order by expiredate desc");
		DateTimeFormatter d=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setProductId(rs.getInt(1));;
			product.setName(rs.getString(2));
			product.setManufacturingDate(LocalDate.parse(String.valueOf(rs.getDate(3)),d));;
			product.setUseBeforeMonths(rs.getInt(4));;
			product.setExpireDate((rs.getDate(5) == null)?null:LocalDate.parse(String.valueOf(rs.getDate(5)),d));
			product.setPrice(rs.getInt(6));
			products.add(product);
		}
		return products;
	}

	@Override
	public Product getProductById(int id) throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		DateTimeFormatter d=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		PreparedStatement ps = con.prepareStatement("select * from product where productid=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Product product = new Product();
		while(rs.next()) {
			product.setProductId(rs.getInt(1));;
			product.setName(rs.getString(2));
			product.setManufacturingDate(LocalDate.parse(String.valueOf(rs.getDate(3)),d));;
			product.setUseBeforeMonths(rs.getInt(4));;
			product.setExpireDate((rs.getDate(5) == null)?null:LocalDate.parse(String.valueOf(rs.getDate(5)),d));
			product.setPrice(rs.getInt(6));
		}
		return product;
	}

	@Override
	public Product updateProduct(int id, Product product) throws SQLException, ClassNotFoundException {
		Connection con = DbConnection.getConnection();
		PreparedStatement ps1 = con.prepareStatement("update product set name =?,manufacturingdate=?,usebeforemonths=?,expiredate=?,price=? where productid=?");
		ps1.setString(1, product.getName());
		ps1.setDate(2, java.sql.Date.valueOf(product.getManufacturingDate()));
		ps1.setInt(3, product.getUseBeforeMonths());
		ps1.setDate(4,null);
		ps1.setInt(5,product.getPrice());
		ps1.setInt(6, id);
		ps1.execute();
		return product;
	}

	@Override
	public boolean deleteProduct(int id) throws SQLException, ClassNotFoundException {
		Connection con = DbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from product where productid=?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		int c = 0;
		while(rs.next()) {
			c++;
		}
		if(c == 0) {
			return false;
		}
		PreparedStatement ps1 = con.prepareStatement("delete from product where productid=?");
		ps1.setInt(1, id);
		ps1.execute();
		return true;
	}

	@Override
	public boolean deleteProductWhoSDateExpired() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement ps = con.prepareStatement("delete from product where expiredate>(select curdate())");
		ps.execute();
		return true;
	}

	@Override
	public boolean updateExpireDate() throws ClassNotFoundException, SQLException {
		Connection con = DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement("UPDATE product SET expiredate = DATE_ADD(manufacturingdate, INTERVAL usebeforemonths MONTH)");
	    return !(pst.execute());
	}
	@Override
	public boolean applyDiscount() throws ClassNotFoundException, SQLException {
//		Connection con = DbConnection.getConnection();
//		PreparedStatement pst=con.prepareStatement("select * from product where date_add(manufacturingdate, INTERVAL 1 YEAR)  between now() and  DATE_ADD(now(), INTERVAL 1 MONTH)");
//		DateTimeFormatter d=DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		ResultSet rs = pst.executeQuery();
//		while(rs.next()) {
//			Integer id = rs.getInt(1);
//			Integer price = rs.getInt(6);
//			price = price-(price*10);
//			PreparedStatement ps1 = con.prepareStatement("update product set price=? where productid=?");
//			ps1.setInt(1, price);
//			ps1.setInt(2, id);
//			ps1.execute();
//		}
//	return true;
		LocalDate toDay= LocalDate.now();
		Connection con= DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement("Update product set price=price-price*0.10 where datediff(expiredate,?)<31 and datediff(expiredate,?)>0");
		pst.setDate(1, java.sql.Date.valueOf(toDay));
		pst.setDate(2, java.sql.Date.valueOf(toDay));
		return !(pst.execute());
		
		
	}
}
