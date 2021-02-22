package br.com.javaparaweb.medprice.medicamento;

import java.io.Serializable;
import java.util.Collections;
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
		Collections.sort(this.comentarios);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apresentacao == null) ? 0 : apresentacao.hashCode());
		result = prime * result + ((comentarios == null) ? 0 : comentarios.hashCode());
		result = prime * result + idMed;
		result = prime * result + ((laboratorio == null) ? 0 : laboratorio.hashCode());
		long temp;
		temp = Double.doubleToLongBits(pmc0);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pmc12);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pmc17);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pmc175);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pmc18);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(pmc20);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((registro == null) ? 0 : registro.hashCode());
		result = prime * result + ((substancia == null) ? 0 : substancia.hashCode());
		result = prime * result + ((tarja == null) ? 0 : tarja.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((usuarios == null) ? 0 : usuarios.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicamento other = (Medicamento) obj;
		if (apresentacao == null) {
			if (other.apresentacao != null)
				return false;
		} else if (!apresentacao.equals(other.apresentacao))
			return false;
		if (comentarios == null) {
			if (other.comentarios != null)
				return false;
		} else if (!comentarios.equals(other.comentarios))
			return false;
		if (idMed != other.idMed)
			return false;
		if (laboratorio == null) {
			if (other.laboratorio != null)
				return false;
		} else if (!laboratorio.equals(other.laboratorio))
			return false;
		if (Double.doubleToLongBits(pmc0) != Double.doubleToLongBits(other.pmc0))
			return false;
		if (Double.doubleToLongBits(pmc12) != Double.doubleToLongBits(other.pmc12))
			return false;
		if (Double.doubleToLongBits(pmc17) != Double.doubleToLongBits(other.pmc17))
			return false;
		if (Double.doubleToLongBits(pmc175) != Double.doubleToLongBits(other.pmc175))
			return false;
		if (Double.doubleToLongBits(pmc18) != Double.doubleToLongBits(other.pmc18))
			return false;
		if (Double.doubleToLongBits(pmc20) != Double.doubleToLongBits(other.pmc20))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		if (substancia == null) {
			if (other.substancia != null)
				return false;
		} else if (!substancia.equals(other.substancia))
			return false;
		if (tarja == null) {
			if (other.tarja != null)
				return false;
		} else if (!tarja.equals(other.tarja))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (usuarios == null) {
			if (other.usuarios != null)
				return false;
		} else if (!usuarios.equals(other.usuarios))
			return false;
		return true;
	}
}

