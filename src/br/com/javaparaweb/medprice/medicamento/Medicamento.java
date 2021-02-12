package br.com.javaparaweb.medprice.medicamento;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.usuario.Usuario;

/**
 * The persistent class for the medicamento database table.
 * 
 */
@Entity
@NamedQuery(name="Medicamento.findAll", query="SELECT m FROM Medicamento m")
public class Medicamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_med")
	private int idMed;

	private String apresentacao;

	private String laboratorio;

	@Column(name="pmc_0")
	private double pmc0;

	@Column(name="pmc_12")
	private double pmc12;

	@Column(name="pmc_17")
	private double pmc17;

	@Column(name="pmc_175")
	private double pmc175;

	@Column(name="pmc_18")
	private double pmc18;

	@Column(name="pmc_20")
	private double pmc20;

	private String produto;

	private String registro;

	private String substancia;

	private String tarja;

	private String tipo;

	//bi-directional many-to-one association to Comentario
	@OneToMany(mappedBy="medicamentoBean")
	private List<Comentario> comentarios;

	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinColumn(name="id_med")
	private List<Usuario> usuarios;

	public Medicamento() {
	}

	public int getIdMed() {
		return this.idMed;
	}

	public void setIdMed(int idMed) {
		this.idMed = idMed;
	}

	public String getApresentacao() {
		return this.apresentacao;
	}

	public void setApresentacao(String apresentacao) {
		this.apresentacao = apresentacao;
	}

	public String getLaboratorio() {
		return this.laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public double getPmc0() {
		return this.pmc0;
	}

	public void setPmc0(double pmc0) {
		this.pmc0 = pmc0;
	}

	public double getPmc12() {
		return this.pmc12;
	}

	public void setPmc12(double pmc12) {
		this.pmc12 = pmc12;
	}

	public double getPmc17() {
		return this.pmc17;
	}

	public void setPmc17(double pmc17) {
		this.pmc17 = pmc17;
	}

	public double getPmc175() {
		return this.pmc175;
	}

	public void setPmc175(double pmc175) {
		this.pmc175 = pmc175;
	}

	public double getPmc18() {
		return this.pmc18;
	}

	public void setPmc18(double pmc18) {
		this.pmc18 = pmc18;
	}

	public double getPmc20() {
		return this.pmc20;
	}

	public void setPmc20(double pmc20) {
		this.pmc20 = pmc20;
	}

	public String getProduto() {
		return this.produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getRegistro() {
		return this.registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getSubstancia() {
		return this.substancia;
	}

	public void setSubstancia(String substancia) {
		this.substancia = substancia;
	}

	public String getTarja() {
		return this.tarja;
	}

	public void setTarja(String tarja) {
		this.tarja = tarja;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Comentario> getComentarios() {
		return this.comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public Comentario addComentario(Comentario comentario) {
		getComentarios().add(comentario);
		comentario.setMedicamentoBean(this);

		return comentario;
	}

	public Comentario removeComentario(Comentario comentario) {
		getComentarios().remove(comentario);
		comentario.setMedicamentoBean(null);

		return comentario;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}

