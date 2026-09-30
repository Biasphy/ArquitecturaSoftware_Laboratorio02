package org.example;

import org.example.presentacion.EstudianteUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstudianteUI estudianteUI = new EstudianteUI();

        int opcion;
        do {
            System.out.println("\n=====================================");
            System.out.println("   SISTEMA DE GESTIÓN ACADÉMICA      ");
            System.out.println("=====================================");
            System.out.println("1. Gestionar Estudiantes");
            System.out.println("2. Gestionar Cursos");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> estudianteUI.mostrarMenu();
                case 2 -> System.out.println("\n[Aviso] WIP.");
                case 3 -> System.out.println("\nSaliendo del sistema, cerrando Skynet.");
                default -> System.out.println("\nOpción inválida. Intente de nuevo.");
            }
        } while (opcion != 3);

        scanner.close();
    }
}