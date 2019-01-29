package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import control.Clase;
import control.Lista;

public class Clasificacion {

	// ATRIBUTOS
	private String equipo;
	private int puntuacion;
	private int partidosGanados;
	private int partidosEmpate;
	private int partidosPerdidos;
	private int partidosJugados;
	private int numGolesFavor;
	private int numGolesContra;
	private int diferenciaGoles;

	public Clasificacion(String nombreCortoEquipo) {
		this.equipo = nombreCortoEquipo;
		this.puntuacion = 0;
		this.partidosGanados = 0;
		this.partidosEmpate = 0;
		this.partidosPerdidos = 0;
		this.numGolesFavor = 0;
		this.numGolesContra = 0;
		this.diferenciaGoles = 0;
	}

	public Clasificacion(String equipo, int puntuacion, int partidosGanados, int partidosEmpate, int partidosPerdidos,
			int partidosJugados, int numGolesFavor, int numGolesContra) {
		this.equipo = equipo;
		this.puntuacion = puntuacion;
		this.partidosGanados = partidosGanados;
		this.partidosEmpate = partidosEmpate;
		this.partidosPerdidos = partidosPerdidos;
		this.partidosJugados = partidosJugados;
		this.numGolesFavor = numGolesFavor;
		this.numGolesContra = numGolesContra;
		this.diferenciaGoles = numGolesFavor - numGolesContra;
	}

	public Clasificacion(String[] atributos) throws NumberFormatException {
		this(atributos[0], Integer.parseInt(atributos[1]), Integer.parseInt(atributos[2]),
				Integer.parseInt(atributos[3]), Integer.parseInt(atributos[4]), Integer.parseInt(atributos[5]),
				Integer.parseInt(atributos[6]), Integer.parseInt(atributos[7]));
	}

	// GETTERS Y SETTERS
	public String getEquipo() {
		return equipo;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public int getPartidosGanados() {
		return partidosGanados;
	}

	public int getPartidosEmpate() {
		return partidosEmpate;
	}

	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public int getPartidosJugados() {
		return partidosJugados;
	}

	public int getNumGolesFavor() {
		return numGolesFavor;
	}

	public int getNumGolesContra() {
		return numGolesContra;
	}

	public int getDiferenciaGoles() {
		return diferenciaGoles;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
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

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public void setNumGolesFavor(int numGolesFavor) {
		this.numGolesFavor = numGolesFavor;
	}

	public void setNumGolesContra(int numGolesContra) {
		this.numGolesContra = numGolesContra;
	}

	public void setDiferenciaGoles(int diferenciaGoles) {
		this.diferenciaGoles = diferenciaGoles;
	}

	public String getLineaStringCampos(String separador) {
		return this.getEquipo() + separador + this.getPuntuacion() + separador + this.getPartidosGanados() + separador
				+ this.getPartidosEmpate() + separador + this.getPartidosPerdidos() + separador
				+ this.getPartidosJugados() + separador + this.getNumGolesFavor() + separador + this.getNumGolesContra()
				+ separador + this.getDiferenciaGoles();
	}

	public static ArrayList<Clasificacion> crearListaFichero(String rutaFichero, String separador) {
		return Lista.crearLista(Clase.CLASIFICACION, rutaFichero, separador);
	}

	public static void ordenarMapaClasificacion(ArrayList<Clasificacion> listaClasificaciones) {
		Clasificacion max;
		for (int i = 0; i < listaClasificaciones.size() - 1; i++) {
			max = listaClasificaciones.get(i);

			for (int j = i + 1; j < listaClasificaciones.size(); j++)
				max = getClasificacionMayor(max, listaClasificaciones.get(j));

			intercambiarPosiciones(listaClasificaciones.get(i), i, max, listaClasificaciones.indexOf(max),
					listaClasificaciones);
		}
	}

	private static Clasificacion getClasificacionMayor(Clasificacion max, Clasificacion comprobado) {
		if (max.getPuntuacion() < comprobado.getPuntuacion()) {
			return comprobado;
		} else if (max.getPuntuacion() == comprobado.getPuntuacion()) {
			if (max.getDiferenciaGoles() < comprobado.getDiferenciaGoles()) {
				return comprobado;
			} else if (max.getDiferenciaGoles() == comprobado.getDiferenciaGoles()
					&& max.getNumGolesFavor() < comprobado.getNumGolesFavor()) {
				return comprobado;
			}
		}
		return max;
	}

	private static void intercambiarPosiciones(Clasificacion clasificacion, int indexClasificacion, Clasificacion max,
			int indexMax, ArrayList<Clasificacion> listaClasificaciones) {
		Clasificacion aux;
		aux = clasificacion;
		listaClasificaciones.set(indexClasificacion, max);
		listaClasificaciones.set(indexMax, aux);

	}

	public static HashMap<String, Clasificacion> getMapClasificacion(ArrayList<Partido> partidos) {
		return getMapClasificacion(crearListaClavesEquipos(partidos), partidos);
	}

	public static HashMap<String, Clasificacion> getMapClasificacion(ArrayList<String> clavesEquipos,
			ArrayList<Partido> partidos) {
		HashMap<String, Clasificacion> mapa = new HashMap<String, Clasificacion>();

		addClavesMapa(clavesEquipos, mapa);
		for (Partido partido : partidos) {
			getGEPEquipos(partido, mapa);
			getPuntuacionEquipos(mapa);
			getGolesFavorYContra(partido, mapa);
		}

		return mapa;
	}

	private static void getGolesFavorYContra(Partido partido, HashMap<String, Clasificacion> mapa) {
		setNumGolesEquipo(partido.getEquipoLocal(), partido.getGolesLocal(), partido.getGolesVisitante(), mapa);
		setNumGolesEquipo(partido.getEquipoVisitante(), partido.getGolesVisitante(), partido.getGolesLocal(), mapa);
	}

	private static void setNumGolesEquipo(String equipo, int golesFavor, int golesContra,
			HashMap<String, Clasificacion> mapa) {
		Clasificacion clasificacion = mapa.get(equipo);
		clasificacion.setNumGolesFavor(clasificacion.getNumGolesFavor() + golesFavor);
		clasificacion.setNumGolesContra(clasificacion.getNumGolesContra() + golesContra);
		clasificacion.setDiferenciaGoles(clasificacion.getNumGolesFavor() - clasificacion.getNumGolesContra());
	}

	private static void getPuntuacionEquipos(HashMap<String, Clasificacion> mapa) {
		Set<String> clavesMapa = mapa.keySet();
		for (String equipo : clavesMapa) {
			Clasificacion clasificacion = mapa.get(equipo);
			clasificacion.setPuntuacion(3 * clasificacion.getPartidosGanados() + clasificacion.getPartidosEmpate());
			clasificacion.setPartidosJugados(clasificacion.getPartidosGanados() + clasificacion.getPartidosEmpate()
					+ clasificacion.getPartidosPerdidos());
		}
	}

	private static void getGEPEquipos(Partido partido, HashMap<String, Clasificacion> mapa) {
		switch (esGanador(partido.getGolesLocal(), partido.getGolesVisitante())) {
		case 1:
			setGEP(partido.getEquipoLocal(), partido.getEquipoVisitante(), mapa, false);
			break;
		case -1:
			setGEP(partido.getEquipoVisitante(), partido.getEquipoLocal(), mapa, false);
			break;
		case 0:
			setGEP(partido.getEquipoLocal(), partido.getEquipoVisitante(), mapa, true);
			break;
		}
	}

	private static void setGEP(String equipo1, String equipo2, HashMap<String, Clasificacion> mapa, boolean esEmpate) {
		if (!esEmpate) {
			mapa.get(equipo1).setPartidosGanados(mapa.get(equipo1).getPartidosGanados() + 1);
			mapa.get(equipo2).setPartidosPerdidos(mapa.get(equipo2).getPartidosPerdidos() + 1);
		} else {
			mapa.get(equipo1).setPartidosEmpate(mapa.get(equipo1).getPartidosEmpate() + 1);
			mapa.get(equipo2).setPartidosEmpate(mapa.get(equipo2).getPartidosEmpate() + 1);
		}
	}

	private static byte esGanador(int goles1, int goles2) {
		if (goles1 > goles2)
			return 1; // This partido gana
		else if (goles1 < goles2)
			return -1; // This partido pierde
		else
			return 0; // Empate
	}

	private static void addClavesMapa(ArrayList<String> clavesEquipos, HashMap<String, Clasificacion> mapa) {
		for (String clave : clavesEquipos)
			mapa.put(clave, new Clasificacion(clave));
	}

	private static ArrayList<String> crearListaClavesEquipos(ArrayList<Partido> partidos) {
		ArrayList<String> lista = new ArrayList<String>();
		for (Partido partido : partidos) {
			addEquipoEnLista(partido.getEquipoLocal(), lista);
			addEquipoEnLista(partido.getEquipoVisitante(), lista);
		}
		return lista;
	}

	private static void addEquipoEnLista(String equipo, ArrayList<String> lista) {
		if (!lista.contains(equipo))
			lista.add(equipo);
	}
}