package lv.venta.demo.services;
import java.util.ArrayList;
import lv.venta.demo.models.*;
public interface CRUDProduct {
	//CRUD
	//C - create
	public abstract boolean createNewProduct(Product product);
	
	//R -read or retrieve
	public abstract ArrayList<Product> readAllProducts();
	
	public abstract Product readProductById(int id) throws Exception;
	
	//U - update
	public abstract boolean updateProductById(int id, Product product);
	
	//D -delete
	public abstract boolean deleteByProductId(int id);

	//boolean createNewProduct(Product product);
	
	
}
