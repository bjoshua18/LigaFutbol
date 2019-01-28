package control;

import java.io.*;
import java.util.ArrayList;

import modelo.*;

public class Lectura {

	public static ArrayList<String> leerFichero(String rutaFichero) {
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
}