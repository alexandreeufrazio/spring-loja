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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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

    @GetMapping(value = "{id}")
    public Produto buscarProdutoPorId(@PathVariable("id") Long id) {
	    return produtoService.pesquisarPorIdProduto(id);
    } 

    @GetMapping(value = "/nome")
    public Produto buscarProdutoPorNome(@RequestParam(value="nome") String nome){
        return produtoService.pesquisarPorNomeProduto(nome);
    }  
    
    @PostMapping
    public ResponseEntity<Produto> cadastrarNovoProduto(@RequestBody Produto produto,
        UriComponentsBuilder uriComponentsBuilder) {
            produto = produtoService.cadastrarProduto(produto.getNome(), produto.getPreco(), produto.getMarca().getNome());
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
              uriComponentsBuilder.path(
                  "/produto/" + produto.getId()).build().toUri());
              return new ResponseEntity<Produto>(produto, responseHeaders, HttpStatus.CREATED);
      
    }

    @DeleteMapping(value="/{id}")
    public void deletaProduto(@PathVariable("id") Long id) {
        produtoService.excluirPorIdProduto(id);  
    }
}
