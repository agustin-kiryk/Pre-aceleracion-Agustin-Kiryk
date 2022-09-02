package com.disney.alkemy.Controller;

import com.disney.alkemy.DTO.PeliculaAuxDTO;
import com.disney.alkemy.DTO.PeliculaDTO;
import com.disney.alkemy.Service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("movies")
public class PeliculaController {


    private PeliculaService peliculaService;
    @Autowired
    PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PeliculaAuxDTO>> getAll() {
        List<PeliculaAuxDTO> peliculas = peliculaService.getAll();
        return ResponseEntity.ok(peliculas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getDetailsById(@PathVariable Long id) {
        PeliculaDTO pelicula = this.peliculaService.getDetailsById(id);
        return ResponseEntity.ok(pelicula);
    }

    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getDetailsByFilters(
             @RequestParam(required = false) String titulo,
             @RequestParam(required = false) Long genero,
             @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<PeliculaDTO> peliculas = this.peliculaService.getDetailsByFilters(titulo, genero, order);
        return ResponseEntity.ok(peliculas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> update (@PathVariable Long id, @RequestBody PeliculaDTO pelicula) {
        PeliculaDTO result = this.peliculaService.update(id, pelicula);
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(
            @RequestBody PeliculaDTO pelicula) {
        PeliculaDTO result = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{id}/characters/{idCharacters}")
    public ResponseEntity<Void> addPersonaje(
            @PathVariable Long id,
            @PathVariable Long idPersonaje){
        this.peliculaService.addPersonaje(id, idPersonaje);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/characters/{idCharacters}")
    public ResponseEntity<Void> delete(
             @PathVariable Long id,
             @PathVariable Long idPersonaje) {
        this.peliculaService.removePersonaje(id, idPersonaje);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }








    /**
    @Autowired
    private PeliculaService peliculaService;


    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaGuardado = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardado);
    }
    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getAll(){
        List<PeliculaDTO> pelicula = peliculaService.getAllpeliculas();
        return ResponseEntity.ok().body(pelicula);

    }
    //Borrar peliculas//
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); **/

}
