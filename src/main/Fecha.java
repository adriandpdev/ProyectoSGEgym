package main;

import java.util.Calendar;

public class Fecha {

	Calendar fecha = Calendar.getInstance();

	int dia = fecha.get(Calendar.DAY_OF_MONTH);
	int mes = fecha.get(Calendar.MONTH) + 1;
	int a�o = fecha.get(Calendar.YEAR);

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getA�o() {
		return a�o;
	}

	public void setA�o(int a�o) {
		this.a�o = a�o;
	}

	public String fechaActual() {
		String fecha = a�o + "-" + mes + "-" + dia;

		return fecha;
	}

}
