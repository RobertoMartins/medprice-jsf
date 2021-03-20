package br.com.javaparaweb.medprice.util;

import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;

public class UtilValidator {

	

	public static String validaCamposCadastro(Usuario usuario, String confirmarSenha) {

		String erros = "";

		String nome = usuario.getNome();
		String email = usuario.getEmail();
		String senha = usuario.getSenha();
		String confirmSenha = confirmarSenha;

		UsuarioRN usuarioRN = new UsuarioRN();

		String regexSenha = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&amp;])[A-Za-z\\d@$!%*?&amp;]{8,10}$";
		String regexEmail = "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)";

		if (nome.equals("") || email.equals("") || senha.equals("") || confirmSenha.equals("")) {
			erros = "Preencha todos os campos obrigatorios. *";
			return erros;
		}
		if (nome.length() < 5) {
			erros = "O nome deve possuir mais de 5 letras.";
		}

		if (!senha.matches(regexSenha) || !confirmSenha.matches(regexSenha)) {
			erros = "Senha inválida. A senha informada não atende os requisitos";
		}

		if (!senha.equals(confirmSenha)) {
			erros = "A senha não foi confirmada corretamente.";
		}

		if (!email.matches(regexEmail)) {
			erros = "Informe um e-mail válido.";
		} else if (usuarioRN.buscarPorLogin(email) != null) {
			erros = "Esse email já está sendo utilizado.";
		}

		return erros;
	}
	
	public static String validaCamposRec(String novaSenha, String confirmarNovaSenha){
		
		String erros = "";
		String regexSenha = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&amp;])[A-Za-z\\d@$!%*?&amp;]{8,10}$";

		if (novaSenha.equals("") || confirmarNovaSenha.equals("")) {
			erros = "Preencha todos os campos obrigatorios. *";
			return erros;
		}
		
		if (!novaSenha.matches(regexSenha) || !confirmarNovaSenha.matches(regexSenha)) {
			erros = "Senha inválida. A senha informada não atende os requisitos";
		}

		if (!novaSenha.equals(confirmarNovaSenha)) {
			erros = "A senha não foi confirmada corretamente.";
		}
		return erros;
	}

}
