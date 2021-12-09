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
import br.gov.sp.fatec.springlojaapp.exception.RegistroNaoEncontradoException;
import java.util.Optional;

@Service("autenticaoService")
public class AutenticaoServiceImpl implements AutenticaoService{

    @Autowired
    UsuarioRepository usuarioRepo;

    @Autowired
    AutorizacaoRepository autorizacaoRepo;

    @Autowired
    private PasswordEncoder passEncorder;

    @Transactional
    public Usuario cadastrarUsuario(String nome, String email ,String senha, String autorizacao) {
        Autorizacao aut = autorizacaoRepo.findByNome(autorizacao);
        if (aut == null) {
            aut = new Autorizacao();
            aut.setNome(autorizacao);
            autorizacaoRepo.save(aut);
        }

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setSenha(passEncorder.encode(senha));
        usuario.setAutorizacoes(new HashSet<Autorizacao>());
        usuario.getAutorizacoes().add(aut);
        usuarioRepo.save(usuario);

        return usuario;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> buscarTodosUsuarios() {

        return usuarioRepo.findAll();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario buscarUsuarioPorNome(String nome) {
      Usuario usuario = usuarioRepo.findByNome(nome);
      if (usuario != null) {
        return usuario;
      }
      throw new RegistroNaoEncontradoException("Usuário não encontrado!");
    }

    @Override
    @PreAuthorize("hasAnyRole('ADMIN', 'USUARIO')")
    public Usuario buscarUserById(Long id) {
      Optional<Usuario> usuarioOp = usuarioRepo.findById(id);
      if (usuarioOp.isPresent()) {
        return usuarioOp.get();
      }
      throw new RegistroNaoEncontradoException("Usuário não encontrado!");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Autorizacao buscarAutorizacaoPorNome(String nome) {
      Autorizacao autorizacao = autorizacaoRepo.findByNome(nome);
      if (autorizacao != null) {
        return autorizacao;
      }
      throw new RegistroNaoEncontradoException("Autorização não encontrada!");
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public void deletarUsuario (Long id){
                usuarioRepo.deleteById(id);
    }
    
    @Override
    @PreAuthorize("isAuthenticated()")
    public Usuario atualizarUsuario (Long id, String nome, String email ,String senha, String autorizacao){
      
      Usuario usuario = usuarioRepo.findById(id).get();
      usuario.setNome(nome);
      usuario.setEmail(email);
      usuario.setSenha(passEncorder.encode(senha));
      usuario.setAutorizacoes(new HashSet<Autorizacao>());
      usuarioRepo.save(usuario);

      return usuario;
  }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByNome(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário " + username + " não encontrado! ");
        }
        return User.builder().username(username).password(usuario.getSenha())
                .authorities(usuario.getAutorizacoes().stream()
                    .map(Autorizacao::getNome).collect(Collectors.toList())
                    .toArray(new String[usuario.getAutorizacoes().size()]))
                .build();
    }
    
}
