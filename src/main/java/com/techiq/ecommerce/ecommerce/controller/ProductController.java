package com.techiq.ecommerce.ecommerce.controller;

import com.techiq.ecommerce.ecommerce.model.Product;
import com.techiq.ecommerce.ecommerce.repository.ProductRepository;
import com.techiq.ecommerce.ecommerce.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    public static String uploadDirectory=System.getProperty("user.dir") + "/src/main/resources/static/imagedata";
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String getHome() {
        return "index";
    }

    @GetMapping("/all")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProductSubmit(@ModelAttribute Product product, @RequestParam("file") MultipartFile file, Model model) {
        StringBuilder fileNames = new StringBuilder();
        String filename=product.getId() + file.getOriginalFilename().substring(file.getOriginalFilename().length()-4);
        Path fileNamePath = Paths.get(uploadDirectory, filename);
        try {
            Files.write(fileNamePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImage(filename.toString());
        productRepository.save(product);
        return "redirect:/products/all";
    }

    @GetMapping("/updateProduct/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "updateProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProductSubmit(@ModelAttribute Product product) {
        productRepository.save(product);
        return "redirect:/products/all";
    }

    @RequestMapping("deleteProduct/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.delete(id);
        return "redirect:/products/all";
    }
}

