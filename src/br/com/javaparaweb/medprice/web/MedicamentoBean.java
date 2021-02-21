package br.com.javaparaweb.medprice.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.medicamento.MedicamentoRN;

@ManagedBean(name = "medicamentoBean")
@RequestScoped
public class MedicamentoBean {
	private Medicamento med = new Medicamento();
	private List<Medicamento> lista;
	private List<Medicamento> listaHome;

	
	private String registro;

	private String textoPesquisa;
	

	public void pesquisar() {
		
		  MedicamentoRN medRN = new MedicamentoRN();
		  this.lista = medRN.buscarPorString(this.textoPesquisa) ;
		

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

	public String detalhe() {
		ContextoBean c = new ContextoBean();
		System.out.println(registro);
		this.med= c.medicamentoContext(registro);
		return "meddetalhe";
	}

}
