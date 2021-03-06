package br.gov.sp.fatec.springlojaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity<Marca> cadastrarNovaMarca(@RequestBody Marca marca,
        UriComponentsBuilder uriComponentsBuilder) {
            marca = marcaService.cadastrarMarca(marca.getNome());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
              uriComponentsBuilder.path(
                  "/marca/" + marca.getId()).build().toUri());
              return new ResponseEntity<Marca>(marca, responseHeaders, HttpStatus.CREATED);
      
    }

    /*@PostMapping
    public Marca cadastrarNovaMarca(@RequestBody Marca marca) {
            return marcaService.cadastrarMarca(marca.getNome());
    
    }*/

    @PutMapping(value="/{id}")
    public Marca atualizarMarca(@PathVariable("id") Long id, @RequestBody Marca marca){
        return marcaService.atualizarMarca(id, marca.getNome());
    }

    @DeleteMapping(value="/{id}")
    public void deletaMarca(@PathVariable("id") Long id) {
        marcaService.deleteMarca(id);    
    }
}