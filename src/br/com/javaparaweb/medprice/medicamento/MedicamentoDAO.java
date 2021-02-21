package br.com.javaparaweb.medprice.medicamento;

import java.util.List;

import br.com.javaparaweb.medprice.comentario.Comentario;

public interface MedicamentoDAO {
	
	public List<Medicamento> listar();
	
	public List<Comentario> listarComentario(Medicamento medicamento);
	public Medicamento buscarPorRegistro(String registro) ;
	public List<Medicamento> buscarPorString(String pesquisa) ;
}
