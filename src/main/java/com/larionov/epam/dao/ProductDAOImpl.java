package com.larionov.epam.dao;

import com.larionov.epam.mapper.ProductMapper;
import com.larionov.epam.item.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final String SQL_QUERY_GET_ALL = "SELECT * FROM public.\"Products\"";
    private final String SQL_INSERT_INTO = "INSERT INTO public.\"Products\"(\n" +
            "\tid_brand, id_type, id_category, price, id_supplier)\n" +
            "\tVALUES (?, ?, ?, ?, ?)";
    private final String SQL_QUERY_GET_PRODUCT_ID = "SELECT * FROM public.\"Products\" where article = ?";


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SQL_QUERY_GET_ALL, productMapper);
    }

    @Override
    public Product getProductByArticle(Long article) {
        return jdbcTemplate.queryForObject(SQL_QUERY_GET_PRODUCT_ID, new Object[]{article}, productMapper);
    }

    @Override
    public void addProduct(Product product) {
        jdbcTemplate.update(SQL_INSERT_INTO, product.getIdBrand(), product.getIdType(), product.getIdCategory(), product.getPrice(), product.getIdSupplier());
    }

}
