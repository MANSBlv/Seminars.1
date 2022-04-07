package lv.venta.demo.repos;

import org.springframework.data.repository.CrudRepository;

import lv.venta.demo.models.Product;

public interface iProductRepo extends CrudRepository<Product, Integer>{

	boolean existsByTitleAndDescription(String title, String description);

	

}
