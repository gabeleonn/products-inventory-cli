package com.company.inventory;

import com.company.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory implements IInventory {
    List<Product> products = new ArrayList<>();


    @Override
    public void create(Product product) {
        this.products.add(product);
    }

    @Override
    public Product findOne(String name) {
        try {
            return this.products.stream().filter(
                    product -> {
                        boolean isEqual = product.getName().equals(name);
                        return isEqual;
                    }
            ).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        return this.products;
    }

    @Override
    public void update(Product updated) {
        Product old = this.findOne(updated.getName());
        old.setPrice(updated.getPrice());
        old.setQuantity(updated.getQuantity());
        old.setUnit(updated.getUnit());
        this.products = this.products.stream().map(
                product -> {
                    if(product.getName().equals(updated.getName())) {
                        return old;
                    } else {
                        return product;
                    }
                }).collect(Collectors.toList());
    }

    @Override
    public void delete(String name) {
        this.products.removeIf(product -> product.getName().equals(name));
    }
}
