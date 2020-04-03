package com.oneightwo.accounting4goods.service;

import com.oneightwo.accounting4goods.model.Product;
import com.oneightwo.accounting4goods.service.basic.BasicService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService extends BasicService<Product> {

    List<Product> getFilteredProductByNameAndType(String productName, String typeName);

    List<Product> getFilteredProductByType(String typeProduct);

    List<Product> getFilteredProductByName(String productName);
}
