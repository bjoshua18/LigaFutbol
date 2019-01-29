package modelo;

import java.util.ArrayList;

import control.Clase;
import control.Lista;

public class Jugador extends Persona {

	private int idJugador;
	private int dorsal;
	private int idEquipo;

	public Jugador(String nif, String nombre, int longitudPaso, String fecha_nac, char sexo, int idJugador, int dorsal,
			int idEquipo) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);
		this.idJugador = idJugador;
		this.dorsal = dorsal;
		this.idEquipo = idEquipo;
	}

	public Jugador(String[] atributos) throws NumberFormatException {
		this(atributos[0], atributos[1], Integer.parseInt(atributos[2]), atributos[3], atributos[4].charAt(0),
				Integer.parseInt(atributos[5]), Integer.parseInt(atributos[6]), Integer.parseInt(atributos[6]));
	}

	public int getIdJugador() {
		return idJugador;
	}

	public int getDorsal() {
		return dorsal;
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public String getLineaStringCampos(String separador) {
		return this.getNif() + separador + this.getNombre() + separador + this.getLongitudPaso() + separador
				+ this.getFecha_nac() + separador + this.getSexo() + separador + this.getIdJugador() + separador
				+ this.getDorsal() + separador + this.getIdEquipo();
	}

	public static ArrayList<Jugador> crearListaFichero(String rutaFichero, String separador) {
		return Lista.crearLista(Clase.JUGADOR, rutaFichero, separador);
	}
}