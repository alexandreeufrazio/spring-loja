package br.gov.sp.fatec.springlojaapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.springlojaapp.entity.Produto;
import br.gov.sp.fatec.springlojaapp.entity.Marca;


@SpringBootTest
@Transactional
@Rollback
public class ProdutoServiceTests {

    @Autowired
	private ProdutoService produtoService;
	
	//@Autowired
	//private ProdutoRepository produtoRepo;
	
    //@Autowired
    //private MarcaRepository marcaRepo;
	
	@Test
	void produtoServiceCadastrarProdutoTestOK() {
		Produto produto = produtoService.cadastrarProduto("Iphone", new BigDecimal("9500.00"),"Apple");
		
	    assertNotNull(produto.getId());
	}
	
	/*@Test
	void produtoServiceexcluirPorNomeProdutoTestOK() {
		Produto produto = produtoRepo.findById(1L).get();
		Marca marca = marcaRepo.findById(2L).get();
		
		marcaRepo.delete(marca);
		
		produtoService.excluirPorIdProduto(1L);
		
	    assertNull(produto.getId());
		
	}*/
    
}
