package uso;

import java.util.*;

import control.*;
import modelo.*;

public class Llamada {

	public static void main(String[] args) {
		Ejercicio ejercicio = new Ejercicio();
		
		// Leer el fichero de equipos y crear un arraylist de objetos Equipo
		ArrayList<Equipo> listaEquipos = Lectura.crearListaEquipos("ficheros/equipos.txt", "#");
		
		// Crear una lista de jugadores aleatorios y escribirlo en un fichero
		ArrayList<Jugador> listaJugadores = Aleatorio.crearListaJugadoresAleatorios(400);
		Escritura.escribirLista(listaJugadores, "ficheros/jugadoresAleatorios", "#");
		
		// Crear una lista de partidos aleatorios y escribirlo en un fichero
		ArrayList<Partido> listaPartidos = Aleatorio.crearListaPartidosAleatorios(listaEquipos);
		Escritura.escribirLista(listaPartidos, "ficheros/partidosAleatorios", "#");
		
		System.out.println("FIN DEL PROGRAMA");
	}
}
