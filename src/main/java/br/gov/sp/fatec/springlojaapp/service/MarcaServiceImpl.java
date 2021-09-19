package br.gov.sp.fatec.springlojaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springlojaapp.repository.MarcaRepository;
import br.gov.sp.fatec.springlojaapp.entity.Marca;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Marca> buscarTodasMarcas() {
	    return marcaRepo.findAll();
    } 
    
    @Override
    public Marca buscarMarcaPorId(Long id){
        Optional<Marca> marcaOp = marcaRepo.findById(id);
        if(marcaOp.isPresent()) {
            return marcaOp.get();
        }
        throw new RuntimeException("Marca não encontrado!");
    }

    @Override
    public Marca buscarMarcaPorNome(String nome){
        Marca marca = marcaRepo.findByNome(nome);
        if (marca != null){
            return marca;
        }
        throw new RuntimeException("Marca não encontrado!");
    }

    @Override
	@Transactional
    public Marca cadastrarMarca(String nome) {
        Marca marca = marcaRepo.findByNomeIgnoreCase(nome);
				
	    if(marca == null) {
	    	marca = new Marca();
		    marca.setNome(nome);
		    marcaRepo.save(marca);
            return marca;
		}
        throw new RuntimeException("Marca já cadastrada!");	
    }
}
