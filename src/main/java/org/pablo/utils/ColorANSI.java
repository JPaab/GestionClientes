package org.pablo.utils;


// Cree una clase guardada en un package distinto a los demas, para manejar el tema de los colores, sin ensuciar el Main.
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

    public String pintar(String texto) {
        return color + texto + RESET.color;
    }
}
