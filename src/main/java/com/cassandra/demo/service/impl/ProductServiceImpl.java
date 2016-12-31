package com.cassandra.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cassandra.demo.dao.ProductDao;
import com.cassandra.demo.entity.Product;
import com.cassandra.demo.service.ProductService;
 
@Service
public class ProductServiceImpl implements ProductService{

	
	@Autowired
	ProductDao productDao;
	
	@Override
	public void createProduct(Product product) {
		 productDao.createProduct(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(int productId) {
		productDao.deleteProduct(productId);
	}

	@Override
	public List<Product> getAllProducts() {
		return productDao.getAllProducts();
	}

}
