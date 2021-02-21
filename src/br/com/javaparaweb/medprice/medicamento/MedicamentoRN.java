package br.com.javaparaweb.medprice.medicamento;

import java.util.List;

import br.com.javaparaweb.medprice.comentario.Comentario;
import br.com.javaparaweb.medprice.util.DAOFactory;

public class MedicamentoRN {
	private MedicamentoDAO medicamentoDAO;
	
	public MedicamentoRN() {
		this.medicamentoDAO = DAOFactory.criarMedicamentoDAO();
	}
	
	public List<Medicamento> listar(){
		return this.medicamentoDAO.listar();
	}
	
	public List<Comentario> listarComentario(Medicamento medicamento){
		return this.medicamentoDAO.listarComentario(medicamento);
	}
	public Medicamento buscarPorRegistro(String registro) {
		return this.medicamentoDAO.buscarPorRegistro(registro);
	}
	
	public List<Medicamento> buscarPorString(String pesquisa){
		return this.medicamentoDAO.buscarPorString(pesquisa);
	}
	
}
