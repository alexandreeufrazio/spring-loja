package br.gov.sp.fatec.springlojaapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import java.math.BigDecimal;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "ven_venda")
public class Venda {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ven_id")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name ="ven_data")
	private Date data;
	
	@Column(name ="ven_qtde")
	private Long qtde;
	
	@Column(name ="ven_preco_venda")
	private BigDecimal precoVenda;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ite_itemvenda",
	joinColumns = { @JoinColumn(name = "ven_id") },
	inverseJoinColumns = { @JoinColumn(name = "pro_id") })
	private Set<Produto> produtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getQtde() {
        return qtde;
    }

    public void setQtde(Long qtde) {
        this.qtde = qtde;
    }

    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
		this.produtos = produtos;
	}
}
