package com.company.product;

import java.math.BigDecimal;

public interface IProduct {
    private String validateName(String name) {
        return null;
    }

    private BigDecimal validatePrice(Double price) {
        return null;
    }

    private String validateUnit(String unit) {
        return null;
    }

    private int validateQuantity(int quantity) {
        return 0;
    }

    String getName();

    void setName(String name);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    void setPrice(int price);

    String getUnit();

    void setUnit(String unit);

    int getQuantity();

    void setQuantity(int quantity);
}
