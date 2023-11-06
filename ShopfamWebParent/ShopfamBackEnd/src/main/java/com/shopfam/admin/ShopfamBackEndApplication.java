package com.shopfam.admin;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.shopfam.common.entity" , "com.shopme.admin.user"})
public class ShopfamBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopfamBackEndApplication.class, args);
	}

}
