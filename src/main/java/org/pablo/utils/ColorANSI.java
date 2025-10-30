package org.pablo.utils;

public enum ColorANSI {
    RESET("\u001B[0m"),
    ROJO("\u001B[31m"),
    VERDE("\u001B[32m"),
    AMARILLO("\u001B[33m"),
    AZUL("\u001B[34m");

    private final String color;
    ColorANSI(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String pintar(String texto) {
        return color + texto + RESET.color;
    }
}
