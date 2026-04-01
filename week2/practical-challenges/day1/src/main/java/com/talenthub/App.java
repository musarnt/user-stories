package com.talenthub;

public class App {
    public static void main(String[] args) {

        MenuPrincipal menu = new MenuPrincipal();
        menu.mostrarOpcionModerno(3);

        System.out.println(menu.getSalaryCategory(7001));
    }
}