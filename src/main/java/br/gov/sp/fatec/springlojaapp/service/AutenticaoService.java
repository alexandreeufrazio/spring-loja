package br.gov.sp.fatec.springlojaapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springlojaapp.entity.Usuario;

public interface AutenticaoService extends UserDetailsService {
    
    public Usuario cadastrarUsuario (String nome, String email, String senha, String nomeAutorizacao);

    public List<Usuario> buscarTodosUsuarios();
}
