package org.example.presentacion;

import org.example.business.Curso;
import org.example.business.CursoService;

import java.util.List;
import java.util.Scanner;

public class CursoUI {
    //Para el uso de la capa de negocio
    private final CursoService servicio;
    //Para uso de I/O
    private final Scanner scanner;



    public CursoUI() {
        this.servicio = new CursoService();
        this.scanner = new Scanner(System.in);
    }



    //Implementación de un meno por consola para seleccionar las opciones de gestión de cursos, estos métodos se conectan con el service
    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE CURSOS ---");
            System.out.println("1. Registrar Curso");
            System.out.println("2. Listar Cursos");
            System.out.println("3. Actualizar Curso");
            System.out.println("4. Eliminar Curso");
            System.out.println("5. Regresar al Menú Principal");
            System.out.print("Elija una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                scanner.next();
            }
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1 -> registrar();
                case 2 -> listar();
                case 3 -> actualizar();
                case 4 -> eliminar();
                case 5 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }



    //Métodos que deben implementarse
    private void registrar() {
    }

    private void listar() {
    }

    private void actualizar() {
    }

    private void eliminar() {
    }
}