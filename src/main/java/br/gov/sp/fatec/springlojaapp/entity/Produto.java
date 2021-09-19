package br.gov.sp.fatec.springlojaapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonView;
import br.gov.sp.fatec.springlojaapp.controller.View;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "pro_produto")
public class Produto {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pro_id")
	private Long id;

	@Column(name ="pro_nome")
	private String nome;
	
	@Column(name ="pro_preco")
	private BigDecimal preco;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mar_id")
	private Marca marca;
	
	@ManyToMany(mappedBy = "produtos", fetch = FetchType.LAZY)
	private Set<Venda> vendas;

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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Set<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(Set<Venda> vendas) {
        this.vendas = vendas;
    }   
}
