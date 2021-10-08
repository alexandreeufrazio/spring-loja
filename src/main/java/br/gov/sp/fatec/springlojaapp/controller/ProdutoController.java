package br.gov.sp.fatec.springlojaapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.gov.sp.fatec.springlojaapp.service.ProdutoService;
import br.gov.sp.fatec.springlojaapp.entity.Produto;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> buscarTodasProdutos() {
        return produtoService.pesquisarTodosProdutos();
    }

    @GetMapping(value = "id/{id}")
    public Produto buscarProdutoPorId(@PathVariable("id") Long id) {
	    return produtoService.pesquisarPorIdProduto(id);
    } 

    @GetMapping(value = "{produto}")
    public Produto buscarProdutoPorNome(@PathVariable("produto") String nome){
        return produtoService.pesquisarPorNomeProduto(nome);
    }  
}
