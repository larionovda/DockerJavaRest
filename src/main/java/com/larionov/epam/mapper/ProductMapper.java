package com.larionov.epam.mapper;

import com.larionov.epam.item.Product;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setArticle((long) resultSet.getInt("article"));
        product.setIdBrand((long) resultSet.getInt("id_brand"));
        product.setIdType((long) resultSet.getInt("id_type"));
        product.setIdCategory((long) resultSet.getInt("id_category"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setIdSupplier((long) resultSet.getInt("id_supplier"));
        return product;
    }
}
