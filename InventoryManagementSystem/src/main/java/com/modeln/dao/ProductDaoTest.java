package com.modeln.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.modeln.model.Product;

class ProductDaoTest {

	@Test
	void addProductTest() throws ClassNotFoundException, SQLException{
		ProductDaoImp pd = new ProductDaoImp();
		Product product = new Product(768788,"abc",LocalDate.of(2022, 12, 12),3,null,1000);
		assertTrue(pd.addProduct(product));
	}
	
	@Test
	void getProductByIdTest() throws ClassNotFoundException, SQLException {
		ProductDaoImp pd = new ProductDaoImp();
		Product product = pd.getProductById(1);
		Product product1 = new Product(4174,"abc",LocalDate.of(2022, 12, 12),3,null,1000);
		assertNotSame(product,product1);
	}

	@Test
	void updateProductTest() throws ClassNotFoundException, SQLException {
		ProductDaoImp pd = new ProductDaoImp();
		Product product = new Product(4174,"abc",LocalDate.of(2022, 12, 12),3,null,1000);
		Product prod = pd.updateProduct(4174, product);
		assertSame(product,prod);
	}
	
	@Test
	void deleteProductTest() throws ClassNotFoundException, SQLException {
		ProductDaoImp pd = new ProductDaoImp();
		assertTrue(pd.deleteProduct(1));
	}
	
	@Test
	void deleteProductWhoSDateExpiredTest() throws ClassNotFoundException, SQLException {
		ProductDaoImp pd = new ProductDaoImp();
		assertTrue(pd.deleteProductWhoSDateExpired());
	}
	@Test
	void updateExpireDateTest() throws ClassNotFoundException, SQLException {
		ProductDaoImp pd = new ProductDaoImp();
		assertTrue(pd.updateExpireDate());
	}
	@Test
	void applyDiscountTest() throws ClassNotFoundException, SQLException {
		ProductDaoImp pd = new ProductDaoImp();
		assertTrue(pd.updateExpireDate());
	}
	
	
}
