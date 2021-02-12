package br.com.javaparaweb.medprice.usuario;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.medicamento.Medicamento;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_usuario")
	private int idUsuario;

	private String email;

	private String nome;

	private String senha;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="usuarioBean")
	private List<Comentario> comentarios;

	//bi-directional many-to-many association to Medicamento
	@ManyToMany(mappedBy="usuarios")
	private List<Medicamento> medicamentos;
	
	@ElementCollection(targetClass = String.class) 
	@JoinTable(
			name="usuario_permissao", 
			uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario","permissao"})}, 
			joinColumns = @JoinColumn(name = "usuario")) 
	@Column(name = "permissao", length=50) 
	private Set<String>	permissao	= new HashSet<String>();

	public Usuario() {
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setUsuarioBean(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setUsuarioBean(null);

		return comentario;
	}

	public List<Medicamento> getMedicamentos() {
		return this.medicamentos;
	}

	public void setMedicamentos(List<Medicamento> medicamentos) {
		this.medicamentos = medicamentos;
	}
	public Set<String> getPermissao() {
		return permissao;
	}

	public void setPermissao(Set<String> permissao) {
		this.permissao = permissao;
	}
}
