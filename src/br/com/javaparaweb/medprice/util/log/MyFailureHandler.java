package br.com.javaparaweb.medprice.util.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class MyFailureHandler implements AuthenticationFailureHandler {
	

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String nome = request.getParameter("j_username");

		LoggerUtil.escreveLog("Tentativa de login com o email: " +nome+ " falhou, Motivo: "
				+ exception.getMessage());

		response.sendRedirect("/medprice/publico/login.jsf?login_error=1");
	}

}