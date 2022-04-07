package lv.venta.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.demo.models.Product;
import lv.venta.demo.services.CRUDProduct;

@Controller
public class MyFirstController {
	@Autowired
	private CRUDProduct productCRUDService;
	//url - localhost:8080/home
	@GetMapping("/home")
	public String getHomePage()
	{
		System.out.println("Mans kontrolieris");
		return"home";//paradis home.html lapu
	}
	
	@GetMapping("/send")
	public String getSend(Model model)
	{
		model.addAttribute("message","Hewlo!");
		model.addAttribute("message2","Moris");
		model.addAttribute("message3","Bond");
		return"message";// paradis message.html
	}
	//izveidot kontrolieru funkc, kas suuta objektu uz frontend
	@GetMapping("/object")
	public String getObject(Model model) {
		Product prod= new Product("abols","garsigs",10,0.99f);
		model.addAttribute("object", prod);
		return "one-product-page";
	}
	@GetMapping("/prod")
	public String getOtherObject(Model model) {
		try {
			model.addAttribute("prod", productCRUDService.readAllProducts());
			return "two-product-page";
		}
		catch (Exception e){
			return "error-page";
		}
	}
	@GetMapping("/prodFilter")
	public String getAllProductsFilter(@RequestParam(name="id") int id,  Model model) {
		try {
				model.addAttribute("object", productCRUDService.readProductById(id));
				return "one-product-page";
		}
		 catch (Exception e){
		return "error-page";
		}
	}
	@GetMapping("/prod/{id}")
	public String getAllProductsById(@PathVariable(name="id") int id, Model model) {
		try {
			model.addAttribute("object", productCRUDService.readProductById(id));
			return "one-product-page";
	}
	 catch (Exception e){
	return "error-page";
	}
	}
	
	
	@GetMapping("/addProduct")
	public String getAddProduct(Product product) {
		return "add-product-page";
	}
	
	
	@PostMapping("/addProduct")
	public String postAddProduct(Product product) {
		if(productCRUDService.createNewProduct(product))
			return"redirect:/prod";
		else
			return"redirect:/error";
	}
	
	
	@GetMapping("/updateProduct/{id}")// localhost:8080/updateProduct/2
		public String getUpdateProduct(@PathVariable(name="id") int id, Model model) {
		try {
			model.addAttribute("product", productCRUDService.readProductById(id));
			return "update-product-page";
		}
		catch (Exception e){
			return "error-page";
		}
				
	
		
	}
	
	@PostMapping("/updateProduct/{id}")
	public String postUpdateProduct(@PathVariable(name="id")int id, Product product) {
		if(productCRUDService.updateProductById(id, product))
				return "redirect:/prod/"+id;
		else
		
				return "redirect:/error-page";
	}
	// delete funkc
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") int id,Model model) {
		if(productCRUDService.deleteByProductId(id)) {
				model.addAttribute("prod", productCRUDService.readAllProducts());
				return"two-product-page";
			}
		else return"error-page";
	}
	

		
	}
	
	


