package br.gov.sp.fatec.springlojaapp.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.springlojaapp.entity.Marca;

@SpringBootTest
@Transactional
@Rollback
public class MarcaRepositoryTests {

    @Autowired
	private MarcaRepository marcaRepo;
	
	
	@Test
	void testaMarcaInsercaoTestoOK() {
		Marca marca = new Marca();
		marca.setNome("Acer");
		marcaRepo.save(marca);
		assertNotNull(marca.getId());
	}
    
}
