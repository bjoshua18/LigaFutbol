package control;

import java.util.*;
import modelo.*;

public class Ejercicio {
	
	public HashMap<String, Integer> puntuacionEquipos(ArrayList<Partido> listaPartidos) {
		HashMap<String, Integer> puntuaciones = new HashMap<String, Integer>();
		HashMap<String, ArrayList<Integer>> mapaEstadisticas = getGEPMap(listaPartidos);
		Set<String> clavesMapa = mapaEstadisticas.keySet();
		
		for (String clave : clavesMapa)
			puntuaciones.put(clave, getPuntuacion(mapaEstadisticas.get(clave)));
		
		return puntuaciones;
	}
	
	private Integer getPuntuacion(ArrayList<Integer> listaGEP) {
		return 3 * listaGEP.get(0) + listaGEP.get(1);
	}

	// (Con Rita) Método que devuelve un HashMap con todos los equipos y sus
	// estadísticas en un ArrayList

	public HashMap<String, ArrayList<Integer>> getGEPMap(ArrayList<Partido> listaPartidos) {
		HashMap<String, ArrayList<Integer>> mapa = new HashMap<String, ArrayList<Integer>>();

		for (Partido partido : listaPartidos) {
			introduceEquiposEnMapa(partido, mapa);
			setEstadisticasEquipos(partido, mapa);
		}

		return mapa;
	}

	private void setEstadisticasEquipos(Partido partido, HashMap<String, ArrayList<Integer>> mapa) {
		switch(esGanador(partido.getGolesLocal(), partido.getGolesVisitante())) {
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

	private void setGEP(String equipo1, String equipo2, HashMap<String, ArrayList<Integer>> mapa, boolean esEmpate) {
		if(!esEmpate) {
			mapa.get(equipo1).set(0, mapa.get(equipo1).get(0)+1);
			mapa.get(equipo2).set(2, mapa.get(equipo2).get(2)+1);
		} else {
			mapa.get(equipo1).set(1, mapa.get(equipo1).get(1)+1);
			mapa.get(equipo2).set(1, mapa.get(equipo2).get(1)+1);
		}
	}

	private byte esGanador(int goles1, int goles2) {
		if (goles1 > goles2)
			return 1; // This partido gana
		else if (goles1 < goles2)
			return -1; // This partido pierde
		else
			return 0; // Empate
	}

	private void introduceEquiposEnMapa(Partido partido, HashMap<String, ArrayList<Integer>> mapa) {
		introduceEquipoEnMapa(partido.getEquipoLocal(), mapa);
		introduceEquipoEnMapa(partido.getEquipoVisitante(), mapa);
	}

	private void introduceEquipoEnMapa(String equipo, HashMap<String, ArrayList<Integer>> mapa) {
		if (!mapa.containsKey(equipo))
			mapa.put(equipo, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
	}

	// 22/01/19
	// Método que devuelve un HashMap con todos los equipos y sus partidos jugados
	public HashMap<String, Integer> comprobarPartidos(ArrayList<Partido> partidos) {
		HashMap<String, Integer> equipos = new HashMap<String, Integer>();

		for (int i = 0; i < partidos.size(); i++) {
			sumarPartidoAEquipo(equipos, partidos.get(i).getEquipoLocal());
			sumarPartidoAEquipo(equipos, partidos.get(i).getEquipoVisitante());
		}

		return equipos;
	}

	public void sumarPartidoAEquipo(HashMap<String, Integer> equipos, String equipo) {
		if (!equipos.containsKey(equipo))
			equipos.put(equipo, 1);
		else
			equipos.replace(equipo, equipos.get(equipo) + 1);
	}
}
