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



    //Este método se conecta con el service mediante registrarCurso para lograr registrar un curso con datos pedidos mediante I/O
    private void registrar() {
        System.out.println("\n--- REGISTRAR NUEVO CURSO ---");
        System.out.print("Ingrese código del curso: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nombre del curso: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese número de créditos: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido para los créditos:");
            scanner.next();
        }
        int creditos = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        servicio.registrarCurso(codigo, nombre, creditos);
    }

    //Método que imprimirá todos los cursos que están dentro de la lista en RAM, estos previamente fueron traidos del Json por el service que se llama en este método
    private void listar() {
        List<Curso> cursos = servicio.listarCursos();
        System.out.println("\n--- LISTA DE CURSOS REGISTRADOS ---");
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados en el sistema.");
        } else {
            for (int i = 0; i < cursos.size(); i++) {
                Curso c = cursos.get(i);
                System.out.println((i + 1) + ". Código: " + c.getCodigo() +
                        " | Nombre: " + c.getNombre() +
                        " | Créditos: " + c.getCreditos());
            }
        }
    }

    //Método para actualizar los datos de un curso, usaremos I/O para pedir los datos a actualizar del curso(identificado por código), este también usa el service para usar Json y modificar los datos del curso elegido.
    private void actualizar() {
        System.out.println("\n--- ACTUALIZAR CURSO ---");
        System.out.print("Ingrese el código del curso que desea actualizar: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nuevos créditos: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Ingrese un número válido para los créditos:");
            scanner.next();
        }
        int creditos = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        servicio.actualizarCurso(codigo, nombre, creditos);
    }

    private void eliminar() {
    }
}