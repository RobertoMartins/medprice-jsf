package br.com.javaparaweb.medprice.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.medicamento.MedicamentoRN;
import br.com.javaparaweb.medprice.usuario.UsuarioRN;

@ManagedBean(name = "medicamentoBean")
@ViewScoped
public class MedicamentoBean {
	private Medicamento med ;
	private List<Medicamento> lista;
	private List<Medicamento> listaHome;
	ContextoBean c = new ContextoBean();


	
	private String registro;

	private String textoPesquisa="";
	

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
	
	
	public String favoritar() {
		UsuarioRN usuarioRN = new UsuarioRN();
		c.getUsuarioLogado().getMedicamentos().add(this.med);
		usuarioRN.salvar(c.getUsuarioLogado());
		System.out.println(c.getUsuarioLogado().getMedicamentos().size());
		
		return "medicamento";
	}
	
}
