package com.company.inventory;

import com.company.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory implements IInventory {
    List<Product> products = new ArrayList<>();
    int size = 0;

    public Inventory() {}

    public int getSize() {
        return this.size;
    }

    public void create(Product product) {
        if(size <= 20) {
            this.products.add(product);
            this.size++;
        }
    }

    public Product findOne(String name) {
        try {
            return this.products.stream().filter(
                    product -> product.getName().equals(name)
            ).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public List<Product> findAll() {
        return this.products;
    }

    public boolean exists(String name) {
        return this.products.stream().anyMatch(product -> product.getName().equals(name));
    }

    public void raiseQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() + quantity);
    }

    public void lowerQuantity(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity);
    }

    public void raisePrice(Product product, Double percentage) {
        product.setPrice(product.getPrice() + (product.getPrice() * (percentage / 100)));
    }

    public void lowerPrice(Product product, Double percentage) {
        product.setPrice(product.getPrice() - (product.getPrice() * (percentage / 100)));
    }

    public void update(Product newProduct, Product oldProduct) {
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setQuantity(newProduct.getQuantity());
        oldProduct.setUnit(newProduct.getUnit());
    }

    public void delete(String name) {
        this.products.removeIf(product -> product.getName().equals(name));
        this.size--;
    }
}
