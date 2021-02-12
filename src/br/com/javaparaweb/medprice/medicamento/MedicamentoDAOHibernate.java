package br.com.javaparaweb.medprice.medicamento;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import br.com.javaparaweb.medprice.usuario.Usuario;

public class MedicamentoDAOHibernate implements MedicamentoDAO {
	
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> listar(Usuario usuario) {

		String hql = "select c from Medicamento c where c.usuario = :usuario";
		Query query = this.session.createQuery(hql);
		query.setInteger("usuario", usuario.getIdUsuario());

		List<Medicamento> lista = query.list();

		return lista;
	}

}
