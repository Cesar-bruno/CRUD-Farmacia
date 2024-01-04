package com.generation.farmacia.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity // defini que vai ser uma tabela
@Table(name = "tb_categoria") // defini o nome da tabela 
public class Categoria {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // configura que vai auto incremento
	private long id ;
	
	@Column(length = 20)
	@NotBlank(message = " O atributo nome é obrigatório.") // defini que o nome n pode ficar vazia, e msg que vai ser visualizada
	@Size(min = 5, max = 20, message = "O atributo nome precisa ter entre 5 e 20 caracteres.") // defini o tamanho do titulo
	private String nome;
	
	
	@Column(length = 100)
	@NotBlank(message = " O atributo descrição é obrigatório.") // defini que o nome n pode ficar vazia, e msg que vai ser visualizada
	@Size(min = 5, max = 100, message = "O atributo descrição precisa ter entre 5 e 20 caracteres.") // defini o tamanho do titulo
	private String descricao;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoria", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;


	public List<Produto> getProduto() {
		return produto;
	}


	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
