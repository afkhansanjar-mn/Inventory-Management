package com.modeln.validation;

import com.modeln.model.Product;

public class Validate {
	public static String validateName(Product product) {
		if( product.getProductId() == null) return "ID Cannot be null";
		if(product.getName() == null) return "Name Cannot be null";
		if(product.getName().length()>15) return "Name Size should not be Exceed 15 Characters";
		if(product.getName().length()>0 && Character.isDigit(product.getName().charAt(0))) return "Name Should not Starts with Number";
		return "valid";
	}
}
