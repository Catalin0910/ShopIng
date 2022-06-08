package com.example.vendor.VendorWebsite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vendor.VendorWebsite.model.Category;
import com.example.vendor.VendorWebsite.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}

	public void addCategori(Category category) {
		categoryRepository.save(category);
	}
	public void removeCateById(int id) {
		categoryRepository.deleteById(id);
	}
	public Optional<Category> getCategById(int id) {
		return categoryRepository.findById(id);
	}

	public Optional<Category> getCategById(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
