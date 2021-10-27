package br.gov.sp.fatec.springlojaapp.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.sp.fatec.springlojaapp.entity.Autorizacao;
import br.gov.sp.fatec.springlojaapp.entity.Usuario;
import br.gov.sp.fatec.springlojaapp.repository.AutorizacaoRepository;
import br.gov.sp.fatec.springlojaapp.repository.UsuarioRepository;

@Service("autenticaoService")
public class AutenticaoServiceImpl implements AutenticaoService{

    @Autowired
    private AutorizacaoRepository autorizacaoRepo;

    @Autowired

    private UsuarioRepository usuarioRepo;
    @Override
    @Transactional
    public Usuario cadastrarUsuario(String nome, String email, String senha, String nomeAutorizacao) {
        Autorizacao autorizacao = autorizacaoRepo.findByNome(nomeAutorizacao);

    if(autorizacao == null) {
        autorizacao = new Autorizacao();
        autorizacao.setNome(nomeAutorizacao);
        autorizacaoRepo.save(autorizacao);
    }

    Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setEmail(email);
    usuario.setSenha(senha);
    usuario.setAutorizacoes(new HashSet<Autorizacao>());
    usuario.getAutorizacoes().add(autorizacao);
    usuarioRepo.save(usuario);

    return usuario;
    }
    @Override
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    
    
}
