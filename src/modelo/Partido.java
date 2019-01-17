package modelo;

public class Partido {
	
	private int idPartido;
	private int jornada;
	private String equipoLocal;
	private int golesLocal;
	private String equipoVisitante;
	private int golesVisitante;

	public Partido(int idPartido, int jornada, String equipoLocal, int golesLocal, String equipoVisitante, int golesVisitante) {
		this.idPartido = idPartido;
		this.jornada = jornada;
		this.equipoLocal = equipoLocal;
		this.golesLocal = golesLocal;
		this.equipoVisitante = equipoVisitante;
		this.golesVisitante = golesVisitante;
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

	
}