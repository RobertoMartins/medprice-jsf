package br.com.javaparaweb.medprice.web;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;
import br.com.javaparaweb.medprice.util.DAOException;
import br.com.javaparaweb.medprice.util.GmailUtil;
import br.com.javaparaweb.medprice.util.UtilException;
import br.com.javaparaweb.medprice.util.UtilValidator;
import net.bytebuddy.utility.RandomString;

@ManagedBean(name="emailBean")
@SessionScoped
public class EmailBean implements Serializable {
	private static final long serialVersionUID = -8218240456882550331L;

	private Usuario usuario;
	private String novaSenha;
	private String confirmarNovaSenha;
	private String email;

	private boolean emailSend = false;

	public String recuperarSenha() {
		
		
		UsuarioRN usuarioRN = new UsuarioRN();

		if (usuarioRN.buscarPorLogin(this.email) == null) {
			System.out.println("Nao achou");
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage();
			facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
			facesMessage.setSummary("Aviso:");
			facesMessage.setDetail("Nenhuma email foi encontrado. Verifique o email e tente novamente.");
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
				
					GmailUtil gmailUtil = new GmailUtil();
					try {
						gmailUtil.enviarEmail(email, "Recuperação de Senha", resetPasswordLink);
					} catch (UtilException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
		
	return "email_enviado";
	}

	public String salvarNovaSenha() {
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

		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		this.usuario.setSenha(bcpe.encode(this.novaSenha));

		this.usuario.setToken(null);

		UsuarioRN usuarioRN = new UsuarioRN();
		usuarioRN.salvar(this.usuario);

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

	public void validaToken(String token) {
		
		System.out.println(token);
		UsuarioRN usuarioRN = new UsuarioRN();
		Usuario usuario = usuarioRN.get(token);
		

		if (usuario == null) {
			return;
		}
		System.out.println(usuario.getEmail());
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
