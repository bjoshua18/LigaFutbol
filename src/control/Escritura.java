package control;

import java.io.*;
import java.util.ArrayList;

import modelo.*;

public class Escritura {
	public static <T> void escribirLista(ArrayList<T> lista, String rutaDestino, String separador) {
		try {
			BufferedWriter escritura = new BufferedWriter(new FileWriter(rutaDestino));

			// Para escribir newLine() delante y que la última línea no quede vacía
			escritura.write(getLineaStringCampos(lista.get(0), separador));
			for (int i = 1; i < lista.size(); i++) {
				escritura.newLine();
				escritura.write(getLineaStringCampos(lista.get(0), separador));
			}

			escritura.close();
		} catch (IOException e) {
			System.out.println("IO Exception.");
		}
	}
	
	private static String getLineaStringCampos(Object obj, String separador) {
		Equipo e;
		Jugador j;
		Partido p;
		if(obj.getClass().getName().equalsIgnoreCase("modelo.Equipo")) {
			e = (Equipo) obj;
			return e.getLineaStringCampos(separador);
		} else if(obj.getClass().getName().equalsIgnoreCase("modelo.Jugador")) {
			j = (Jugador) obj;
			return j.getLineaStringCampos(separador);
		} else if(obj.getClass().getName().equalsIgnoreCase("modelo.Partido")) {
			p = (Partido) obj;
			return p.getLineaStringCampos(separador);
		} else {
			System.out.println("La clase no está contemplada.");
			return null;
		}
	}
}
