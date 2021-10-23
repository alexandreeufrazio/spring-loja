package br.gov.sp.fatec.springlojaapp.service;

import br.gov.sp.fatec.springlojaapp.entity.Usuario;

public interface AutenticaoService {
    
    public Usuario cadastrarUsuario (String nome, String email, String senha, String nomeAutorizacao);
}
