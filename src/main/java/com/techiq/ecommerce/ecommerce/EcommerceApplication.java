package com.techiq.ecommerce.ecommerce;

import com.techiq.ecommerce.ecommerce.controller.ProductController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class EcommerceApplication {

	public static void main(String[] args) {
		new File(ProductController.uploadDirectory).mkdir();

		SpringApplication.run(EcommerceApplication.class, args);
	}

}
