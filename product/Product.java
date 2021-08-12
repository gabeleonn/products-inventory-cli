package com.company.product;

import com.company.utils.Screen;

import java.math.BigDecimal;
import java.util.*;

public class Product implements IProduct {
    private String name;
    private BigDecimal price;
    private String unit;
    private int quantity;

    public Product() {
    }

    public Product builder(Screen screen) {
        this.name = this.getValidName(screen);
        this.price = this.getValidPrice(screen);
        this.unit = this.getValidUnit(screen);
        this.quantity = this.getValidQuantity(screen);
        return this;
    }

    private String getValidName(Screen screen) {
        boolean isValid = false;
        String name = "";
        while(!isValid) {
            name = screen.getInputString("Nome: ");
            // TODO Validate unique field
            isValid = true;
        }
        return name;
    }

    private BigDecimal getValidPrice(Screen screen) {
        boolean isValid = false;
        Double price = 0.00;
        while(!isValid) {
            try {
                price = screen.getInputFloat("Preço: ");
                if(price > 0) {
                    isValid = true;
                } else {
                    screen.show("Preço deve ser maior que zero. Preencha novamente.");
                }
            } catch (InputMismatchException e) {
                screen.show("Somente números separados com vírgula são válidos. Preencha novamente.");
                continue;
            }

        }
        return new BigDecimal(price);
    }

    private String getValidUnit(Screen screen) {
        boolean isValid = false;
        List<String> validUnits = Arrays.asList("kilo", "grama", "litro", "mililitro", "unidade" );
        String unit = null;
        while(!isValid) {
            unit = screen.getInputString("Unidade de medida: ");

            if(validUnits.contains(unit)) {
                isValid = true;
            } else {
                screen.show("Opa, só essas unidades são válidas " + validUnits.toString());
            }
        }
        return unit;
    }

    private int getValidQuantity(Screen screen) {
        boolean isValid = false;
        int quantity = 0;
        while(!isValid) {
            quantity = screen.getInputInt("Quantidade: ");
            if(quantity >= 0) {
                isValid = true;
            } else {
                screen.show("A quantidade deve ser igual ou maior que zero.");
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = new BigDecimal(price);
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
        return "{\n\n" +
                "\tNOME: " + this.name + "\n" +
                "\tPREÇO: " + this.price + "\n" +
                "\tUNIDADE: " + this.unit + "\n" +
                "\tQUANTIDADE:" + this.quantity + "\n" +
                "\n}";
    }
}
