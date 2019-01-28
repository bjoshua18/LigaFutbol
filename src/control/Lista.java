package control;

import java.util.ArrayList;

import modelo.Clasificacion;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;

public class Lista {

	public static <T> ArrayList<T> crearLista(Enum<Clase> clase, String rutaFichero, String separador) {
		ArrayList<T> lista = new ArrayList<T>();
		ArrayList<String> lineasFichero = Lectura.leerFichero(rutaFichero);
		addObjetosEnLista(lineasFichero, separador, lista, clase);
		
		return lista;
	}

	private static <T> void addObjetosEnLista(ArrayList<String> lineasFichero, String separador, ArrayList<T> lista,
			Enum<Clase> clase) {
		for (String linea : lineasFichero)
			addObjetoEnLista(linea.split(separador), lista, clase);
	}

	private static <T> void addObjetoEnLista(String[] atributos, ArrayList<T> lista, Enum<Clase> clase) {
		if (clase == Clase.EQUIPO)
			lista.add((T) new Equipo(atributos));
		else if (clase == Clase.JUGADOR)
			lista.add((T) new Jugador(atributos));
		else if (clase == Clase.EQUIPO)
			lista.add((T) new Partido(atributos));
		else if (clase == Clase.CLASIFICACION)
			lista.add((T) new Clasificacion(atributos));
		else
			System.out.println("La clase no está contemplada.");
	}
}
