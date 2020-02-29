package com.larionov.epam.service;

import com.larionov.epam.dao.ProductDAO;
import com.larionov.epam.item.Product;
import com.larionov.epam.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDAO dataProduct;

    @Override
    public Page<Product> findPaginated(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> products = new ArrayList<>();

        if (dataProduct.getAllProducts().size() < startItem) {
            products = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, dataProduct.getAllProducts().size());
            products = dataProduct.getAllProducts().subList(startItem, toIndex);
        }

        return new PageImpl<Product>(products, PageRequest.of(currentPage, pageSize), dataProduct.getAllProducts().size());
    }

    @Async
    @Override
    public CompletableFuture<List<Product>> getAllProduct() throws ExecutionException, InterruptedException {
        return CompletableFuture.completedFuture(dataProduct.getAllProducts());
    }

    @Async
    @Override
    public CompletableFuture<Product> getProductByArticle(Long article) {
        return CompletableFuture.completedFuture(dataProduct.getProductByArticle(article));
    }

    @Async
    @Override
    public void addNewProducts(Product product) {
        dataProduct.addProduct(product);
    }

}
