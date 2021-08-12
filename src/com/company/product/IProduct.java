package com.company.product;

import com.company.utils.Screen;

public interface IProduct {

    Product builder(Screen screen);

    Product update(Screen screen);

    String getValidName(Screen screen);

    Double getValidPrice(Screen screen);

    String getValidUnit(Screen screen);

    int getValidQuantity(Screen screen);

    String getName();

    void setName(String name);

    Double getPrice();

    void setPrice(Double price);

    String getUnit();

    void setUnit(String unit);

    int getQuantity();

    void setQuantity(int quantity);
}
