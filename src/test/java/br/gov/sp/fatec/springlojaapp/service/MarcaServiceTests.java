package br.gov.sp.fatec.springlojaapp.service;

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

    @Test
	void marcaServiceexcluirPorIdTestOK() {
		Marca marca = marcaRepo.findById(1L).get();

		marcaRepo.delete(marca);
		
	    assertNull(marca.getId());
		
	}
    
}
