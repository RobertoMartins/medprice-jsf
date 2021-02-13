package br.com.javaparaweb.medprice.medicamento;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;

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

}
