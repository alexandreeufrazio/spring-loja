package br.gov.sp.fatec.springlojaapp.service;

import java.math.BigDecimal;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.sp.fatec.springlojaapp.entity.Produto;
import br.gov.sp.fatec.springlojaapp.exception.RegistroNaoEncontradoException;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;
import br.gov.sp.fatec.springlojaapp.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

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
	@PreAuthorize("isAuthenticated()")
	public void excluirPorIdProduto(Long id) {
		Produto produto = produtoRepo.findById(id).get();
		if(produto != null) {
			produtoRepo.delete(produto);
		}else{
			throw new RegistroNaoEncontradoException("Produto não encontrado!"); 	
		}
	}

	
	@Override
	@PreAuthorize("isAuthenticated()")
	public Produto pesquisarPorNomeProduto(String nome) {
		Produto produto = produtoRepo.findByNomeIgnoreCase(nome);
		if(produto != null){
			return produto;
		}
		throw new RegistroNaoEncontradoException("Produto não encontrado!");	
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Produto pesquisarPorIdProduto (Long id) {
		Optional<Produto> produtoOp = produtoRepo.findById(id);
        if(produtoOp.isPresent()) {
            return produtoOp.get();
        }
        throw new RegistroNaoEncontradoException("Produto não encontrado!");
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Produto> pesquisarTodosProdutos() {
		return produtoRepo.findAll();
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Produto atualizarProduto (Long id, String nome, BigDecimal preco, String nomeMarca) {
		Produto produto = produtoRepo.findById(id).get();

		Marca marca = marcaRepo.findByNomeIgnoreCase(nomeMarca);
				
	    if(marca == null) {
	    	marca = new Marca();
		    marca.setNome(nomeMarca);
		    marcaRepo.save(marca);
		}

        if (produto != null){
            produto.setNome(nome);
			produto.setPreco(preco);
			produto.setMarca(marca);
            produtoRepo.save(produto);
            return produto;
        }
        throw new RegistroNaoEncontradoException("Marca não encontrado!");
	}

}
