package br.com.javaparaweb.medprice.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import br.com.javaparaweb.medprice.usuario.*;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	
	public String novo() {
		this.destinoSalvar = "home";
		this.usuario = new Usuario();
		return "/publico/usuario";
	}
	
	
	
}