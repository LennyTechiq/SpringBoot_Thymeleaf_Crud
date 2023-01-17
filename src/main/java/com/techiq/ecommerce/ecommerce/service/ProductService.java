package com.techiq.ecommerce.ecommerce.service;

import com.techiq.ecommerce.ecommerce.model.Product;
import com.techiq.ecommerce.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

//    public void saveProduct(MultipartFile file, String name, Double price, int quantity) throws IOException {
//        Product product = new Product();
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        if(fileName.contains("..")) {
//            System.out.println("Invalid image file!");
//        }
//        product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//        product.setName(name);
//        product.setPrice(price);
//        product.setQuantity(quantity);
//        productRepository.save(product);
//    }
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
