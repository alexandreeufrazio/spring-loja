package br.gov.sp.fatec.springlojaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;
import br.gov.sp.fatec.springlojaapp.entity.Marca;


@Service("marcaService")
public class MarcaServiceImpl implements MarcaService{

    @Autowired
	private MarcaRepository marcaRepo;

    @Override
    public void excluirMarca (Marca marca){
		marcaRepo.delete(marca);	
    }
	
    @Override
	public Marca pesquisarPorIdMarca (Long id){
        Marca marca = marcaRepo.findById(id).get();
        return marca;
    }
 
    @Override
    public Marca atualizarMarca (Long id, String nome){
        Marca marca = marcaRepo.findById(id).get();
        marca.setNome(nome);
        marcaRepo.save(marca);
        return marca;
    }
    
}
