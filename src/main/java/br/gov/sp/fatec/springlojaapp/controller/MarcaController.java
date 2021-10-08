package br.gov.sp.fatec.springlojaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import br.gov.sp.fatec.springlojaapp.service.MarcaService;


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
    public Marca buscarMarcaPorId(@PathVariable("id") Long id) {
	    return marcaService.buscarMarcaPorId(id);
    } 

    @GetMapping(value = "/nome")
    public Marca buscarMarcaPorNome(@RequestParam(value="nome") String nome) {
	    return marcaService.buscarMarcaPorNome(nome);
    } 

    @PostMapping
    public Marca cadastrarNovaMarca(@RequestBody Marca marca){
        return marcaService.cadastrarMarca(marca.getNome());
    }
}
