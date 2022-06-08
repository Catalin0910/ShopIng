package com.example.vendor.VendorWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CheckoutController {
	
	@PostMapping("/payNow")
	public String checkoutOk() {
		return "confirmation";
	}

}
