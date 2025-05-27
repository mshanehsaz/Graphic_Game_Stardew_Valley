package com.tilldawn.Model.enums;

public enum KeyboardController {
    Up("W"),
    Down("S"),
    Right("D"),
    Left("A"),
    Reload("R"),
    Shot("Q");

    private String aChar;

    KeyboardController(String aChar) {
        this.aChar = aChar;
    }

    public String getaChar() {
        return aChar;
    }

    public void setAChar(String aChar) {
        this.aChar = aChar;
    }
}
