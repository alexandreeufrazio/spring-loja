package br.gov.sp.fatec.springlojaapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.gov.sp.fatec.springlojaapp.entity.Usuario;

import br.gov.sp.fatec.springlojaapp.entity.Autorizacao;

public interface AutenticaoService extends UserDetailsService {
    
    public Usuario cadastrarUsuario(String nome,String email,  String senha, String autorizacao);

    public List<Usuario> buscarTodosUsuarios();
    
    public Usuario buscarUsuarioPorNome(String nome);
 
    public Usuario buscarUserById(Long id);

    public void deletarUsuario (Long id);

    public Usuario atualizarUsuario (Long id,String nome,String email,  String senha, String autorizacao);

    public Autorizacao buscarAutorizacaoPorNome(String nome);
}
