package com.company.product;

import com.company.inventory.Inventory;
import com.company.utils.Screen.Screen;

import java.util.*;

public class Product implements IProduct {
    private Inventory inventory;

    private String name;
    private Double price;
    private String unit;
    private int quantity = 0;

    public Product() {
    }

    public Product(Inventory inventory) {
        this.inventory = inventory;
    }

    public Product builder(Screen screen) {
        this.name = this.getValidName(screen);
        this.price = this.getValidPrice(screen);
        this.unit = this.getValidUnit(screen);
        this.quantity = this.getValidQuantity(screen);
        return this;
    }

    public Product update(Screen screen) {
        Product newProduct = new Product(inventory);
        newProduct.setName(this.getValidName(screen));
        newProduct.setPrice(this.getValidPrice(screen));
        newProduct.setUnit(this.getValidUnit(screen));
        newProduct.setQuantity(this.getValidQuantity(screen));
        return newProduct;
    }

    public String getValidName(Screen screen) {
        boolean isValid = false;
        String name = "";

        while(!isValid) {
            if(this.name != null) {
                name = screen.getInputString(String.format("Nome (%s): ", this.name));
                if(name.isBlank()) {
                    name = this.name;
                    break;
                }
            } else {
                name = screen.getInputString("Nome: ");
            }

            if(!inventory.exists(name) && !name.isBlank()) {
                isValid = true;
            } else {
                screen.show(String.format("Nome %s já em uso", name));
            }
        }
        return name;
    }

    public Double getValidPrice(Screen screen) {
        boolean isValid = false;
        Double price = 0.00;
        while(!isValid) {
            try {
                String input;

                if(this.price != null) {
                    input = screen.getInputString(String.format("Price (%s): ", this.price));
                    if(input.isBlank()) {
                        price = this.price;
                    } else {
                        price = Double.parseDouble(input);
                    }
                } else {
                    input = screen.getInputString("Preço: ");
                    price = Double.parseDouble(input);
                }

                if(price > 0) {
                    isValid = true;
                } else {
                    screen.show("Preço deve ser maior que zero. Preencha novamente.");
                }
            } catch (Exception e) {
                screen.show("Somente números separados com ponto são válidos. Preencha novamente.");
            }

        }
        return price;
    }

    public String getValidUnit(Screen screen) {
        boolean isValid = false;
        List<String> validUnits = Arrays.asList("kilo", "grama", "litro", "mililitro", "unidade" );
        String unit = null;
        while(!isValid) {
            if(this.unit != null) {
                unit = screen.getInputString(String.format("Unidade de medida (%s): ", this.unit));
                if(unit.isBlank()) {
                    unit = this.unit;
                }
            } else {
                unit = screen.getInputString("Unidade de medida: ");
            }

            if(validUnits.contains(unit)) {
                isValid = true;
            } else {
                screen.show("Opa, só essas unidades são válidas " + validUnits);
            }
        }
        return unit;
    }

    public int getValidQuantity(Screen screen) {
        boolean isValid = false;
        int quantity = 0;
        while(!isValid) {
            try {
                String input;
                if(this.unit != null) {
                    input = screen.getInputString(String.format("Quantidade (%d): ", this.quantity));
                    if(input.isBlank()) {
                        quantity = this.quantity;
                    } else {
                        quantity = Integer.parseInt(input);
                    }
                } else {
                    input = screen.getInputString("Quantidade: ");
                    quantity = Integer.parseInt(input);
                }

                if(quantity >= 0) {
                    isValid = true;
                } else {
                    screen.show("A quantidade deve ser igual ou maior que zero.");
                }
            } catch (Exception e) {
                screen.show("A quantidade deve ser númerica.");
            }


        }
        return quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tNOME: " + this.name + "\n" +
                "\tPREÇO: " + this.price + "\n" +
                "\tUNIDADE: " + this.unit + "\n" +
                "\tQUANTIDADE: " + this.quantity +
                "\n}";
    }
}
