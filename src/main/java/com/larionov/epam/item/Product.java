package com.larionov.epam.item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
    private Long article;
    private Long idBrand;
    private Long idType;
    private Long idCategory;
    private Long idSupplier;
    private BigDecimal price;

    @Override
    public String toString() {
        return "Product{" +
                "article=" + article +
                ", idBrand=" + idBrand +
                ", idType=" + idType +
                ", idCategory=" + idCategory +
                ", idSupplier=" + idSupplier +
                '}';
    }

}
