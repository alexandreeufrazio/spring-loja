package br.gov.sp.fatec.springlojaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/marca")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @GetMapping
    public List<Marca> buscarTodasMarcas() {
        return marcaService.buscarTodasMarcas();
    }

    @GetMapping(value = "/{id}")
    public Marca buscarPorId(@PathVariable("id") Long id) {
	    return marcaService.buscarMarcaPorId(id);
    } 
}
