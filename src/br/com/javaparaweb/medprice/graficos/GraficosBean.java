package br.com.javaparaweb.medprice.graficos;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;

import br.com.javaparaweb.medprice.medicamento.Medicamento;
import br.com.javaparaweb.medprice.medicamento.MedicamentoRN;

@ManagedBean(name = "graficosBean")
@SessionScoped
public class GraficosBean {

	private BarChartModel precoColunas;

	private PieChartModel tipoPizza;

	private List<Medicamento> medicamentos;

	public GraficosBean() {

		// lista de todos os medicamentos
		medicamentos = this.getLista();

		// criando grafico de colunas
		this.criaGrafColuna(medicamentos);
		this.criaGrafPizza(medicamentos);

	}

	public void criaGrafColuna(List<Medicamento> medicamentos) {
		// grupos
		int gp1 = 0, gp2 = 0, gp3 = 0, gp4 = 0, gp5 = 0, gp6 = 0;
		// percorrer a lista e separar em grupos
		for (int i = 0; i < medicamentos.size(); i++) {

			if (medicamentos.get(i).getPmc0() < 9.99) {
				gp1++;

			} else if (medicamentos.get(i).getPmc0() < 99.99) {
				gp2++;

			} else if (medicamentos.get(i).getPmc0() < 299.99) {
				gp3++;

			} else if (medicamentos.get(i).getPmc0() < 499.99) {
				gp4++;

			} else if (medicamentos.get(i).getPmc0() < 999.99) {
				gp5++;

			} else if (medicamentos.get(i).getPmc0() >= 1000) {
				gp6++;

			}

		}

		ChartSeries meds = new ChartSeries();

		this.precoColunas = new BarChartModel();

		meds.setLabel("Quantidade de Medicamentos");
		meds.set("< 9,99", gp1);
		meds.set("10-99", gp2);
		meds.set("100-299", gp3);
		meds.set("300-499", gp4);
		meds.set("500-999", gp5);
		meds.set("1000+", gp6);

		this.precoColunas.addSeries(meds);

		this.precoColunas.setTitle("Quantidade de medicamentos por grupo de preço");
		this.precoColunas.setLegendPosition("ne");
		Axis xAxis = this.precoColunas.getAxis(AxisType.X);
		xAxis.setLabel("Intervalo de preços");

		Axis yAxis = this.precoColunas.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade");
		yAxis.setMin(0);
		yAxis.setMax(14000);

	}

	public void criaGrafPizza(List<Medicamento> medicamentos) {

		int gp1 = 0, gp2 = 0, gp3 = 0, gp4 = 0, gp5 = 0, gp6 = 0, gp7 = 0;

		for (int i = 0; i < medicamentos.size(); i++) {
			if (medicamentos.get(i).getTipo().equals("Biológico")) {
				gp1++;
			} else if (medicamentos.get(i).getTipo().equals("Específico")) {
				gp2++;
			} else if (medicamentos.get(i).getTipo().equals("Fitoterápico")) {
				gp3++;
			} else if (medicamentos.get(i).getTipo().equals("Genérico")) {
				gp4++;
			} else if (medicamentos.get(i).getTipo().equals("Novo")) {
				gp5++;
			} else if (medicamentos.get(i).getTipo().equals("Similar")) {
				gp6++;
			}else if(medicamentos.get(i).getTipo().equals("Radiofármaco")) {
				gp7++;
			}
		}

		this.tipoPizza = new PieChartModel();
		this.tipoPizza.set("Biologico", gp1);
		this.tipoPizza.set("Específico", gp2);
		this.tipoPizza.set("Fitoterápico", gp3);
		this.tipoPizza.set("Genérico", gp4);
		this.tipoPizza.set("Novo", gp5);
		this.tipoPizza.set("Similar", gp6);
		this.tipoPizza.set("Radiofármaco", gp7);
		this.tipoPizza.setTitle("Medicamentos por tipo");
		this.tipoPizza.setLegendPosition("e");
		this.tipoPizza.setShowDataLabels(true);
		this.tipoPizza.setDataFormat("percent");
	}

	public PieChartModel getTipoPizza() {
		return tipoPizza;
	}

	public void setTipoPizza(PieChartModel tipoPizza) {
		this.tipoPizza = tipoPizza;
	}

	public BarChartModel getPrecoColunas() {
		return this.precoColunas;
	}

	private List<Medicamento> getLista() {
		MedicamentoRN mRN = new MedicamentoRN();
		return mRN.listar();
	}
}
