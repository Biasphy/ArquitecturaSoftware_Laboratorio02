package org.example.business;

import org.example.data.CursoRepository;
import java.util.List;

public class CursoService {
    private final CursoRepository repository;

    public CursoService() {
        this.repository = new CursoRepository();
    }



    // Obtener todos los cursos desde CursoRepository
    public List<Curso> listarCursos() {
        return repository.obtenerTodos();
    }

    // Registrar un nuevo curso con validaciones de negocio, como que el código insertado no sea vacío así como el nombre, que los créditos no sean un número negativo y que no exista el código, solo si todo se cumple se retorna un true para decir que se paso las validaciones, si algo falla se retorna false
    public boolean registrarCurso(String codigo, String nombre, int creditos) {
        if (codigo == null || codigo.trim().isEmpty() || nombre == null || nombre.trim().isEmpty()) {
            System.out.println("Error de validación: El código y el nombre del curso son obligatorios.");
            return false;
        }

        if (creditos <= 0) {
            System.out.println("Error de validación: Los créditos deben ser mayores a cero.");
            return false;
        }

        // Validar que el código no exista previamente
        List<Curso> existentes = repository.obtenerTodos();
        for (Curso c : existentes) {
            if (c.getCodigo().equalsIgnoreCase(codigo)) {
                System.out.println("Error de negocio: Ya existe un curso registrado con el código " + codigo);
                return false;
            }
        }

        Curso nuevo = new Curso(codigo, nombre, creditos);
        repository.insertar(nuevo);
        System.out.println("¡Curso registrado exitosamente!");
        return true;
    }
}