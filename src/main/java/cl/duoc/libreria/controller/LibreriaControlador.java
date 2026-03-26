package cl.duoc.libreria.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.libreria.service.LibreriaServicio;
import cl.duoc.libreria.model.Libro;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/productos")
@RequiredArgsConstructor

public class LibreriaControlador {

    private final LibreriaServicio servicio;

    @PostMapping("")
    public ResponseEntity<?> guardar(@RequestBody Libro libro) {
        Libro nuevo = servicio.guardar(libro);

        if (nuevo != null) {
            return ResponseEntity.status(201).body(nuevo);
        }

        return ResponseEntity.badRequest().body("No se pudo agregar el libro");
    }

    @GetMapping("")
    public List<Libro> obtenerTodos() {
        return servicio.obtenerTodos();
    }

    @DeleteMapping("/{nombre}")
    public ResponseEntity<String> borrar(@PathVariable String nombre) {
        if (servicio.eliminar(nombre)) {
            return ResponseEntity.status(204).body("Producto eliminado");
        }

        return ResponseEntity.badRequest().body("Producto no existe.");
    }
    
    
    
}
