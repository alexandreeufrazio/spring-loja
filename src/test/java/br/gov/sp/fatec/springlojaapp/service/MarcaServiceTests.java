package br.gov.sp.fatec.springlojaapp.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;
import br.gov.sp.fatec.springlojaapp.entity.Marca;

@SpringBootTest
@Transactional
@Rollback
public class MarcaServiceTests {

    @Autowired
    private MarcaRepository marcaRepo;

	@Autowired
	private MarcaService marcaService;

    @Test
	void marcaServiceexcluirTestOK() {
		Marca marca = new Marca();
		marca.setNome("IBM");
		marcaRepo.save(marca);
		
		marcaService.excluirMarca(marca);
		
	    assertNull(marcaRepo.findByNomeIgnoreCase("IBM"));
		
	}

	@Test
	void marcaServiceAtualizaTestOK(){
		Marca marca = new Marca();
		marca.setNome("IBM");
		marcaRepo.save(marca);


		marcaService.atualizarMarca(marca.getId(), "Oracle");

		assertEquals("Oracle",marca.getNome());
	}
  
}
