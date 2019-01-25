package modelo;

public class Jugador extends Persona {
	
	private int idJugador;
	private int dorsal;
	private int idEquipo;

	public Jugador(String nif, String nombre, int longitudPaso, String fecha_nac, char sexo,int idJugador, int dorsal, int idEquipo) {
		super(nif, nombre, longitudPaso, fecha_nac, sexo);
		this.idJugador = idJugador;
		this.dorsal = dorsal;
		this.idEquipo = idEquipo;
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
				+ this.getDorsal() + separador+this.getIdEquipo();
	}
}