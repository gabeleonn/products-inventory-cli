package com.company;

import com.company.inventory.Inventory;
import com.company.product.Product;
import com.company.utils.Screen;

public class Main {
    private static final Screen screen = new Screen();
    private static final Inventory inventory = new Inventory();

    public static void main(String[] args) {
        while (true) {
            screen.showScreen("main");
            int option = screen.getInput();
            handleMainMenuOption(option);
        }
    }

    private static void handleInventoryOption(int option) {
        switch (option) {
            case 0:
                // BACK TO MAIN
                break;
            case 1:
                // TODO IN

                break;
            case 2:
                // TODO OUT
            default:
                screen.show(String.format("Opção %d inválida", option));
                System.exit(1);
        }
    }

    private static void handleProductOption(int option) {
        switch (option) {
            case 0 -> handleMainMenuOption(-1);
            case 1 -> {
                boolean keepOne = true;
                while (keepOne) {
                    screen.showScreen("products-create");
                    Product product = new Product().builder(screen);
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
                            inventory.update(oldProduct, newProduct);
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
                int option = screen.getInput();
                handleProductOption(option);
                break;
            case 2:

                break;
            case 3:
                // TODO show price readjustment
                screen.show(Integer.toString(mainMenuOption));
                break;
            case 4:
                // TODO show report
                screen.show(Integer.toString(mainMenuOption));
                break;
            default:
                screen.show(String.format("Opção %d inválida", mainMenuOption));
                System.exit(1);

        }
    }
}
