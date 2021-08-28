package br.gov.sp.fatec.springlojaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;
import br.gov.sp.fatec.springlojaapp.repository.ProdutoRepository;
import br.gov.sp.fatec.springlojaapp.entity.Marca;


@Service("marcaService")
public class MarcaServiceImpl implements MarcaService{

    @Autowired
	private MarcaRepository marcaRepo;

    @Autowired
	private ProdutoRepository produtoRepo;


    public void excluirMarca (Marca marca){
		marcaRepo.delete(marca);	
    }
	
	public Marca pesquisarPorIdMarca (Long id){
        Marca marca = marcaRepo.findById(id).get();
        return marca;
    }
    
}
