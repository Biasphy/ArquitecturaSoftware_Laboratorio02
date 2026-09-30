package org.example.business;

import org.example.data.EstudianteRepository;
import java.util.List;

public class EstudianteService {
    private final EstudianteRepository repository;

    public EstudianteService() {
        this.repository = new EstudianteRepository();
    }

    // Obtener todos los estudiantes
    public List<Estudiante> listarEstudiantes() {
        return repository.obtenerTodos();
    }

    // Registrar un nuevo estudiante con validaciones de negocio
    public boolean registrarEstudiante(String codigo, String nombre, String apellido, int edad) {
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error de validación: El código y el nombre son obligatorios.");
            return false;
        }

        if (edad <= 0) {
            System.out.println("Error de validación: La edad debe ser mayor a cero.");
            return false;
        }

        // Validar que el código no exista previamente
        List<Estudiante> existentes = repository.obtenerTodos();
        for (Estudiante e : existentes) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Error de negocio: Ya existe un estudiante registrado con el código " + codigo);
                return false;
            }
        }

        Estudiante nuevo = new Estudiante(codigo, nombre, apellido, edad);
        repository.insertar(nuevo);
        System.out.println("¡Estudiante registrado exitosamente!");
        return true;
    }

    // Actualizar un estudiante existente
    public boolean actualizarEstudiante(String codigo, String nombre, String apellido, int edad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error de validación: El nombre no puede estar vacío.");
            return false;
        }

        Estudiante estudianteActualizado = new Estudiante(codigo, nombre, apellido, edad);
        boolean actualizado = repository.actualizar(estudianteActualizado);

        if (actualizado) {
            System.out.println("¡Estudiante actualizado correctamente!");
        } else {
            System.out.println("No se encontró un estudiante con el código " + codigo);
        }
        return actualizado;
    }

    // Eliminar un estudiante por código
    public boolean eliminarEstudiante(String codigo) {
        boolean eliminado = repository.eliminar(codigo);
        if (eliminado) {
            System.out.println("¡Estudiante eliminado correctamente!");
        } else {
            System.out.println("No se encontró un estudiante con el código " + codigo);
        }
        return eliminado;
    }
}