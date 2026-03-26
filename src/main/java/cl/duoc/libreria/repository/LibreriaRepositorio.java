package cl.duoc.libreria.repository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import cl.duoc.libreria.model.Libro;

@Repository
public class LibreriaRepositorio {

    private List<Libro> listaLibros = new ArrayList<>();

    //Guardar
    public Libro save(Libro libro){
        listaLibros.add(libro);
        return libro;
    }

    //Obtener Todos
    public List<Libro> findAll(){
        return listaLibros;
    }

    //Eliminar por nombre
    public boolean delete(String nombre){
        Libro libroPorBorrar = listaLibros.stream()
                .filter(libro -> libro.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);

        if (libroPorBorrar != null) {
            listaLibros.remove(libroPorBorrar);
            return true;
        }
        return false;
    }



}
