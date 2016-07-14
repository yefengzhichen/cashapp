package com.yefeng.cashapp.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yefeng.cashapp.model.Product;

public class ProductDaoTest {
	ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

	@Test
	public void saveTest() {
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		Product product = new Product("Item0001", "apple", "kg", "fruit", "fresh fruit", 13.00);
		productDao.save(product);
		boolean valid = productDao.contains(product);
		Assert.assertTrue(valid);
	}

	@Test
	public void updateTest() {
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		Product product = new Product("Item0001", "apple", "kg", "fruit", "fresh fruit", 12.5);
		productDao.update(product);
		Product product2 = productDao.getByBarcode("Item0001");
		// boolean valid = (product.getPrice() == product2.getPrice());
		boolean valid = (product.getPrice().equals(product2.getPrice()));
		Assert.assertTrue(valid);
	}

	@Test
	public void getAllTest() {
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		Product product = new Product("Item0002", "milk", "box", "drink", "milk drink", 3.5);
		productDao.save(product);
		List<Product> result = productDao.getAll();
		System.out.println(result);
	}

	@Test
	public void setDiscountTest() {
		ProductDao productDao = (ProductDao) context.getBean("productDao");
		Product product = new Product("Item0002", "milk", "box", "drink", "milk drink", 3.5);
		productDao.save(product);
		Assert.assertFalse(product.isDiscount());
		productDao.setDiscount("Item0002");
		Product product2 = productDao.getByBarcode("Item0002");
		Assert.assertTrue(product2.isDiscount());
	}

}
