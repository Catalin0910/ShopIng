package com.example.vendor.VendorWebsite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vendor.VendorWebsite.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findUserByEmail(String email);

}
