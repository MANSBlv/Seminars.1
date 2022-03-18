package lv.venta.demo.controllers;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lv.venta.demo.models.Product;

@Controller
public class MyFirstController {

	private ArrayList<Product> allproducts= new ArrayList<>(Arrays.asList(new Product("abols","garsigs",10,0.99f),
			new Product("bumbieris","zals", 2, 0.12f)));
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
		model.addAttribute("prod", allproducts);
		return "two-product-page";
	}
	@GetMapping("/prodFilter")
	public String getAllProductsFilter(@RequestParam(name="id") int id,  Model model) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				model.addAttribute("object", temp);
				return "one-product-page";
			}
		}
		return "error-page";
	}
	@GetMapping("/prod/{id}")
	public String getAllProductsById(@PathVariable(name="id") int id, Model model) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				model.addAttribute("object", temp);
				return "one-product-page";
			}
		}
		return "error-page";
	}
	@GetMapping("/addProduct")
	public String getAddProduct(Product product) {
		return "add-product-page";
	}
	@PostMapping("/addProduct")
	public String postAddProduct(Product product) {
		Product newProd= new Product(product.getTitle(), product.getDescription(),product.getQuantity(),product.getPrice());
		allproducts.add(newProd);
		return"redirect:/prod";
	}
	@GetMapping("/updateProduct/{id}")// localhost:8080/updateProduct/2
		public String getUpdateProduct(@PathVariable(name="id") int id, Model model) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				model.addAttribute("product", temp);
				return "update-product-page";
			}
		}
		return "error-page";
	}
	@PostMapping("/updateProduct/{id}")
	public String postUpdateProduct(@PathVariable(name="id")int id, Product product) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				temp.setTitle(product.getTitle());
				temp.setDescription(product.getDescription());
				temp.setPrice(product.getPrice());
				temp.setQuantity(product.getQuantity());
				return "redirect:/prod/"+id;
			}
		}
		return "redirect:/error-page";
	}
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") int id,Model model) {
		for(Product temp: allproducts) {
			if(temp.getId()==id) {
				allproducts.remove(temp);
				model.addAttribute("prod", allproducts);
				return"two-product-page";
			}
		}
		return"error-page";
	}
	
	
}

