package com.company.utils.Screen;

import com.company.utils.Validations.Validation;
import java.util.Scanner;

public class Screen implements IScreen {
    private Scanner read;
    public Validation validation;

    public Screen() {}

    public Screen(Validation validations, Scanner read) {
        this.validation = validations;
        this.read = read;
    }

    public void showScreen(String screen) {
        this.showHeader();
        switch (screen) {
            case "products" -> {
                show("CADASTRO DE PRODUTOS\n");
                show(
                        """
                        1 - INCLUSÃO
                        2 - ALTERAÇÃO
                        3 - CONSULTA
                        4 - EXCLUSÃO
                        0 - RETORNAR
                        """
                );
            }
            case "products-create" -> show("CADASTRO DE PRODUTOS - INCLUSÃO DE PRODUTO\n");
            case "products-read" -> show("CONSULTA DE PRODUTO\n");
            case "products-update" -> show("ALTERAÇÃO DE PRODUTO\n");
            case "products-delete" -> show("EXCLUSÃO DE PRODUTO\n");
            case "inventory" -> {
                show("MOVIMENTAÇÃO\n");
                show(
                        """
                        1 - ENTRADA
                        2 - SAÍDA
                        0 - RETORNAR
                        """
                );
            }
            case "inventory-in" -> show("ENTRADA DE PRODUTO\n");
            case "inventory-out" -> show("SAÍDA DE PRODUTO\n");
            case "price" -> {
                show("AJUSTE DE PREÇO\n");
                show(
                        """
                        1 - AUMENTO
                        2 - REDUÇÃO
                        0 - RETORNAR
                        """
                );
            }
            case "price-raise" -> show("AUMENTO DE PREÇO\n");
            case "price-lower" -> show("REDUÇÃO DE PREÇO\n");
            case "report" -> {
                show("RELATÓRIOS\n");
                show(
                    "1 - PRINCIPAL\n" +
                    "0 - RETORNAR"
                );
            }
            case "report-main" -> show("RELATÓRIO PRINCIPAL\n");
            default -> {
                show("MENU PRINCIPAL\n");
                show(
                        """
                        1 - CADASTRO DE PRODUTOS
                        2 - MOVIMENTAÇÃO
                        3 - REAJUSTE DE PREÇOS
                        4 - RELATÓRIOS
                        0 - FINALIZAR
                        """
                );
            }
        }
        if(this.validation.hasValidationMessage()) {
            this.show(this.validation.getMessage());
        }
    }

    public void showHeader() {
        show("\n--------------------------------------------------");
        show("\t\tEMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.");
        show("\t\t\tSISTEMA DE CONTROLE DE ESTOQUE");
        show("--------------------------------------------------\n");
    }

    public int getInput() {
        this.clearScanner();
        showOptionStatement("Opção: ");
        return this.read.nextInt();
    }

    public int getYesOrNoInput(String message) {
        boolean isValid = false;
        char repeat = 0;
        while(!isValid){
            try {
                this.clearScanner();
                showOptionStatement(message);
                String input = this.read.nextLine().toLowerCase();
                repeat = input.charAt(0);

                if(
                        input.length() != 1 ||
                                (repeat != 's' && repeat != 'n')
                ) {
                    this.show("Opa, entrada inválida, tente novamente.");
                } else {
                    isValid = true;
                }

            } catch (Exception e) {
                this.show("Opa, entrada inválida, tente novamente.");
            }
        }

        return repeat == 's' ? 1 : 0;
    }

    public int getInputConfirmation() {
        return this.getYesOrNoInput("Deseja confirmar? (S/N): ");
    }

    public int getRepeatOperation() {
        return this.getYesOrNoInput("Deseja repetir operação? (S/N): ");
    }

    public int getInputInt(String statement) {
        this.clearScanner();
        showOptionStatement(statement);
        return this.read.nextInt();
    }

    public String getInputString(String statement) {
        this.clearScanner();
        showOptionStatement(statement);
        return this.read.nextLine();
    }

    public Double getInputFloat(String statement) {
        this.clearScanner();
        showOptionStatement(statement);
        return this.read.nextDouble();
    }

    private void clearScanner() {
        this.read = null;
        this.read = new Scanner(System.in);
    }

    private void showOptionStatement(String message) {
        System.out.print(message);
    }

    public void show(String message) {
        System.out.println(message);
    }
}
