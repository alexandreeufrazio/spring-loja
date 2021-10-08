package br.gov.sp.fatec.springlojaapp.service;

import br.gov.sp.fatec.springlojaapp.entity.Produto;
import java.math.BigDecimal;
import java.util.List;

public interface ProdutoService {

    public Produto cadastrarProduto (String nome, BigDecimal preco, String nomeMarca);
	
	public Produto atualizarProduto (String nome, BigDecimal preco, String nomeMarca);
	
	public void excluirPorIdProduto (Long id);
	
	public Produto pesquisarPorNomeProduto (String nome);
	
	public Produto pesquisarPorIdProduto (Long i);
	
	public List<Produto> pesquisarTodosProdutos ();

	public Produto atualizarProduto(Long id, String nome, BigDecimal preco, String nomeMarca);    
}
