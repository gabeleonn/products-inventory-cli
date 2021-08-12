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
    public boolean exists(String name) {
        return this.products.stream().anyMatch(product -> product.getName().equals(name));
    };

    @Override
    public void update(Product newProduct, Product oldProduct) {
        if (newProduct.getName().equals(oldProduct.getName())) {
            this.products = this.products.stream().map(
                    product -> {
                        if(product.getName().equals(newProduct.getName())) {
                            return newProduct;
                        } else {
                            return product;
                        }
                    }).collect(Collectors.toList());
        } else {
            this.products.removeIf(product -> product.getName().equals(oldProduct.getName()));
            this.create(newProduct);
        }
    }

    @Override
    public void delete(String name) {
        this.products.removeIf(product -> product.getName().equals(name));
    }
}
