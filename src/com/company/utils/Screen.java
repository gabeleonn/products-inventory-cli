package com.company.utils;

import java.util.Locale;
import java.util.Scanner;

public class Screen implements IScreen {
    private Scanner read = new Scanner(System.in);

    public void showScreen(String screen) {
        this.showHeader();
        switch (screen) {
            case "products":
                show("CADASTRO DE PRODUTOS\n");
                show(
                    "1 - INCLUSÃO\n" +
                    "2 - ALTERAÇÃO\n" +
                    "3 - CONSULTA\n" +
                    "4 - EXCLUSÃO\n" +
                    "0 - RETORNAR"
                );
                break;
            case "products-create":
                show("CADASTRO DE PRODUTOS - INCLUSÃO DE PRODUTO\n");
                break;
            case "products-read":
                show("CONSULTA DE PRODUTO\n");
                break;
            case "products-update":
                show("ALTERAÇÃO DE PRODUTO\n");
                break;
            case "products-delete":
                show("EXCLUSÃO DE PRODUTO\n");
                break;
            case "inventory":
                show("MOVIMENTAÇÃO\n");
                show(
                    "1 - ENTRADA\n" +
                    "2 - SAÍDA\n" +
                    "0 - RETORNAR"
                );
                break;
            case "inventory-in":
                show("ENTRADA DE PRODUTO\n");
                break;
            case "inventory-out":
                show("SAÍDA DE PRODUTO\n");
                break;
            case "price":
                show("AJUSTE DE PREÇO\n");
                show(
                        "1 - AUMENTO\n" +
                        "2 - REDUÇÃO\n" +
                        "0 - RETORNAR"
                );
                break;
            case "price-raise":
                show("AUMENTO DE PREÇO\n");
                break;
            case "price-lower":
                show("REDUÇÃO DE PREÇO\n");
                break;
            case "report":
                show("RELATÓRIOS\n");
                show(
                        "1 - PRINCIPAL\n" +
                        "0 - RETORNAR"
                );
                break;
            case "report-main":
                show("RELATÓRIO PRINCIPAL\n");
                break;
            default:
                show("MENU PRINCIPAL\n");
                show(
                    "1 - CADASTRO DE PRODUTOS\n" +
                    "2 - MOVIMENTAÇÃO\n" +
                    "3 - REAJUSTE DE PREÇOS\n" +
                    "4 - RELATÓRIOS\n" +
                    "0 - FINALIZAR"
                );
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

    public int getInputConfirmation() {
        this.clearScanner();
        showOptionStatement("Deseja confirmar? (S/N): ");
        char repeat = this.read.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        if (repeat == 's') {
            return 1;
        } else if(repeat == 'n') {
            return 0;
        } else {
            return -1;
        }
    }

    public int getRepeatOperation() {
        this.clearScanner();
        showOptionStatement("Deseja repetir operação? (S/N): ");
        char repeat = this.read.nextLine().toLowerCase(Locale.ROOT).charAt(0);
        if (repeat == 's') {
            return 1;
        } else if(repeat == 'n') {
            return 0;
        } else {
            return -1;
        }
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
