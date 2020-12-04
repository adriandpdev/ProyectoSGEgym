package main;

import java.util.Calendar;

public class Fecha {

	Calendar fecha = Calendar.getInstance();

	int dia = fecha.get(Calendar.DAY_OF_MONTH);
	int mes = fecha.get(Calendar.MONTH) + 1;
	int año = fecha.get(Calendar.YEAR);

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

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String fechaActual() {
		String fecha = año + "-" + mes + "-" + dia;

		return fecha;
	}

}
