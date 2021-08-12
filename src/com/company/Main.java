package com.company;

import com.company.inventory.Inventory;
import com.company.product.Product;
import com.company.utils.Screen;

import java.util.List;

public class Main {
    private static final Screen screen = new Screen();
    private static final Inventory inventory = new Inventory();

    public static void main(String[] args) {
        try {
            while (true) {
                screen.showScreen("main");
                int option = screen.getInput();
                handleMainMenuOption(option);
            }
        } catch (Exception e) {
            screen.show("Opa, parece que algo ocorreu de errado. Recomece o sistema.");
        }
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
                    } else {
                        screen.show("Nenhum produto cadastrado.");
                    }

                    // It makes no sense to ask to repeat
                    // however is better than just skipping ahead.

                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepOne = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(4);
                    } else if (repeat == 0) {
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
                        double price = screen.getInputFloat("Porcentagem de aumento (ex: 1 para 1%): ");
                        while (price <= 0) {
                            screen.show("Quantidade deve ser maior que zero.");
                            price = screen.getInputFloat("Porcentagem de aumento (ex: 1 para 1%): ");
                        }

                        screen.show("Preço final: " + (product.getPrice() + (product.getPrice() * (price / 100))));

                        int confirm = screen.getInputConfirmation();
                        if (confirm == 1) {
                            inventory.raisePrice(product, price);
                        } else if (confirm == -1) {
                            keepOne = false;
                            screen.show("Opção inválida. Voltando ao menu.");
                            handleMainMenuOption(3);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepOne = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(3);
                    } else if (repeat == 0) {
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
                        screen.show("Preço atual: " + product.getQuantity());
                        Double price = screen.getInputFloat("Porcentagem de aumento (ex: 1 para 1%): ");
                        while (price <= 0) {
                            screen.show("Não utilize números negativos ou zero.");
                            price = screen.getInputFloat("Porcentagem de aumento (ex: 1 para 1%): ");
                        }

                        screen.show("Preço final: " + (product.getPrice() - (product.getPrice() * (price / 100))));

                        int confirm = screen.getInputConfirmation();
                        if (confirm == 1) {
                            inventory.lowerPrice(product, price);
                        } else if (confirm == -1) {
                            keepTwo = false;
                            screen.show("Opção inválida. Voltando ao menu.");
                            handleMainMenuOption(3);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepTwo = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(3);
                    } else if (repeat == 0) {
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

                        int confirm = screen.getInputConfirmation();
                        if (confirm == 1) {
                            inventory.raiseQuantity(product, quantity);
                        } else if (confirm == -1) {
                            keepOne = false;
                            screen.show("Opção inválida. Voltando ao menu.");
                            handleMainMenuOption(2);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepOne = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(2);
                    } else if (repeat == 0) {
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

                    if (product != null) {
                        screen.show(product.toString());
                        screen.show("Quantidade atual: " + product.getQuantity());
                        int quantity = screen.getInputInt("Quantidade de saída: ");
                        while (quantity <= 0) {
                            screen.show("Não utilize números negativos ou zero.");
                            quantity = screen.getInputInt("Quantidade de saída: ");
                        }

                        screen.show("Quantidade final: " + (product.getQuantity() + quantity));

                        int confirm = screen.getInputConfirmation();
                        if (confirm == 1) {
                            inventory.lowerQuantity(product, quantity);
                        } else if (confirm == -1) {
                            keepTwo = false;
                            screen.show("Opção inválida. Voltando ao menu.");
                            handleMainMenuOption(2);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepTwo = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(2);
                    } else if (repeat == 0) {
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
                    int confirm = screen.getInputConfirmation();
                    if (confirm == 1) {
                        inventory.create(product);
                        int repeat = screen.getRepeatOperation();
                        if (repeat == -1) {
                            keepOne = false;
                            screen.show("Opção inválida. Voltando ao menu.");
                            handleMainMenuOption(1);
                        } else if (repeat == 0) {
                            keepOne = false;
                            handleMainMenuOption(1);
                        }
                    } else if (confirm == -1) {
                        keepOne = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(1);
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
                        int confirm = screen.getInputConfirmation();
                        if (confirm == 1) {
                            inventory.update(newProduct, oldProduct);
                        } else if (confirm == -1) {
                            keepTwo = false;
                            screen.show("Opção inválida. Voltando ao menu.");
                            handleMainMenuOption(1);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepTwo = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(1);
                    } else if (repeat == 0) {
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
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepThree = false;
                        screen.show("Opção inválida. Voltando ao menu principal.");
                        handleMainMenuOption(1);
                    } else if (repeat == 0) {
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
                        int confirm = screen.getInputConfirmation();
                        if (confirm == 1) {
                            inventory.delete(name);
                        } else if (confirm == -1) {
                            keepFour = false;
                            screen.show("Opção inválida. Voltando ao menu principal.");
                            handleMainMenuOption(1);
                        }
                    } else {
                        screen.show("Produto não encontrado.");
                    }
                    int repeat = screen.getRepeatOperation();
                    if (repeat == -1) {
                        keepFour = false;
                        screen.show("Opção inválida. Voltando ao menu.");
                        handleMainMenuOption(1);
                    } else if (repeat == 0) {
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
                screen.showScreen("products");
                int optionOne = screen.getInput();
                handleProductOption(optionOne);
                break;
            case 2:
                screen.showScreen("inventory");
                int optionTwo = screen.getInput();
                handleInventoryOption(optionTwo);
                break;
            case 3:
                screen.showScreen("price");
                int optionThree = screen.getInput();
                handlePriceOptions(optionThree);
                break;
            case 4:

                screen.showScreen("report");
                int optionFour = screen.getInput();
                handleReportOptions(optionFour);
                break;
            default:
                screen.show(String.format("Opção %d inválida", mainMenuOption));
                System.exit(1);

        }
    }
}
