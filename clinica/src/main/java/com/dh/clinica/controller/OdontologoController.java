package com.dh.clinica.controller;

import com.dh.clinica.entity.Odontologo;
import com.dh.clinica.service.impl.OdontologoService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {
    private OdontologoService odontologoService;


    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    // ingresa -> JSON -> jackson -> Objeto Odontologo
    // salga -> Objeto Odontologo -> jackson -> JSON
    @PostMapping("/guardar")
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if(odontologo.isPresent()){
            return ResponseEntity.ok(odontologo.get());
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

    @GetMapping("/buscartodos")
    public ResponseEntity<List<Odontologo>> buscarTodos(){
        return ResponseEntity.ok(odontologoService.buscarTodos());
    }

    @PutMapping("/modificar")
    public ResponseEntity<?> modificarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoEncontrado.isPresent()) {
            odontologoService.modificarodontolgo(odontologoEncontrado.get());
            String jsonResponse = "{\"mensaje\": \"El odontologo ha sido modificado\"}";
            return ResponseEntity.ok(jsonResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarPorId(id);
        if (odontologoEncontrado.isPresent()) {
            odontologoService.eliminarodontolgo(id);
            String jsonResponse = "{\"mensaje\": \"El odontologo ha sido eliminado\"}";
            return ResponseEntity.ok(jsonResponse);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscarApellidoNombre")
    public ResponseEntity<List<Odontologo>> buscarApellidoYNombre(@RequestParam String apellido,
                                                                @RequestParam String nombre){
        return ResponseEntity.ok(odontologoService.buscarPorApellidoyNombre(apellido, nombre));
    }

    @GetMapping("/buscarNombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarLikeNombre(@PathVariable String nombre){
        return ResponseEntity.ok(odontologoService.buscarLikeNombre(nombre));
    }
    @GetMapping("/buscarmatricula/{matricula}")
    public ResponseEntity<Optional<Odontologo>> buscarLikeMatricula(@PathVariable String matricula){
        Optional<Odontologo> odontologoEncontrado = odontologoService.buscarLikeMatricula(matricula);
        if(odontologoEncontrado.isPresent()){
            return ResponseEntity.ok(odontologoService.buscarLikeMatricula(matricula));
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }
    }

}