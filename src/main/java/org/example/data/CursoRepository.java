package org.example.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.business.Curso;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

    //Atributos necesarios para la manipulación de Json mediante Gson como la ruta de json el objeto Gson.
    private final String RUTA_ARCHIVO = "data/cursos.json";
    private final Gson gson;



    // Formateando el Gson para que sea legible mediante su método pretty.
    public CursoRepository() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
        verificarYCrearArchivo();
    }



    // Aseguramos de que la carpeta data y el archivo cursos.json existan, de ser el caso de que no exista se crean.
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
            System.out.println("Error al inicializar el archivo de cursos: " + e.getMessage());
        }
    }



    // Método para guardar la lista COMPLETA de cursos en el archivo
    public void guardarLista(List<Curso> cursos) {
        try (FileWriter writer = new FileWriter(RUTA_ARCHIVO)) {
            gson.toJson(cursos, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar los cursos: " + e.getMessage());
        }
    }



    // Obtener todos los cursos del JSON y lso guarda en RAM
    public List<Curso> obtenerTodos() {
        try (FileReader reader = new FileReader(RUTA_ARCHIVO)) {
            Type listType = new TypeToken<ArrayList<Curso>>() {}.getType();
            List<Curso> cursos = gson.fromJson(reader, listType);
            return cursos != null ? cursos : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al leer los cursos: " + e.getMessage());
            return new ArrayList<>();
        }
    }



    // Insertar un nuevo curso a la lista en RAM y luego lo guarda en el Json.
    public void insertar(Curso curso) {
        List<Curso> cursos = obtenerTodos();
        cursos.add(curso);
        guardarLista(cursos);
    }
}