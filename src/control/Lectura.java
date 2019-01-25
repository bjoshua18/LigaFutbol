package control;

import java.io.*;
import java.util.ArrayList;

import modelo.*;

public class Lectura {

	static ArrayList<String> leerFichero(String rutaFichero) {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(rutaFichero));

			ArrayList<String> lineasFichero = new ArrayList<String>();

			String registro;

			while ((registro = fichero.readLine()) != null)
				lineasFichero.add(registro);

			fichero.close();
			return lineasFichero;

		} catch (IOException e) {
			System.out.println("IO Exception");
			return null;
		}
	}

	private static <T> ArrayList<T> crearLista(String clase, String rutaFichero, String separador) {
		ArrayList<T> lista = new ArrayList<T>();
		ArrayList<String> lineasFichero = leerFichero(rutaFichero);
		addObjetosEnLista(lineasFichero, separador, lista, clase);
		
		return lista;
	}

	private static <T> void addObjetosEnLista(ArrayList<String> lineasFichero, String separador, ArrayList<T> lista,
			String clase) {
		for (String linea : lineasFichero)
			addObjetoEnLista(linea.split(separador), lista, clase);
	}

	@SuppressWarnings("unchecked")
	private static <T> void addObjetoEnLista(String[] atributos, ArrayList<T> lista, String clase) {
		if (clase.equals("modelo.Equipo")) {
			lista.add((T) crearEquipo(atributos));
		} else if (clase.equals("modelo.Jugador")) {
			lista.add((T) crearJugador(atributos));
		} else if (clase.equals("modelo.Partido")) {
			lista.add((T) crearPartido(atributos));
		} else {
			System.out.println("La clase no está contemplada.");
		}
	}

	private static Equipo crearEquipo(String[] atributos) {
		return new Equipo(Integer.parseInt(atributos[0]), atributos[1], atributos[2]);
	}

	private static Jugador crearJugador(String[] atributos) {
		return new Jugador(atributos[0], atributos[1], Integer.parseInt(atributos[2]), atributos[3],
				atributos[4].charAt(0), Integer.parseInt(atributos[5]), Integer.parseInt(atributos[6]),
				Integer.parseInt(atributos[6]));
	}

	private static Partido crearPartido(String[] atributos) {
		return new Partido(Integer.parseInt(atributos[0]), Integer.parseInt(atributos[1]), atributos[2],
				Integer.parseInt(atributos[3]), atributos[4], Integer.parseInt(atributos[5]));
	}

	public static ArrayList<Equipo> crearListaEquipos(String rutaFichero, String separador) {
		return crearLista("modelo.Equipo", rutaFichero, separador);
	}

	public static ArrayList<Jugador> crearListaJugadores(String rutaFichero, String separador) {
		return crearLista("modelo.Jugador", rutaFichero, separador);
	}

	public static ArrayList<Partido> crearListaPartidos(String rutaFichero, String separador) {
		return crearLista("modelo.Partido", rutaFichero, separador);
	}
}