package br.com.javaparaweb.medprice.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.medicamento.MedicamentoRN;
import br.com.javaparaweb.medprice.usuario.Usuario;

@ManagedBean(name = "medicamentoBean")
@ViewScoped

public class MedicamentoBean {
	private Medicamento med ;
	private List<Medicamento> lista;
	private List<Medicamento> listaHome;
	Usuario u = new Usuario();
	ContextoBean c = new ContextoBean();
	MedicamentoRN medRN = new MedicamentoRN();
	private String registro;
	private String textoPesquisa="";
	

	//médoto chamado para pesquisar medicamentos que contém a string passada
	public void pesquisar() {
		
		  MedicamentoRN medRN = new MedicamentoRN();
		  this.lista = medRN.buscarPorString(this.textoPesquisa) ;
		

	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}

	public String getTextoPesquisa() {
		return textoPesquisa;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	public Medicamento getMed() {
		return med;
	}

	public void setMed(Medicamento med) {
		this.med = med;
	}

	public List<Medicamento> getLista() {
		if (this.lista == null) {
			MedicamentoRN medRN = new MedicamentoRN();
			this.lista = medRN.listar();
		}
		return this.lista;
	}

	//sublista de medicamentos mostrada na tela inicial.
	public List<Medicamento> getListaHome() {
		if (this.listaHome == null) {
			MedicamentoRN medRN = new MedicamentoRN();
			this.listaHome = medRN.listar().subList(0, 3);
		}
		return this.listaHome;

	}

	public void setListaHome(List<Medicamento> listaHome) {
		this.listaHome = listaHome;
	}

	public void setLista(List<Medicamento> lista) {
		this.lista = lista;
	}
	

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}
	
}
