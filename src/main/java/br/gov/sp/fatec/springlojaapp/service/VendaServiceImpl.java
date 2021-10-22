package br.gov.sp.fatec.springlojaapp.service;

import javax.transaction.Transactional;
import br.gov.sp.fatec.springlojaapp.entity.Venda;
import br.gov.sp.fatec.springlojaapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springlojaapp.entity.Produto;
import java.util.List;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import br.gov.sp.fatec.springlojaapp.repository.VendaRepository;
import br.gov.sp.fatec.springlojaapp.repository.ProdutoRepository;
import java.util.Optional;


@Service("vendaService")
public class VendaServiceImpl implements VendaService{

    @Autowired
	private VendaRepository vendaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Override
	@Transactional
	public Venda cadastrarVenda(Long qtde, BigDecimal preco, String nomeProduto) {
		Produto produto = produtoRepo.findByNomeIgnoreCase(nomeProduto);
		
	    if(produto != null) {
	    	Venda venda = new Venda(); 
	    	Date date = new Date();
			venda.setData(date);
			venda.setPrecoVenda(preco);
			venda.setQtde(qtde);
			venda.setProdutos(new HashSet<Produto>());
			venda.getProdutos().add(produto);
			vendaRepo.save(venda);
			return venda;
	    }
		throw new RegistroNaoEncontradoException("Produto não encontrado!");	
	}

	@Override
	public Venda atualizarVenda(String nome, BigDecimal preco, String nomeMarca) {
		return null;
	}

	@Override
	public Venda excluirPorIdVenda(Long id) {
		return null;
	}

	@Override
	public Venda pesquisarPorIdVenda(Long id) {
		Optional<Venda> vendaOp = vendaRepo.findById(id);
        if(vendaOp.isPresent()) {
            return vendaOp.get();
        }
        throw new RegistroNaoEncontradoException("Venda não encontrado!");
	}

	@Override
	public List<Venda> pesquisarTodosVenda() {
		return vendaRepo.findAll();
	}
    
}
