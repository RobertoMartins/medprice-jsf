package br.com.javaparaweb.medprice.comentario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.usuario.Usuario;


/**
 * The persistent class for the comentario database table.
 * 
 */
@Entity
@NamedQuery(name="Comentario.findAll", query="SELECT c FROM Comentario c")
public class Comentario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_com")
	private int idCom;

	//bi-directional many-to-one association to Medicamento
	@ManyToOne
	@JoinColumn(name="medicamento")
	private Medicamento medicamentoBean;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario")
	private Usuario usuarioBean;

	public Comentario() {
	}

	public int getIdCom() {
		return this.idCom;
	}

	public void setIdCom(int idCom) {
		this.idCom = idCom;
	}

	public Medicamento getMedicamentoBean() {
		return this.medicamentoBean;
	}

	public void setMedicamentoBean(Medicamento medicamentoBean) {
		this.medicamentoBean = medicamentoBean;
	}

	public Usuario getUsuarioBean() {
		return this.usuarioBean;
	}

	public void setUsuarioBean(Usuario usuarioBean) {
		this.usuarioBean = usuarioBean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCom;
		result = prime * result + ((medicamentoBean == null) ? 0 : medicamentoBean.hashCode());
		result = prime * result + ((usuarioBean == null) ? 0 : usuarioBean.hashCode());
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
		Comentario other = (Comentario) obj;
		if (idCom != other.idCom)
			return false;
		if (medicamentoBean == null) {
			if (other.medicamentoBean != null)
				return false;
		} else if (!medicamentoBean.equals(other.medicamentoBean))
			return false;
		if (usuarioBean == null) {
			if (other.usuarioBean != null)
				return false;
		} else if (!usuarioBean.equals(other.usuarioBean))
			return false;
		return true;
	}

}