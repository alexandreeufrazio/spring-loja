package br.gov.sp.fatec.springlojaapp.service;

import br.gov.sp.fatec.springlojaapp.entity.Marca;

public interface MarcaService {

    public void excluirPorIdMarca (Long id);
	
	public Marca pesquisarPorIdMarca (Long id);
    
}
