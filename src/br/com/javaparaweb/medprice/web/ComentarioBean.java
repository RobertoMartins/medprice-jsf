package br.com.javaparaweb.medprice.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.comentario.ComentarioRN;
import br.com.javaparaweb.medprice.usuario.Usuario;

@ManagedBean(name = "comentarioBean")
@ViewScoped
public class ComentarioBean {
	
	private Comentario comentario = new Comentario();
	private String registro;
	

	@ManagedProperty(value = "#{contextoBean}")
	private ContextoBean contextoBean;
	
	//método para adicionar um novo comentario
	public void add() {
		Usuario u = contextoBean.getUsuarioLogado();
		ComentarioRN comRN = new ComentarioRN();
		comentario.setUsuarioBean(u);
		comRN.adicionar(comentario);
		comentario.getMedicamentoBean().addComentario(comentario);
		comentario=new Comentario();
		
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
