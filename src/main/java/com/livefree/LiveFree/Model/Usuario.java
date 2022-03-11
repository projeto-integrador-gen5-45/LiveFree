package com.livefree.LiveFree.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.livefree.LiveFree.util.EUsuario;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	// Campos da model (tabela)

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 1, max = 20)
	private String nome;

	@Schema(example = "email@email.com.br")
	@NotNull(message = "O atributo email é Obrigatório!")
	@Email(message = "O atributo email deve ser um email válido!")
	private String email;

	@NotNull
	private String senha;

	private @Enumerated(EnumType.STRING) EUsuario usuario;

	@NotNull
	private String cidade;

	@NotNull
	private String idioma;

	@OneToMany(mappedBy = "criador", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("criador")
	private List<Servico> meusServicos = new ArrayList<>();

	// Getters & Setters

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public EUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(EUsuario usuario) {
		this.usuario = usuario;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public List<Servico> getMeusServicos() {
		return meusServicos;
	}

	public void setMeusServicos(List<Servico> meusServicos) {
		this.meusServicos = meusServicos;
	}



}
