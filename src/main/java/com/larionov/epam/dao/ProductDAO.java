package com.larionov.epam.dao;

import com.larionov.epam.item.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO {

    List<Product> getAllProducts();

    Product getProductByArticle(Long article);

    void addProduct(Product product);

}
