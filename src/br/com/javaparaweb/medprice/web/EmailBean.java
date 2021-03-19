package br.com.javaparaweb.medprice.web;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.bytebuddy.utility.RandomString;

import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;
import br.com.javaparaweb.medprice.util.DAOException;
import br.com.javaparaweb.medprice.util.GmailUtil;

@ManagedBean(name="emailBean")
@ViewScoped
public class EmailBean implements Serializable {
	private static final long serialVersionUID = -8218240456882550331L;

	private Usuario usuario;
	private String novaSenha;
	private String confirmarNovaSenha;
	private String email;
	private boolean emailSend = false;

	public void recuperarSenha() {

		UsuarioRN usuarioRN = new UsuarioRN();

		if (usuarioRN.buscarPorLogin(this.email) == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail("Nenhuma conta cadastrada com esse email.");
			context.addMessage("MessageEmailNotFound", facesMessage);
			return;
		}

		this.setEmailSend(true);
		String token = Random(45);

		String resetPasswordLink = "http://localhost:8080/bedriver/public/resetar_senha.jsf?token=" + token;

		try {
			usuarioRN.updatePasswordToken(token, this.email);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		ExecutorService emailExecutor = Executors.newCachedThreadPool();
		emailExecutor.execute(new Runnable() {
			@Override
			public void run() {
				GmailUtil.enviarEmail(email, "Recuperação de Senha", resetPasswordLink);
			}
		});
	}

	public String salvarNovaSenha() {
		String regex = "\"[a-zA-Z0-9\\-\\_\\.]+@[a-zA-Z0-9\\-\\_\\.]+\"";
		FacesContext context = FacesContext.getCurrentInstance();

		if (!this.getNovaSenha().equals(this.getConfirmarNovaSenha())) {
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail("A senha não foi confirmada corretamente.");
			context.addMessage("NewPassNotEquals", facesMessage);
			return null;
		}

		if (this.getNovaSenha().matches(regex) || this.getConfirmarNovaSenha().matches(regex)) {
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail("Informe uma senha mais forte, contendo: " + "8 ou mais caracteres, "
					+ "letras maiúsculas e minúsculas, " + "números, " + "caracteres especiais.");
			context.addMessage("NewPasswordNotStrong", facesMessage);
			return null;
		}

		// Utilizando BCrypt na senha
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		this.usuario.setSenha(bcpe.encode(this.novaSenha));

		this.usuario.setToken(null);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

		return "/index";
	}

	public boolean isEmailSend() {
		return emailSend;
	}

	public void setEmailSend(boolean emailSend) {
		this.emailSend = emailSend;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void validaToken(String token) {

		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario usuario = usuarioRN.get(token);

		if (usuario == null) {
			return;
		}

		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmarNovaSenha() {
		return confirmarNovaSenha;
	}

	public void setConfirmarNovaSenha(String confirmarNovaSenha) {
		this.confirmarNovaSenha = confirmarNovaSenha;
	}

	private String Random(int i) {
		Random random = new Random();

	}

}
