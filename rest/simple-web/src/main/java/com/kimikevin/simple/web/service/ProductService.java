package com.kimikevin.simple.web.service;

import com.kimikevin.simple.web.model.Product;
import com.kimikevin.simple.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id)
                .orElseThrow(IllegalStateException::new);
    }

    public void addProduct(Product product) {
        repository.save(product);
    }

    public void updateProduct(Product product) {
       repository.save(product);
    }

    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
