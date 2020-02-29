package com.larionov.epam.controller;

import com.larionov.epam.item.Product;
import com.larionov.epam.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("product")
public class ControllerProduct {

    private static final Logger logger = LoggerFactory.getLogger(ControllerProduct.class);
    @Autowired
    private ProductService productService;
    private ModelAndView modelAndView = new ModelAndView("index");

    @GetMapping
    public ModelAndView getAllProducts(@RequestParam("page") Optional<Integer> page,
                                       @RequestParam("size") Optional<Integer> size) throws ExecutionException, InterruptedException {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
        List<Product> products = productPage.getContent();

        modelAndView.addObject("productPage", productPage);
        modelAndView.addObject("products", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
        }

        return modelAndView;
    }

    @PostMapping
    public void addNewProduct(@ModelAttribute("product") Product newProduct) {
        productService.addNewProducts(newProduct);
    }

    @RequestMapping("/add-product")
    public ModelAndView redirectToPageAdd() {
        ModelAndView modelAndView = new ModelAndView("addProduct");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

}
