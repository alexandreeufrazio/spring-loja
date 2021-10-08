package br.gov.sp.fatec.springlojaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.sp.fatec.springlojaapp.service.VendaService;
import br.gov.sp.fatec.springlojaapp.entity.Venda;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/venda")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping
    public List<Venda> pesquisarTodosVenda() {
        return vendaService.pesquisarTodosVenda();
    }
    
}
