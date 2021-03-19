package br.com.javaparaweb.medprice.util;

import java.util.Date;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.javaparaweb.medprice.util.UtilException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GmailUtil {
	// https://developers.google.com/gmail/api/guides/sending

	public static void enviarEmail(String para, String assunto, String mensagem) throws UtilException {
		Properties prop = new Properties();
		
		String email = "medpriceweb@gmail.com";
		String password = "WEB2projeto";
		
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(email, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(email));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(para));
			message.setSubject(assunto);
			message.setText(mensagem);
			Transport.send(message);

			System.out.println("Email Enviado");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}