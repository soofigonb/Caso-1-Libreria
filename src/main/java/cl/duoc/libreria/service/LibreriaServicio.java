package cl.duoc.libreria.service;

import java.util.List;
import org.springframework.stereotype.Service;

import cl.duoc.libreria.model.Libro;
import cl.duoc.libreria.repository.LibreriaRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibreriaServicio {

    private final LibreriaRepositorio repo;
    
    //Guardar
    public Libro guardar (Libro libro){
        if (libro == null) return null;

        if (libro.getNombre() == null || libro.getNombre().isEmpty()) {
            return null;
        }

        if (libro.getPrecio() <= 0) {
            return null;
        }

        if (libro.getCategoria() == null || libro.getCategoria().isEmpty()) {
            return null;
        }

        return repo.save(libro);
    }

    //Obtener todos
    public List<Libro> obtenerTodos(){
        return repo.findAll();
    }

    //Eliminar por nombre
    public boolean eliminar(String nombre){
        if (nombre != null && !nombre.isEmpty()) {
            return repo.delete(nombre);
        }
        return false;
    }


}
