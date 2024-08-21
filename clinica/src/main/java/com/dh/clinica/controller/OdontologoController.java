package com.dh.clinica.controller;

import com.dh.clinica.model.Odontologo;
import com.dh.clinica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @GetMapping("/buscar/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id){
        return odontologoService.buscarPorId(id);
    }

    @GetMapping("/buscartodos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.buscarTodos();
    }

    @PutMapping("/modificar")
    public String modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.modificarodontolgo(odontologo);
        return "el odontologo fue modificado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarOdontologo(@PathVariable Integer id){
        odontologoService.eliminarodontolgo(id);
        return "el odontologo fue eliminado";
    }
}