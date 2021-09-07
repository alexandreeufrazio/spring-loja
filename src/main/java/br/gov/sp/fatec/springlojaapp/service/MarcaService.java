package br.gov.sp.fatec.springlojaapp.service;

import br.gov.sp.fatec.springlojaapp.entity.Marca;
import java.util.List;

public interface MarcaService {

    public void excluirMarca (Marca marca);
	
	public Marca pesquisarPorIdMarca (Long id);

    public Marca atualizarMarca (Long id, String nome);

    public List<Marca> buscarTodasMarcas();   

    public Marca buscarMarcaPorId(Long id);
}
