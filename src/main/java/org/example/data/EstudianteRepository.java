package org.example.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.business.Estudiante;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepository {
    private final String RUTA_ARCHIVO = "data/Estudiantes.json";
    private final Gson gson;

    public EstudianteRepository() {
        //Gson con pretty printing para que el JSON sea legible en texto
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        verificarYCrearArchivo();
    }

    // Asegura que la carpeta y el archivo JSON existan para evitar errores de lectura
    private void verificarYCrearArchivo() {
        try {
            File archivo = new File(RUTA_ARCHIVO);
            File carpeta = archivo.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }
            if (!archivo.exists()) {
                archivo.createNewFile();
                guardarLista(new ArrayList<>());
            }
        } catch (IOException e) {
            System.out.println("Error al inicializar el archivo de estudiantes: " + e.getMessage());
        }
    }

    // Leer todos los estudiantes
    public List<Estudiante> obtenerTodos() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type listType = new TypeToken<ArrayList<Estudiante>>() {}.getType();
            List<Estudiante> estudiantes = gson.fromJson(reader, listType);
            return estudiantes != null ? estudiantes : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer los estudiantes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Guardar una lista completa en el JSON
    public void guardarLista(List<Estudiante> estudiantes) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(estudiantes, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar los estudiantes: " + e.getMessage());
        }
    }

    // Agregar un nuevo estudiante
    public void insertar(Estudiante estudiante) {
        List<Estudiante> estudiantes = obtenerTodos();
        estudiantes.add(estudiante);
        guardarLista(estudiantes);
    }

    // Actualizar un estudiante existente
    public boolean actualizar(Estudiante estudianteActualizado) {
        List<Estudiante> estudiantes = obtenerTodos();
        boolean encontrado = false;

        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCodigo().equalsIgnoreCase(estudianteActualizado.getCodigo())) {
                estudiantes.set(i, estudianteActualizado);
                encontrado = true;
                break;
            }
        }

        if (encontrado) {
            guardarLista(estudiantes);
        }
        return encontrado;
    }

    // Eliminar un estudiante por su código
    public boolean eliminar(String codigo) {
        List<Estudiante> estudiantes = obtenerTodos();
        boolean eliminado = estudiantes.removeIf(e -> e.getCodigo().equalsIgnoreCase(codigo));

        if (eliminado) {
            guardarLista(estudiantes);
        }
        return eliminado;
    }
}