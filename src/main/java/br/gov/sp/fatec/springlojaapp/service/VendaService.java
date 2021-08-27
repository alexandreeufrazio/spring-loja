package br.gov.sp.fatec.springlojaapp.service;

import br.gov.sp.fatec.springlojaapp.entity.Venda;
import java.util.List;
import java.math.BigDecimal;

public interface VendaService {

    public Venda cadastrarVenda (Long qtde, BigDecimal preco, String nomeProduto); 
	
	public Venda atualizarVenda (String nome, BigDecimal preco, String nomeMarca);
	
	public Venda excluirPorIdVenda (Long id);
	
	public Venda pesquisarPorIdVenda (Long id);
	
	public List<Venda> pesquisarTodosVenda ();
    
}
