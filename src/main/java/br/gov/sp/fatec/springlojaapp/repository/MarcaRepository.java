package br.gov.sp.fatec.springlojaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springlojaapp.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long>{
    
    public Marca findByNomeIgnoreCase(String nome);
}
