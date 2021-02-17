package br.com.javaparaweb.medprice.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.comentario.ComentarioRN;
import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.usuario.Usuario;

@ManagedBean(name = "comentarioBean")
@RequestScoped
public class ComentarioBean {
	
	private Comentario comentario = new Comentario();
	private String registro;
	

	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;
	
	
	public String add() {
		Usuario u = contextoBean.getUsuarioLogado();
		Medicamento m = contextoBean.medicamentoContext(registro);
		comentario.setMedicamentoBean(m);
		comentario.setUsuarioBean(u);
		ComentarioRN comRN = new ComentarioRN();
		comRN.adicionar(comentario);
		comentario=new Comentario();
		return null;
	}
	
	public ContextoBean getContextoBean() {
		return contextoBean;
	}

	public void setContextoBean(ContextoBean contextoBean) {
		this.contextoBean = contextoBean;
	}

	public Comentario getComentario() {
		return comentario;
	}

	public void setComentario(Comentario comentario) {
		this.comentario = comentario;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	
	

}
