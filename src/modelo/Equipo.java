package modelo;

import java.util.ArrayList;

import control.Clase;
import control.Lista;

public class Equipo {

	// Fields
	private int idEquipo;
	private String nombreCorto;
	private String nombre;

	// Constructor
	public Equipo(int idEquipo, String nombreCorto, String nombre) {
		this.idEquipo = idEquipo;
		this.nombreCorto = nombreCorto;
		this.nombre = nombre;
	}
	
	public Equipo(String[] atributos) {
		this(Integer.parseInt(atributos[0]), atributos[1], atributos[2]);
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLineaStringCampos(String separador) {
		return this.getIdEquipo() + separador + this.getNombreCorto() + separador + this.getNombre();
	}

	public static ArrayList<Equipo> crearListaFichero(String rutaFichero, String separador) {
		return Lista.crearLista(Clase.EQUIPO, rutaFichero, separador);
	}
}