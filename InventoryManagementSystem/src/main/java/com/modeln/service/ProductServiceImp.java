package com.modeln.service;

import java.sql.SQLException;
import java.util.List;
import com.modeln.dao.ProductDao;
import com.modeln.dao.ProductDaoImp;
import com.modeln.exceptions.NotValidException;
import com.modeln.model.Product;
import com.modeln.validation.Validate;

public class ProductServiceImp implements ProductService{

	ProductDao pd = new ProductDaoImp();
	
	@Override
	public boolean add(Product product) throws ClassNotFoundException, SQLException, NotValidException {
		boolean f = false;
		try {
			if(!Validate.validateName(product).equals("valid")){
				f = true;
				throw new NotValidException(Validate.validateName(product));
				
				
			}
		} catch (NotValidException e) {
			System.out.println(e.getMessage());
		}
	if(f) return false;
	if(pd.addProduct(product)) return true;
	return false;
	}

	@Override
	public List<Product> getProducts() throws ClassNotFoundException, SQLException {
		List<Product> products = pd.getAllProducts();
		return products;
	}

	@Override
	public Product getProductById(int id) {
		
		return null;
	}

	@Override
	public Product update(int id, Product product) throws ClassNotFoundException, SQLException {
		Product product1 = pd.updateProduct(id, product);
		return product1;
	}

	@Override
	public boolean delete(int id) throws ClassNotFoundException, SQLException {
		if(pd.deleteProduct(id)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteProductWhoSDateExpired() throws ClassNotFoundException, SQLException {
		if(pd.deleteProductWhoSDateExpired()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateExpiredDate() throws ClassNotFoundException, SQLException {
		if(pd.updateExpireDate()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean applyDiscount() throws ClassNotFoundException, SQLException {
		if(pd.applyDiscount()) {
			return true;
		}
		return false;
	}

}
