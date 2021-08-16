package com.company.inventory;

import com.company.product.Product;

import java.util.List;

public interface IInventory {

    void create(Product product);

    Product findOne(String name);

    List<Product> findAll();

    void update(Product newProduct, Product oldProduct);

    void delete(String name);

    boolean exists(String name);

    void raiseQuantity(Product product, int quantity);

    void lowerQuantity(Product product, int quantity);

    void raisePrice(Product product, Double percentage);

    void lowerPrice(Product product, Double percentage);

    int getSize();
}
