package br.gov.sp.fatec.springlojaapp.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import br.gov.sp.fatec.springlojaapp.entity.Produto;

@SpringBootTest
@Transactional
@Rollback
public class ProdutoRepositoryTests {

    @Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private MarcaRepository marcaRepo;
	
	@Test
	void testaProdutoInsercaoTestOk() {
		Marca marca = new Marca();
		Produto produto = new Produto();
		marca.setNome("Acer");
		marcaRepo.save(marca);
		produto.setNome("Desktop");
		produto.setPreco(new BigDecimal("9500.00"));
		produto.setMarca(marca);
		produtoRepo.save(produto);
		assertNotNull(produto.getId());
	}
	
	@Test
	void testaBuscaProdutoPorNomeAndMarcaQueryTestOK() {
		List<Produto> produto = produtoRepo.buscaProdutoPorNomeEMarca("DESKTOP", "DELL");
		assertFalse(produto.isEmpty());
	}

	@Test
	void testaBuscaNomeOrPrecoTestOk() {
		List<Produto> produto = produtoRepo.findByNomeOrPreco("DESKTOP", new BigDecimal("9500.00"));
		assertFalse(produto.isEmpty());
	}
    
}
