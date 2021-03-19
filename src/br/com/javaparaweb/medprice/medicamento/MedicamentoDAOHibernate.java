package br.com.javaparaweb.medprice.medicamento;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.usuario.Usuario;

public class MedicamentoDAOHibernate implements MedicamentoDAO {

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> listar() {
		Criteria crit = session.createCriteria(Medicamento.class);
		List<Medicamento> results = crit.list();
		return results;
	}

	@Override
	public List<Comentario> listarComentario(Medicamento medicamento) {
		Criteria crit = session.createCriteria(Comentario.class);
		List<Comentario> results = crit.list();
		return results;
	}

	@Override
	public Medicamento buscarPorRegistro(String registro) {

		String hql = "select m from Medicamento m where m.registro = :registro";
		Query consulta = this.session.createQuery(hql);
		consulta.setString("registro", registro);
		return (Medicamento) consulta.uniqueResult();

	}

	@Override
	public List<Medicamento> buscarPorString(String pesquisa) {
		Criteria crit = session.createCriteria(Medicamento.class);
		crit.add(Restrictions.ilike("produto",pesquisa+"%"));
		List<Medicamento> results = crit.list();
		return results;
		
	}
	@Override
	public void favoritar(Medicamento med) {
		this.session.save(med);
	}

	
}
