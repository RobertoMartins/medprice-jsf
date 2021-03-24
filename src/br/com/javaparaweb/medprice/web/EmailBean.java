package br.com.javaparaweb.medprice.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import net.bytebuddy.utility.RandomString;

import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;
import br.com.javaparaweb.medprice.util.DAOException;
import br.com.javaparaweb.medprice.util.GmailUtil;
import br.com.javaparaweb.medprice.util.UtilException;
import br.com.javaparaweb.medprice.util.UtilValidator;
import br.com.javaparaweb.medprice.util.log.LoggerUtil;

@ManagedBean(name="emailBean")
@ViewScoped
public class EmailBean implements Serializable {
	private static final long serialVersionUID = -8218240456882550331L;

	private Usuario usuario;
	private String novaSenha;
	private String confirmarNovaSenha;
	private String email;
	private boolean emailSend = false;

	public String recuperarSenha() throws IOException{
		
		UsuarioRN usuarioRN = new UsuarioRN();

		if (usuarioRN.buscarPorLogin(this.email) == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail("Nenhum email foi encontrado. Verifique o email e tente novamente.");
			context.addMessage("EmailNaoEncontrado", facesMessage);
			return null;
		}

		this.setEmailSend(true);
		String token = RandomString.make(25);

		String resetPasswordLink = "http://localhost:8080/medprice/publico/nova_senha.jsf?token=" + token;

		try {
			
			usuarioRN.updatePasswordToken(token, this.email);
		} catch (DAOException e) {
			e.printStackTrace();
		}

		ExecutorService emailExecutor = Executors.newCachedThreadPool();
		emailExecutor.execute(new Runnable() {
			@Override
			public void run() {
				FacesContext context = FacesContext.getCurrentInstance();
				GmailUtil gmailUtil = new GmailUtil();
					try {
						gmailUtil.enviarEmail(email, "Recuperação de Senha", resetPasswordLink);
						
					} catch (UtilException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		return "/publico/email_enviado";
	}

	public String salvarNovaSenha() throws IOException {
		String regex = "\"[a-zA-Z0-9\\-\\_\\.]+@[a-zA-Z0-9\\-\\_\\.]+\"";
		FacesContext context = FacesContext.getCurrentInstance();

		String mensagens = UtilValidator.validaCamposRec(novaSenha, confirmarNovaSenha);

		if (!mensagens.equals("")) {
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_WARN);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail(mensagens);
			context.addMessage("Erros", facesMessage);
			return null;
		}

		// Utilizando BCrypt para cripotografar a nova senha
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		this.usuario.setSenha(bcpe.encode(this.novaSenha));

		this.usuario.setToken(null);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);
		LoggerUtil.escreveLog("Nova alteração de senha para o usuário: " + usuario.getEmail());
		
		return "/publico/senha_alterada";
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
	
	//método para validar o token que foi recebido com param no browser

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

}
