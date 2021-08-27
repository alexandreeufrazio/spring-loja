package br.gov.sp.fatec.springlojaapp.service;

import java.math.BigDecimal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.sp.fatec.springlojaapp.entity.Produto;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;
import br.gov.sp.fatec.springlojaapp.repository.ProdutoRepository;
import java.util.List;

@Service("produtoService")
public class ProdutoServiceImpl implements ProdutoService {
    
    @Autowired
	private MarcaRepository marcaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Override
	@Transactional
    public Produto cadastrarProduto(String nome, BigDecimal preco, String nomeMarca) {
        Marca marca = marcaRepo.findByNomeIgnoreCase(nomeMarca);
				
	    if(marca == null) {
	    	marca = new Marca();
		    marca.setNome(nomeMarca);
		    marcaRepo.save(marca);
		}
		Produto produto = new Produto(); 
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setMarca(marca);
		produtoRepo.save(produto);
		return produto;		
    }

    @Override
	public void excluirPorIdProduto(Long id) {
		Produto produto = produtoRepo.findById(id).get();
		if(produto != null) {
			produtoRepo.delete(produto);
			}
	}

	@Override
	public Produto pesquisarPorNomeProduto(String nome) {
		return null;
	}

	@Override
	public Produto pesquisarPorIdProduto(Long id) {
		Produto produto = produtoRepo.findById(id).get();
		return produto;
	}

	@Override
	public List<Produto> pesquisarTodosProdutos() {
		return produtoRepo.findAll();
	}

	@Override
	public Produto atualizarProduto(String nome, BigDecimal preco, String nomeMarca) {
		return null;
	}

	@Override
	public Produto atualizarProduto(Long id, String nome, BigDecimal preco, String nomeMarca) {
		return null;
	}  
}
