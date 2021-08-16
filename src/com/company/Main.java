package com.company;

import com.company.inventory.Inventory;
import com.company.product.Product;
import com.company.utils.Screen.Screen;

import java.util.List;

public class Main {
    private static final Screen screen = new Screen();
    private static final Inventory inventory = new Inventory();

    public static void main(String[] args) {
        boolean keep = true;

        do {
            try {
                screen.showScreen("main");
                int option = screen.getInput();
                handleMainMenuOption(option);

            } catch (Exception e) {
                screen.validation.setMessage("Opção inválida, tente novamente.");
            }

        } while (keep);
    }

    private static void handleReportOptions(int option) {
        switch (option) {
            case 0 -> handleMainMenuOption(-1);
            case 1 -> {
                boolean keepOne = true;
                while (keepOne) {
                    List<Product> products = inventory.findAll();
                    if(products.size() > 0) {
                        products.forEach(System.out::println);

                        if (screen.getRepeatOperation() == 0) {
                            keepOne = false;
                            handleMainMenuOption(4);
                        }
                    } else {
                        screen.validation.setMessage("Nenhum produto cadastrado.");
                        keepOne = false;
                        handleMainMenuOption(4);
                    }
                }
                break;
            }
            default -> {
                screen.show(String.format("Opção %d inválida", option));
                System.exit(1);
            }

        }
    }

    private static void handlePriceOptions(int option) {
        switch (option) {
            case 0 -> handleMainMenuOption(-1);
            case 1 -> {
                boolean keepOne = true;
                while (keepOne) {
                    screen.showScreen("price-raise");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product product = inventory.findOne(name);

                    if (product != null) {
                        screen.show(product.toString());
                        screen.show("Preço atual: " + product.getPrice());

                        double price = 0.0;
                        do {
                            try {
                                price = screen.getInputFloat("Porcentagem de aumento (ex: 1 para 1%): ");
                                if(price <= 0) {
                                    screen.show("O valor deve ser maior que zero.");
                                }
                            } catch (Exception e) {
                                screen.show("Entrada deve ser numérica.");
                            }
                        } while (price <= 0);

                        screen.show("Preço final: " + (product.getPrice() + (product.getPrice() * (price / 100))));

                        if (screen.getInputConfirmation() == 1) {
                            inventory.raisePrice(product, price);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepOne = false;
                        handleMainMenuOption(3);
                    }
                }
            }
            case 2 -> {
                boolean keepTwo = true;
                while (keepTwo) {
                    screen.showScreen("price-lower");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product product = inventory.findOne(name);

                    if (product != null) {
                        screen.show(product.toString());
                        screen.show("Preço atual: " + product.getPrice());

                        Double price = 0.0;
                        do {
                            try {
                                price = screen.getInputFloat("Porcentagem de redução (ex: 1 para 1%): ");
                                if (price <= 0) {
                                    screen.show("Não utilize números negativos ou zero.");
                                } else if (price > 100) {
                                    screen.show("A redução não pode ser maior que o 100%");
                                }
                            } catch (Exception e) {
                                screen.show("A entrada deve ser numérica");
                            }

                        } while(price <= 0 || price > 100);

                        screen.show("Preço final: " + (product.getPrice() - (product.getPrice() * (price / 100))));

                        if (screen.getInputConfirmation() == 1) {
                            inventory.lowerPrice(product, price);
                        }

                    } else {
                        screen.show("Produto não encontrado.");
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepTwo = false;
                        handleMainMenuOption(3);
                    }
                }
            }
            default -> {
                screen.show(String.format("Opção %d inválida", option));
                System.exit(1);
            }
        }
    }

    private static void handleInventoryOption(int option) {
        switch (option) {
            case 0 -> handleMainMenuOption(-1);
            case 1 -> {
                boolean keepOne = true;
                while (keepOne) {
                    screen.showScreen("inventory-in");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product product = inventory.findOne(name);

                    if (product != null) {
                        screen.show(product.toString());
                        screen.show("Quantidade atual: " + product.getQuantity());
                        int quantity = screen.getInputInt("Quantidade de entrada: ");
                        while (quantity <= 0) {
                            screen.show("Quantidade deve ser maior que zero.");
                            quantity = screen.getInputInt("Quantidade de entrada: ");
                        }

                        screen.show("Quantidade final: " + (product.getQuantity() + quantity));

                        if (screen.getInputConfirmation() == 1) {
                            inventory.raiseQuantity(product, quantity);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepOne = false;
                        handleMainMenuOption(2);
                    }
                }
            }
            case 2 -> {
                boolean keepTwo = true;
                while (keepTwo) {
                    screen.showScreen("inventory-out");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product product = inventory.findOne(name);

                    if (product != null && product.getQuantity() > 0) {
                        screen.show(product.toString());
                        screen.show("Quantidade atual: " + product.getQuantity());
                        int quantity = screen.getInputInt("Quantidade de saída: ");
                        while (quantity <= 0 || quantity > product.getQuantity()) {
                            screen.show(
                                quantity <= 0 ?
                                    "Não utilize números negativos ou zero." :
                                    "A saída não pode ser maior que a quantidade em estoque."
                            );
                            quantity = screen.getInputInt("Quantidade de saída: ");
                        }

                        screen.show("Quantidade final: " + (product.getQuantity() - quantity));

                        if (screen.getInputConfirmation() == 1) {
                            inventory.lowerQuantity(product, quantity);
                        }
                    } else {
                        if (product == null) {
                            screen.show("Produto não encontrado.");
                        } else {
                            screen.show(String.format("Produto %s está sem estoque.", product.getName()));
                        }
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepTwo = false;
                        handleMainMenuOption(2);
                    }
                }
            }
            default -> {
                screen.show(String.format("Opção %d inválida", option));
                System.exit(1);
            }
        }
    }

    private static void handleProductOption(int option) {
        switch (option) {
            case 0 -> handleMainMenuOption(-1);
            case 1 -> {
                boolean keepOne = true;
                while (keepOne) {
                    screen.showScreen("products-create");
                    Product product = new Product(inventory).builder(screen);

                    if (screen.getInputConfirmation() == 1) {
                        inventory.create(product);

                        if (screen.getRepeatOperation() == 0) {
                            keepOne = false;
                            handleMainMenuOption(1);
                        }
                    }
                }
            }
            case 2 -> {
                boolean keepTwo = true;
                while (keepTwo) {
                    screen.showScreen("products-update");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product oldProduct = inventory.findOne(name);

                    if (oldProduct != null) {
                        Product newProduct = oldProduct.update(screen);
                        if (screen.getInputConfirmation() == 1) {
                            inventory.update(newProduct, oldProduct);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepTwo = false;
                        handleMainMenuOption(1);
                    }
                }
            }
            case 3 -> {
                boolean keepThree = true;
                while (keepThree) {
                    screen.showScreen("products-read");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product product = inventory.findOne(name);
                    if (product != null) {
                        screen.show(product.toString());
                    } else {
                        screen.show("Produto não encontrado.");
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepThree = false;
                        handleMainMenuOption(1);
                    }
                }
            }
            case 4 -> {
                boolean keepFour = true;
                while (keepFour) {
                    screen.showScreen("products-delete");
                    String name = screen.getInputString("Digite o nome do produto: ");
                    Product product = inventory.findOne(name);
                    if (product != null) {
                        screen.show(product.toString());
                        if (screen.getInputConfirmation() == 1) {
                            inventory.delete(name);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }

                    if (screen.getRepeatOperation() == 0) {
                        keepFour = false;
                        handleMainMenuOption(1);
                    }
                }
            }
            default -> {
                screen.show(String.format("Opção %d inválida", option));
                System.exit(1);
            }
        }
    }

    private static void handleMainMenuOption(int mainMenuOption) {

        switch (mainMenuOption) {
            case -1:
                break;
            case 0:
                System.exit(0);
            case 1:
                boolean keepOne = true;
                do {
                    try {
                        screen.showScreen("products");
                        int optionOne = screen.getInput();
                        handleProductOption(optionOne);
                        keepOne = false;
                    } catch (Exception e) {
                        screen.validation.setMessage("Opção inválida, tente novamente.");
                    }
                } while (keepOne);

                break;
            case 2:
                boolean keepTwo = true;
                do {
                    try {
                        screen.showScreen("inventory");
                        int optionTwo = screen.getInput();
                        handleInventoryOption(optionTwo);
                        keepTwo = false;
                    } catch (Exception e) {
                        screen.validation.setMessage("Opção inválida, tente novamente.");
                    }
                } while (keepTwo);

                break;
            case 3:
                boolean keepThree = true;
                do {
                    try {
                        screen.showScreen("price");
                        int optionThree = screen.getInput();
                        handlePriceOptions(optionThree);
                        keepThree = false;
                    } catch (Exception e) {
                        screen.validation.setMessage("Opção inválida, tente novamente.");
                    }
                } while (keepThree);

                break;
            case 4:
                boolean keepFour = true;
                do {
                    try {
                        screen.showScreen("report");
                        int optionFour = screen.getInput();
                        handleReportOptions(optionFour);
                        keepFour = false;
                    } catch (Exception e) {
                        screen.validation.setMessage("Opção inválida, tente novamente.");
                    }
                } while (keepFour);

                break;
            default:
                screen.show(String.format("Opção %d inválida", mainMenuOption));
                System.exit(1);

        }
    }
}
