package br.com.javaparaweb.medprice.web;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.medicamento.MedicamentoRN;
import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;

@ManagedBean
@SessionScoped
public class ContextoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Usuario logado = getUsuarioLogado();
	public Usuario getUsuarioLogado() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		String login = external.getRemoteUser();
		if (login != null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			return usuarioRN.buscarPorLogin(login);
		}
		return null;
	}

	public Medicamento medicamentoContext(String registro) {
		MedicamentoRN medicamentoRN = new MedicamentoRN();
		return medicamentoRN.buscarPorRegistro(registro);

	}

	public Usuario getLogado() {
		return logado;
	}

	public void setLogado(Usuario logado) {
		this.logado = logado;
	}
	

}