package control;

import java.util.*;

import modelo.*;

public class Llamada {

	public static void main(String[] args) {
		Utilidad util = new Utilidad();
		
		// Crear una lista de jugadores con datos aleatorios
		//ArrayList<Jugador> listaJugadores = util.crearListaJugadores(400);
		 
		// Se guarda en un fichero
		//util.writeListaJugadores(listaJugadores, "ficheros/jugadores.txt", "#");
		
		// Crear la lista de equipos a partir de un fichero
		ArrayList<Equipo> equipos = util.crearListaEquipos("ficheros/equipos.txt", "#");
		
		// Crea la lista de partidos
		ArrayList<Partido> partidos = util.crearListaPartidos(equipos);
		
		// Se guarda en un fichero
		util.writeListaPartidos(partidos, "ficheros/partidos.txt", "#");
		
		System.out.println("FIN DEL PROGRAMA");
	}
}
