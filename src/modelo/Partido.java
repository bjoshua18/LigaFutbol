package modelo;

import java.util.ArrayList;

import control.Clase;
import control.Lista;

public class Partido {

	private int idPartido;
	private int jornada;
	private String equipoLocal;
	private int golesLocal;
	private String equipoVisitante;
	private int golesVisitante;

	public Partido(int idPartido, int jornada, String equipoLocal, int golesLocal, String equipoVisitante,
			int golesVisitante) {
		this.idPartido = idPartido;
		this.jornada = jornada;
		this.equipoLocal = equipoLocal;
		this.golesLocal = golesLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesVisitante = golesVisitante;
	}

	public Partido(String[] atributos) throws NumberFormatException {
		this(Integer.parseInt(atributos[0]), Integer.parseInt(atributos[1]), atributos[2],
				Integer.parseInt(atributos[3]), atributos[4], Integer.parseInt(atributos[5]));
	}

	public int getIdPartido() {
		return idPartido;
	}

	public int getJornada() {
		return jornada;
	}

	public String getEquipoLocal() {
		return equipoLocal;
	}

	public int getGolesLocal() {
		return golesLocal;
	}

	public String getEquipoVisitante() {
		return equipoVisitante;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}

	public void setEquipoLocal(String equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public void setEquipoVisitante(String equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public String getLineaStringCampos(String separador) {
		return this.getIdPartido() + separador + this.getJornada() + separador + this.getEquipoLocal() + separador
				+ this.getGolesLocal() + separador + this.getEquipoVisitante() + separador + this.getGolesVisitante();
	}

	public static ArrayList<Partido> crearListaFichero(String rutaFichero, String separador) {
		return Lista.crearLista(Clase.PARTIDO, rutaFichero, separador);
	}
}