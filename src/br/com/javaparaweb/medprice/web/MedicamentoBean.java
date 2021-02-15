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
	public Medicamento getMed() {
		return med;
	}
	public void setMed(Medicamento med) {
		this.med = med;
	}
	public List<Medicamento> getLista() {
		if(this.lista == null) {
			MedicamentoRN medRN = new MedicamentoRN();
			this.lista = medRN.listar();
		}
		return this.lista;
	}
	
	
	public List<Medicamento> getListaHome() {
		if(this.listaHome == null) {
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
	
	
	public String detalhe() {
		return "meddetalhe";
	}
	

}
