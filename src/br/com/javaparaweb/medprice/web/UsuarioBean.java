package br.com.javaparaweb.medprice.web;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;
import br.com.javaparaweb.medprice.util.UtilValidator;
import br.com.javaparaweb.medprice.util.log.LoggerUtil;

@ManagedBean(name = "usuarioBean")
@RequestScoped
public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String confirmarSenha;
	private List<Usuario> lista;
	private String destinoSalvar;
	ContextoBean c = new ContextoBean();
	private List<Medicamento> listaFav;
	MedicamentoBean m = new MedicamentoBean();
	
	public String novo() throws IOException {
		this.destinoSalvar = "usuariosucesso";
		this.usuario = new Usuario();
		this.usuario.setAtivo(true);
		return "/publico/cadastro";
	}

	public String salvar() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();

		String mensagens = UtilValidator.validaCamposCadastro(usuario, confirmarSenha);

		if (!mensagens.equals("")) {
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail(mensagens);
			context.addMessage("Erros", facesMessage);
			return null;
		}

		UsuarioRN usuarioRN = new UsuarioRN();
		this.usuario.setAtivo(true);

		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		this.usuario.setSenha(bcpe.encode(this.usuario.getSenha()));

		usuarioRN.salvar(this.usuario);
		LoggerUtil.escreveLog("Novo usuário: " + usuario.getEmail());

		return "usuariosucesso";
	}

	public String atribuiPermissao(Usuario usuario, String permissao) {
		this.usuario = usuario;
		Set<String> permissoes = this.usuario.getPermissao();
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

	public String excluir() throws IOException {
		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.excluir(this.usuario);
		LoggerUtil.escreveLog("Usuário excluído: " + usuario.getEmail());
		this.lista = null;
		return null;
	}

	public String ativar() throws IOException {
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

	public List<Medicamento> getListaFav() {
		return c.getLogado().getMedicamentos();
	}

	public void setListaFav(List<Medicamento> listaFav) {
		this.listaFav = listaFav;
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

	public boolean isFavorito(Medicamento medicamento) {
		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario u = usuarioRN.buscarPorLogin(c.getUsuarioLogado().getEmail());

		if (u.getMedicamentos() == null || u.getMedicamentos().size() == 0) {

			return false;
		}
		if (u.getMedicamentos().contains(medicamento)) {

			return true;
		}

		return false;
	}

	public String favoritar(Medicamento med) throws IOException {
		UsuarioRN usuarioRN = new UsuarioRN();
		c.getUsuarioLogado().getMedicamentos().add(med);
		usuarioRN.salvar(c.getUsuarioLogado());
		return "/restrito/medicamento";
	}

	public String deleteFav(Medicamento med) throws IOException {
		UsuarioRN usuarioRN = new UsuarioRN();
		c.getUsuarioLogado().getMedicamentos().remove(med);
		usuarioRN.salvar(c.getUsuarioLogado());
		return "/restrito/favorito";

	}

	public String favoritos() {
		return "/restrito/favorito";
	}

}