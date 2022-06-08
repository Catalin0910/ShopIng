package com.example.vendor.VendorWebsite.global;

import java.util.ArrayList;
import java.util.List;

import com.example.vendor.VendorWebsite.model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	static {
		cart = new ArrayList();
	}

}
