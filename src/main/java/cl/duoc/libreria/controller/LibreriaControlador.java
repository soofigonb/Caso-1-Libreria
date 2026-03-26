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

    @PostMapping("") //http://localhost:8081/api/v1/productos
    public ResponseEntity<?> guardar(@RequestBody Libro libro) {
        try {
            Libro nuevo = servicio.guardar(libro);

            if (nuevo == null) {
                return ResponseEntity.badRequest()
                        .body("Datos inválidos: nombre, precio o categoría incorrectos");
            }

            return ResponseEntity.status(201).body(nuevo);

        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body("Error interno del servidor");
        }
    }

    @GetMapping("") //http://localhost:8081/api/v1/productos
    public ResponseEntity<List<Libro>> obtenerTodos() {
        List<Libro> lista = servicio.obtenerTodos();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @DeleteMapping("/{nombre}") //http://localhost:8081/api/v1/productos/{nombre}
    public ResponseEntity<String> borrar(@PathVariable String nombre) {
        if (servicio.eliminar(nombre)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(404).body("Producto no encontrado");
    }
    
    
    
}
