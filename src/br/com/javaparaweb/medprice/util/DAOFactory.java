package br.com.javaparaweb.medprice.util;

import br.com.javaparaweb.medprice.comentario.ComentarioDAO;
import br.com.javaparaweb.medprice.comentario.ComentarioDAOHibernate;
import br.com.javaparaweb.medprice.medicamento.MedicamentoDAO;
import br.com.javaparaweb.medprice.medicamento.MedicamentoDAOHibernate;
import br.com.javaparaweb.medprice.usuario.UsuarioDAO;
import br.com.javaparaweb.medprice.usuario.UsuarioDAOHibernate;


public class DAOFactory {

	public static UsuarioDAO criarUsuarioDAO() {
		UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return usuarioDAO;
	}
	
	public static MedicamentoDAO criarMedicamentoDAO() {
		MedicamentoDAOHibernate MedicamentoDAO = new MedicamentoDAOHibernate();
		MedicamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return MedicamentoDAO;
	}
	
	public static ComentarioDAO criarComentarioDAO() {
		ComentarioDAOHibernate ComentarioDAO = new ComentarioDAOHibernate();
		ComentarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		return ComentarioDAO;
	}
	
}
