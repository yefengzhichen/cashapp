package com.yefeng.cashapp.dao;

import java.util.List;

import com.yefeng.cashapp.model.Product;

public interface ProductDao {
	public void save(Product product);
	public void update(Product product);
	public Product getByBarcode(String barcode);
	public boolean contains(Product product);
	public List<Product> getAll();
	public void setDiscount(String barcode);
}
