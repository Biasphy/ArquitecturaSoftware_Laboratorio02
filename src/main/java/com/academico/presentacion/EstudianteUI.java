package com.academico.presentacion;

import com.academico.domain.model.Estudiante;
import com.academico.application.EstudianteService;

import java.util.List;
import java.util.Scanner;

public class EstudianteUI {
    private final EstudianteService servicio;
    private final Scanner scanner;

    public EstudianteUI() {
        this.servicio = new EstudianteService();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ESTUDIANTES ---");
            System.out.println("1. Registrar Estudiante");
            System.out.println("2. Listar Estudiantes");
            System.out.println("3. Actualizar Estudiante");
            System.out.println("4. Eliminar Estudiante");
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

    private void registrar() {
        System.out.println("\n--- REGISTRAR NUEVO ESTUDIANTE ---");
        System.out.print("Ingrese código: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese edad: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido para la edad:");
            scanner.next();
        }
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        servicio.registrarEstudiante(codigo, nombre, apellido, edad);
    }

    private void listar() {
        List<Estudiante> estudiantes = servicio.listarEstudiantes();
        System.out.println("\n--- LISTA DE ESTUDIANTES REGISTRADOS ---");
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados en el sistema.");
        } else {
            for (int i = 0; i < estudiantes.size(); i++) {
                Estudiante e = estudiantes.get(i);
                System.out.println((i + 1) + ". Código: " + e.getCodigo() +
                        " | Nombre: " + e.getNombre() +
                        " | Apellido: " + e.getApellido() +
                        " | Edad: " + e.getEdad());
            }
        }
    }

    private void actualizar() {
        System.out.println("\n--- ACTUALIZAR ESTUDIANTE ---");
        System.out.print("Ingrese el código del estudiante que desea actualizar: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nuevo apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese nueva edad: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido para la edad:");
            scanner.next();
        }
        int edad = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        servicio.actualizarEstudiante(codigo, nombre, apellido, edad);
    }

    private void eliminar() {
        System.out.println("\n--- ELIMINAR ESTUDIANTE ---");
        System.out.print("Ingrese el código del estudiante que desea eliminar: ");
        String codigo = scanner.nextLine();
        servicio.eliminarEstudiante(codigo);
    }
}