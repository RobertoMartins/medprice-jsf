package br.com.javaparaweb.medprice.util.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class MySuccessHandler implements AuthenticationSuccessHandler{
	
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(authentication.isAuthenticated()) {
		
			LoggerUtil.escreveLog("Logado com sucesso: "+SecurityContextHolder.getContext().getAuthentication().getName());

			response.sendRedirect("/medprice/restrito/principal.jsf");
		 }else {
			System.out.println("Erro"); 
		 }
		
		
	}
	
	
}