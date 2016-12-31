package com.cassandra.demo.dao;

import java.util.List;

import com.cassandra.demo.entity.Product;

public interface ProductDao {

	public void createProduct(Product product);

	public Product updateProduct(Product product);

	public void deleteProduct(int productId);
	
	public List<Product> getAllProducts();

}
