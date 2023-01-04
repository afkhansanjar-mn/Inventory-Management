package com.modeln.dao;

import java.sql.SQLException;
import java.util.List;

import com.modeln.model.Product;

public interface ProductDao {
	public boolean addProduct(Product product) throws ClassNotFoundException, SQLException;
	public List<Product> getAllProducts() throws ClassNotFoundException, SQLException;
	public Product getProductById(int id) throws ClassNotFoundException, SQLException;
	public Product updateProduct(int id, Product employee) throws SQLException, ClassNotFoundException;
	public boolean deleteProduct(int id) throws SQLException, ClassNotFoundException;
	public boolean deleteProductWhoSDateExpired() throws ClassNotFoundException, SQLException;
	public boolean updateExpireDate() throws ClassNotFoundException, SQLException;
	public boolean applyDiscount() throws ClassNotFoundException, SQLException;
}
