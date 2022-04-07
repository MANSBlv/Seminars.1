package lv.venta.demo.services.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.demo.models.Product;
import lv.venta.demo.services.CRUDProduct;

@Service
public class CRUDProductImpl implements CRUDProduct {

	private ArrayList<Product> allproducts= new ArrayList<>(Arrays.asList(new Product("abols","garsigs",10,0.99f),
			new Product("bumbieris","zals", 2, 0.12f)));
	
	@Override
	public boolean createNewProduct(Product product) {
		boolean isFound= false;
		for(Product pr: allproducts) {
			if(pr.getTitle().equals(product.getTitle()) && pr.getDescription().equals(product.getDescription())){
				isFound=true;
				break;
			}
		}
		if(!isFound) {
			
		
		Product newProd= new Product(product.getTitle(), product.getDescription(),product.getQuantity(),product.getPrice());
		allproducts.add(newProd);
		return true;
		}
		else {
		return false;
		}
	}

	@Override
	public ArrayList<Product> readAllProducts() {
		return allproducts;
	}

	@Override
	public Product readProductById(int id) throws Exception {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				return temp;
			}
		
		}
		throw new Exception("produkts neeksiste");
	}

	@Override
	public boolean updateProductById(int id, Product product) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				temp.setTitle(product.getTitle());
				temp.setDescription(product.getDescription());
				temp.setPrice(product.getPrice());
				temp.setQuantity(product.getQuantity());
				return true;
				
			}
		}
		return false;
	}

	@Override
	public boolean deleteByProductId(int id) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				allproducts.remove(temp);
				return true;
			}
		}
		return false;
	}
	
	

}
