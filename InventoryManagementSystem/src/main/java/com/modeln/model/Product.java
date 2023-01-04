package com.modeln.model;

import java.time.LocalDate;

public class Product {
	private Integer productId;
	private String name;
	private LocalDate manufacturingDate;
	private Integer useBeforeMonths;
	private LocalDate expireDate;
	private Integer price;
	
	public Product() {}
	public Product(Integer productId, String name, LocalDate manufacturingDate, Integer useBeforeMonths,
			LocalDate expireDate, Integer price) {
		super();
		this.productId = productId;
		this.name = name;
		this.manufacturingDate = manufacturingDate;
		this.useBeforeMonths = useBeforeMonths;
		this.expireDate = expireDate;
		this.price = price;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getManufacturingDate() {
		return manufacturingDate;
	}
	public void setManufacturingDate(LocalDate manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}
	public Integer getUseBeforeMonths() {
		return useBeforeMonths;
	}
	public void setUseBeforeMonths(Integer useBeforeMonths) {
		this.useBeforeMonths = useBeforeMonths;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", manufacturingDate=" + manufacturingDate
				+ ", useBeforeMonths=" + useBeforeMonths + ", expireDate=" + expireDate + ", price=" + price + "]";
	}
	
	
}
