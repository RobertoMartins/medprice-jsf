package br.com.javaparaweb.medprice.comentario;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;


import br.com.javaparaweb.medprice.usuario.Usuario;

public class ComentarioDAOHibernate implements ComentarioDAO{
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public Comentario salvar(Comentario comentario) {
		Comentario merged = (Comentario) this.session.merge(comentario);
		this.session.flush();
		this.session.clear();
		return merged;
	}

	@Override
	public void excluir(Comentario comentario) {
		comentario = (Comentario) this.carregar(comentario.getIdCom());
		this.session.delete(comentario);
		this.session.flush();
		this.session.clear();
	}

	@Override
	public Comentario carregar(Integer comentario) {
		return (Comentario) this.session.get(Comentario.class, comentario);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> listar(Usuario usuario) {
		String hql = "select c from Comentario c where c.pai is null and c.usuario = :usuario order by c.data desc";
		Query query = this.session.createQuery(hql);
		query.setInteger("usuario", usuario.getIdUsuario());
		List<Comentario> lista = query.list();
		lista.size();
		
		return lista;
	}
}
