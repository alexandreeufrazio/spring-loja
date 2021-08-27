package br.gov.sp.fatec.springlojaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springlojaapp.entity.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long>{
    
}
