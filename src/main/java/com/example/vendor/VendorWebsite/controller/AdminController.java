package com.example.vendor.VendorWebsite.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.vendor.VendorWebsite.dto.ProductDTo;
import com.example.vendor.VendorWebsite.model.Category;
import com.example.vendor.VendorWebsite.model.Product;
import com.example.vendor.VendorWebsite.service.CategoryService;
import com.example.vendor.VendorWebsite.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String addCategorie(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postCateAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategori(category);
		return "redirect:/admin/categories";
	}

	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCateg(@PathVariable int id) {
		categoryService.removeCateById(id);
		return "redirect:/admin/categories";

	}

	@GetMapping("/admin/categories/update/{id}")
	public String updateCateg(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategById(id);
		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else {
			return "404";
		}
	}

	// product
	@GetMapping("/admin/products")
	public String products(Model model) {
		model.addAttribute("products", productService.getAllProduct());
		return "products";

	}

	@GetMapping("/admin/products/add")
	public String productsAddGet(Model model) {
		model.addAttribute("productDTO", new ProductDTo());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";

	}

	@PostMapping("/admin/products/add")
	public String productAddPost(@ModelAttribute("productDTO") ProductDTo productDTo,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws IOException {

		Product product = new Product();
		product.setId(productDTo.getId());
		product.setName(productDTo.getName());
		product.setCategory(categoryService.getCategById(productDTo.getCategoryId()).get());
		product.setPrice(productDTo.getPrice());
		product.setWeight(productDTo.getWeight());
		product.setDescription(productDTo.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		} else {
			imageUUID = imgName;
		}
		product.setImageName(imageUUID);
		productService.addProducts(product);
		
		return "redirect:/admin/products";

	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.removeProductsById(id);
		return "redirect:/admin/products";

	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTo productDTo = new ProductDTo();
		productDTo.setId(product.getId());
		productDTo.setName(product.getName());
		productDTo.setCategoryId((product.getCategory().getId()));
		productDTo.setPrice(product.getPrice());
		productDTo.setWeight(product.getWeight());
		productDTo.setDescription(product.getDescription());
		productDTo.setImageName(product.getImageName());
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTo);
		
		return "productsAdd";
	}

}
