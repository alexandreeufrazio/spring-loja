package br.gov.sp.fatec.springlojaapp.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passEncoder;

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
    usuario.setSenha(passEncoder.encode(senha));
    usuario.setAutorizacoes(new HashSet<Autorizacao>());
    usuario.getAutorizacoes().add(autorizacao);
    usuarioRepo.save(usuario);

    return usuario;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public List<Usuario> buscarTodosUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(userName);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário " + userName + " não encontrado!"); 
        }
        return User.builder().username(userName)
			.password(usuario.getSenha())
			.authorities(usuario.getAutorizacoes()
					.stream()
					.map(Autorizacao::getNome)
					.collect(Collectors.toList())
					.toArray(new String[usuario
										.getAutorizacoes()
										.size()]))
					.build();      
    }    
}
