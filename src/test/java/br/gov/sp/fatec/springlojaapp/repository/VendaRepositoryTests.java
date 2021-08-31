package br.gov.sp.fatec.springlojaapp.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import br.gov.sp.fatec.springlojaapp.entity.Produto;
import br.gov.sp.fatec.springlojaapp.entity.Venda;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

@SpringBootTest
@Transactional
@Rollback
public class VendaRepositoryTests {

    @Test
	void testaVendaInsercaoTestOk() {
		Venda venda = new Venda();

        Marca marca = new Marca();

		Produto produto = new Produto();

        Date date = new Date();

		marca.setNome("Logitech");
		marcaRepo.save(marca);

		produto.setNome("Mouse");
		produto.setPreco(new BigDecimal("150.00"));
		produto.setMarca(marca);
        produtoRepo.save(produto);

        venda.setData(date);
        venda.setPrecoVenda(new BigDecimal("180.00"));
        venda.setQtde(2L);
        venda.setProdutos(new HashSet<Produto>());
        venda.getProdutos().add(produto);
        vendaRepo.save(venda);

		assertNotNull(venda.getId());
    }
}