package com.company.utils;

public interface IScreen {

    public void showScreen(String screen);

    public void showHeader();

    public int getInput();

    public int getInputConfirmation();

    public int getRepeatOperation();

    public int getInputInt(String statement);

    public String getInputString(String statement);

    public Double getInputFloat(String statement);

    public void show(String message);
}
