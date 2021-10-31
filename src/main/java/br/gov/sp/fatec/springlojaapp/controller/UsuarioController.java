package br.gov.sp.fatec.springlojaapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.gov.sp.fatec.springlojaapp.entity.Usuario;
import br.gov.sp.fatec.springlojaapp.service.AutenticaoService;

@RestController
@CrossOrigin
@RequestMapping(value = "/usuario")
public class UsuarioController {

    @Autowired
    private AutenticaoService autenticacaoService;

    @GetMapping
    //@PreAuthorize("isAuthenticated()")
    public List<Usuario> buscarTodosUsuarios(){
        return autenticacaoService.buscarTodosUsuarios();
    }

    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario, 
        UriComponentsBuilder uriComponentsBuilder) {
            usuario = autenticacaoService.cadastrarUsuario(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), "ROLE_USUARIO");
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.setLocation(
              uriComponentsBuilder.path(
                  "/usuario/" + usuario.getId()).build().toUri());
              return new ResponseEntity<Usuario>(usuario, responseHeaders, HttpStatus.CREATED);
    }
}
