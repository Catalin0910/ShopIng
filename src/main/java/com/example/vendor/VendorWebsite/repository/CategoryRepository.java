package com.example.vendor.VendorWebsite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vendor.VendorWebsite.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
