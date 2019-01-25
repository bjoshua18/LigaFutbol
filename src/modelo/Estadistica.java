package modelo;

public class Estadistica {
	
	//ATRIBUTOS
	private String equipo;
	private int partidosGanados;
	private int partidosEmpate;
	private int partidosPerdidos;
	private int numGolesFavor;
	private int numGolesContra;
	private int puntuacion;
	
	public Estadistica(String nombreCortoEquipo) {
		this.equipo = nombreCortoEquipo;
		this.partidosGanados = 0;
		this.partidosEmpate = 0;
		this.partidosPerdidos = 0;
		this.numGolesFavor = 0;
		this.numGolesContra = 0;
		this.puntuacion = 0;
	}
	
	//GETTERS Y SETTERS
	public int getPartidosGanados() {
		return partidosGanados;
	}
	public int getPartidosEmpate() {
		return partidosEmpate;
	}
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
	public int getNumGolesFavor() {
		return numGolesFavor;
	}
	public int getNumGolesContra() {
		return numGolesContra;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}
	public void setPartidosEmpate(int partidosEmpate) {
		this.partidosEmpate = partidosEmpate;
	}
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}
	public void setNumGolesFavor(int numGolesFavor) {
		this.numGolesFavor = numGolesFavor;
	}
	public void setNumGolesContra(int numGolesContra) {
		this.numGolesContra = numGolesContra;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
	
}
