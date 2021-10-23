package br.gov.sp.fatec.springlojaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.sp.fatec.springlojaapp.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
