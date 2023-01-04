package com.modeln.service;

import java.sql.SQLException;
import java.util.List;

import com.modeln.exceptions.NotValidException;
import com.modeln.model.Product;


public interface ProductService {
	public boolean add(Product product) throws ClassNotFoundException, SQLException, NotValidException;
	public List<Product> getProducts() throws ClassNotFoundException, SQLException;
	public Product getProductById(int id);
	public Product update(int id, Product product) throws ClassNotFoundException, SQLException;
	public boolean delete(int id) throws ClassNotFoundException, SQLException;
	public boolean deleteProductWhoSDateExpired() throws ClassNotFoundException, SQLException;
	public boolean updateExpiredDate() throws ClassNotFoundException, SQLException;
	public boolean applyDiscount() throws ClassNotFoundException, SQLException;
}
