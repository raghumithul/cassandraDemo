package com.cassandra.demo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cassandra.demo.dao.ProductDao;
import com.cassandra.demo.entity.Product;
import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

@Repository
public class ProductDaoImpl implements ProductDao {

	private String keyspace;
	
	@Override
	public void createProduct(Product product) {
		final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		final Session session = cluster.connect(getKeyspace());
		PreparedStatement statement = session.prepare("INSERT INTO product (productId, productName, productDescription) VALUES (?,?,?);");
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement.bind(product.getProductId(), product.getProductName(), product.getProductDescription()));
		cluster.close();
	    session.close();
		
		
	}

	@Override
	public Product updateProduct(Product product) {
		final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		final Session session = cluster.connect(getKeyspace());
		Product newProduct = null;
		PreparedStatement statement = session.prepare("UPDATE product set (productId, productName, productDescription) VALUES (?,?,?);");
		
		BoundStatement boundStatement = new BoundStatement(statement);
		
		ResultSet result = session.execute(boundStatement.bind(product.getProductId(), product.getProductName(), product.getProductDescription()));
		
		for (Row row : result) {
            System.out.format("%s %d %s\n", row.getString("name"),
                    row.getInt("id"), row.getString("address"));
            
            newProduct = new Product();
            newProduct.setProductId(row.getInt("productId"));
            newProduct.setProductDescription(row.getString("productDescription"));
            newProduct.setProductName(row.getString("productName"));
            
        }
		cluster.close();
	    session.close();
		return newProduct;
	}

	@Override
	public void deleteProduct(int productId) {
		final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		final Session session = cluster.connect(getKeyspace());
		PreparedStatement statement = session.prepare("delete FROM product where productId = ?;");
		BoundStatement boundStatement = new BoundStatement(statement);
		session.execute(boundStatement.bind(productId));
		cluster.close();
	    session.close();
		

	}
	
	@Override
	public List<Product> getAllProducts() {
		final Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		final Session session = cluster.connect(getKeyspace());
		List<Product> productList = new ArrayList<Product>();
		ResultSet results = session.execute("SELECT * FROM product");
        for (Row row : results) {
        	Product product = new Product();
        	product.setProductId(row.getInt("productId"));
        	product.setProductDescription(row.getString("productDescription"));
        	product.setProductName(row.getString("productName"));
        	productList.add(product);
            
        }
		cluster.close();
	    session.close();
	    return productList;
	}
	

	public String getKeyspace() {
		return keyspace;
	}

	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}

	
	
	

}
