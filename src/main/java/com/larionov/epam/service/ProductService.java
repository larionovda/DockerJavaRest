package com.larionov.epam.service;

import com.larionov.epam.item.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public interface ProductService {

    CompletableFuture<List<Product>> getAllProduct() throws ExecutionException, InterruptedException;

    CompletableFuture<Product> getProductByArticle(Long Article);

    void addNewProducts(Product product);

    Page<Product> findPaginated(Pageable pageable);

}
