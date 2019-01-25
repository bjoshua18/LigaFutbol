package control;

import java.util.*;
import modelo.*;

public class Ejercicio {
	
	// 23/01/19
	
	// Método que devuelve un HashMap con todos los partidos y sus estadísticas GEP
	public HashMap<String, Estadistica> getMapEstadisticaGEP(ArrayList<Partido> partidos) {
		HashMap<String, Estadistica> mapa = new HashMap<String, Estadistica>();
		
		// Por hacer....
		
		return mapa;
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
			equipos.replace(equipo, equipos.get(equipo)+1);
			
	}
}
