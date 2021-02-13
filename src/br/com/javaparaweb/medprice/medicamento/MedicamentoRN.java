package br.com.javaparaweb.medprice.medicamento;

import java.util.List;

import br.com.javaparaweb.medprice.usuario.Usuario;
import br.com.javaparaweb.medprice.util.DAOFactory;

public class MedicamentoRN {
	private MedicamentoDAO medicamentoDAO;
	
	public MedicamentoRN() {
		this.medicamentoDAO = DAOFactory.criarMedicamentoDAO();
	}
	
	public List<Medicamento> listar(){
		return this.medicamentoDAO.listar();
	}
	
}
