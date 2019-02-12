package uso;

import java.util.*;
import java.util.Map.Entry;

import control.*;
import modelo.*;

public class Llamada {

	public static void main(String[] args) {
		Ejercicio ejercicio = new Ejercicio();

		// Leer el fichero de equipos y crear un arraylist de objetos Equipo
		ArrayList<Equipo> listaEquipos = Equipo.crearListaFichero("ficheros/equipos.txt", "#");

		// Crear una lista de jugadores aleatorios y escribirlo en un fichero
		// ArrayList<Jugador> listaJugadores =
		// Aleatorio.crearListaJugadoresAleatorios(400);
		// Escritura.escribirLista(listaJugadores, "ficheros/jugadoresAleatorios.txt", "#");

		// Crear una lista de partidos aleatorios y escribirlo en un fichero
		// ArrayList<Partido> listaPartidos =
		// Aleatorio.crearListaPartidosAleatorios(listaEquipos);
		// Escritura.escribirLista(listaPartidos, "ficheros/partidosAleatorios.txt", "#");

		ArrayList<Partido> listaPartidos = Partido.crearListaFichero("D:/partidos.txt", "#");
		HashMap<String, ArrayList<Integer>> mapa = ejercicio.getGEPMap(listaPartidos);

		HashMap<String, Clasificacion> mapaClasificacion = Clasificacion.getMapClasificacion(listaPartidos);

		ArrayList<Clasificacion> listaClasificacion = new ArrayList<Clasificacion>(mapaClasificacion.values());
		
		Clasificacion.ordenarMapaClasificacion(listaClasificacion);
		
		HashMap<String, Integer> puntuaciones = ejercicio.generaPuntosEquipos(mapa);
		ejercicio.ordenarMapaPuntosEquipos(puntuaciones);
		
		ejercicio.pruebaSwing();
		
		//ejercicio.mostrarClasificacion(ejercicio.puntuacionEquipos(listaPartidos));
		
		HashMap<String, ArrayList<Integer>> clasificacion = ejercicio.creaClasificacion("D:/partidos.txt");
		List<Entry<String, Integer>> ordenado = ejercicio.ordenarMapaClasificacion(clasificacion);

		System.out.println("FIN DEL PROGRAMA");
	}
}
