package br.com.javaparaweb.medprice.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	ContextoBean c = new ContextoBean();
	
	public String novo() {
		this.destinoSalvar = "usuariosucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/cadastro";
	}
	
	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		String senha = this.usuario.getSenha();
		if (!senha.equals(this.confirmarSenha)) {
			FacesMessage facesMessage = new FacesMessage("A senha n�o foi confirmada corretamente");
			context.addMessage(null, facesMessage);
			return null;
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuario.setAtivo(true);
		
		
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		this.usuario.setSenha(bcpe.encode(this.usuario.getSenha()));
		
		
		
		usuarioRN.salvar(this.usuario);

		return "usuariosucesso";
	}
	
	public String atribuiPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		java.util.Set<String> permissoes = this.usuario.getPermissao();
		if (permissoes.contains(permissao)) {
			permissoes.remove(permissao);
		} else {
			permissoes.add(permissao);
		}
		return null;
	}
	
	public String editar() {
		this.confirmarSenha = this.usuario.getSenha();
		return "/publico/cadastro";
	}
	
	public String excluir() {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		this.lista = null;
		return null;
	}
	
	public String ativar() {
		if (this.usuario.isAtivo())
			this.usuario.setAtivo(false);
		else
			this.usuario.setAtivo(true);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public List<Usuario> getLista() {
		if (this.lista == null) {
			UsuarioRN usuarioRN = new UsuarioRN();
			this.lista = usuarioRN.listar();
		}
		return this.lista;
	}

	public void setLista(List<Usuario> lista) {
		this.lista = lista;
	}

	public String getDestinoSalvar() {
		return destinoSalvar;
	}

	public void setDestinoSalvar(String destinoSalvar) {
		this.destinoSalvar = destinoSalvar;
	}
	
	public boolean isFavorito(Medicamento medicamento){
	

		if(this.usuario.getMedicamentos().contains(medicamento)) {
			System.out.println("Entrou");
			return true;
		}
			
		return false;
				
	}
}