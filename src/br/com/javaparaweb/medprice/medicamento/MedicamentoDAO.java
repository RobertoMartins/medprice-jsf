package br.com.javaparaweb.medprice.medicamento;

import java.util.List;
import br.com.javaparaweb.medprice.usuario.Usuario;

public interface MedicamentoDAO {
	
	public List<Medicamento> listar();
}
