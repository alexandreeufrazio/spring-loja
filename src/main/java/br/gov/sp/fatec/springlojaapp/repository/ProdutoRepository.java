package br.gov.sp.fatec.springlojaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.gov.sp.fatec.springlojaapp.entity.Produto;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
    public Produto findByNomeIgnoreCase(String nome);
	
	public List<Produto> findByNomeContaining(String nome);

	public List<Produto> findByNomeOrPreco(String nome, BigDecimal preco);
	
	@Query("select p from Produto p inner join p.marca m where p.nome = ?1 and m.nome = ?2")
	public List<Produto> buscaProdutoPorNomeEMarca(String nome, String marca);

	
}
