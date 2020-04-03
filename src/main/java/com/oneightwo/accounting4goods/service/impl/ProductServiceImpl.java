package com.oneightwo.accounting4goods.service.impl;

import com.oneightwo.accounting4goods.model.Product;
import com.oneightwo.accounting4goods.repository.ProductRepository;
import com.oneightwo.accounting4goods.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getFilteredProductByNameAndType(String productName, String typeName) {
        return productRepository.getFilteredProductByNameAndType(productName, typeName);
    }

    @Override
    public List<Product> getFilteredProductByType(String typeProduct) {
        return productRepository.getFilteredProductByType(typeProduct);
    }

    @Override
    public List<Product> getFilteredProductByName(String productName) {
        return productRepository.getFilteredProductByName(productName);
    }
}
