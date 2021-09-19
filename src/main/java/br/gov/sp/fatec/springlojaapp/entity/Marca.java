package br.gov.sp.fatec.springlojaapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import br.gov.sp.fatec.springlojaapp.controller.View;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Set;

@Entity
@Table (name = "mar_marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "mar_id")
    private Long id;

    @JsonView(View.MarcaResumo.class)
    @Column (name = "mar_nome")
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "marca")
	private Set<Produto> produtos;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}
