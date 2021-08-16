package com.company.utils.Screen;

public interface IScreen {

    void showScreen(String screen);

    void showHeader();

    int getInput();

    int getYesOrNoInput(String message);

    int getInputConfirmation();

    int getRepeatOperation();

    int getInputInt(String statement);

    String getInputString(String statement);

    Double getInputFloat(String statement);

    void show(String message);
}
