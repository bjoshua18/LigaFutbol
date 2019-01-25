package control;

import java.text.DecimalFormat;
import java.util.ArrayList;

import modelo.*;

public class Aleatorio {

	private static int getNumeroAleatorio(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}

	private static String getNombreAleatorio(ArrayList<String> nombres, ArrayList<String> apellidos) {
		String nombre = nombres.get(getNumeroAleatorio(0, nombres.size() - 1));
		String apellido = apellidos.get(getNumeroAleatorio(0, nombres.size() - 1));
		return nombre + " " + apellido;
	}

	private static ArrayList<String> crearListaNombresAleatorios(int cantidad) {
		ArrayList<String> nombres = Lectura.leerFichero("ficheros/nombres.txt");
		ArrayList<String> apellidos = Lectura.leerFichero("ficheros/apellidos.txt");

		ArrayList<String> listaNombres = new ArrayList<String>();

		for (int i = 0; i < cantidad; i++)
			listaNombres.add(getNombreAleatorio(nombres, apellidos));

		return listaNombres;
	}

	private static String getFechaAleatoria() {
		DecimalFormat df = new DecimalFormat("00");

		String año = Integer.toString(getNumeroAleatorio(1970, 2000));
		String mes = df.format(getNumeroAleatorio(01, 12));
		String dia = df.format(getNumeroAleatorio(01, 31));

		return año + mes + dia;
	}

	private static Jugador crearJugadorAleatorio(String nif, String nombre, int id) {
		return new Jugador(nif, nombre, getNumeroAleatorio(130, 180), getFechaAleatoria(), 'M', id,
				getNumeroAleatorio(1, 11), getNumeroAleatorio(1, 20));
	}

	public static ArrayList<Jugador> crearListaJugadoresAleatorios(int cantidad) {
		ArrayList<String> listaNombres = crearListaNombresAleatorios(cantidad);

		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();

		for (int i = 0; i < listaNombres.size(); i++)
			listaJugadores.add(crearJugadorAleatorio("nif" + (i + 1), listaNombres.get(i), i + 1));

		return listaJugadores;
	}

	private static int[][][] generarPrimeraMitadJornadas() {
		int[][][] jornadas = new int[19][10][2];
		int contadorEquipoID = 2;
		int contadorEquipoDI = 20;

		for (int jornada = 0; jornada < jornadas.length; jornada++)
			for (int partido = 0; partido < jornadas[jornada].length; partido++) {

				if (contadorEquipoID > 20)
					contadorEquipoID = 2;
				if (contadorEquipoDI < 2)
					contadorEquipoDI = 20;

				if (partido == 0) {
					jornadas[jornada][0][0] = 1;
				} else {
					jornadas[jornada][partido][0] = contadorEquipoDI;
					contadorEquipoDI--;
				}

				jornadas[jornada][partido][1] = contadorEquipoID;
				contadorEquipoID++;
			}

		return jornadas;
	}

	private static int[][][] invertirEquipos(int[][][] mitadJornadas) {
		int[][][] segundaMitadJornadas = new int[19][10][2];

		for (int jornada = 0; jornada < mitadJornadas.length; jornada++)
			for (int partido = 0; partido < mitadJornadas[jornada].length; partido++) {
				segundaMitadJornadas[jornada][partido][0] = mitadJornadas[jornada][partido][1];
				segundaMitadJornadas[jornada][partido][1] = mitadJornadas[jornada][partido][0];
			}

		return segundaMitadJornadas;
	}

	private static int[][][] generarJornadasOrdenadas() {
		int[][][] primeraMitadJornadas = generarPrimeraMitadJornadas();
		int[][][] segundaMitadJornadas = invertirEquipos(primeraMitadJornadas);

		int[][][] jornadas = new int[38][10][2];

		for (int i = 0; i < primeraMitadJornadas.length; i++) {
			jornadas[i] = primeraMitadJornadas[i];
			jornadas[primeraMitadJornadas.length + i] = segundaMitadJornadas[i];
		}

		return jornadas;
	}

	private static int[] generarIndicesRandom(int min, int max) {
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int[] indicesRandom = new int[max - min + 1];
		int indice;

		for (int j = 0; j < indicesRandom.length; j++)
			indices.add(j);

		for (int i = 0; i < indicesRandom.length; i++) {
			indice = getNumeroAleatorio(0, indices.size() - 1);
			indicesRandom[i] = indices.get(indice);
			indices.remove(indices.indexOf(indicesRandom[i]));
		}

		return indicesRandom;
	}

	private static int[][][] generarJornadas() {
		int[][][] jornadasOrdenadas = generarJornadasOrdenadas();
		int[][][] jornadas = new int[38][10][2];

		// Para aleatorizar las jornadas
		int[] indicesJornadas = generarIndicesRandom(0, jornadas.length - 1);

		for (int i = 0; i < jornadas.length; i++) {
			// Para aleatorizar partidas de una jornada
			int[] indicesPartidos = generarIndicesRandom(0, jornadas[i].length - 1);
			for (int j = 0; j < jornadas[i].length; j++) {
				jornadas[indicesJornadas[i]][indicesPartidos[j]] = jornadasOrdenadas[i][j];
			}
		}

		return jornadas;
	}

	private static Partido crearPartido(int id, int jornada, String equipoLocal, String equipoVisitante) {
		return new Partido(id, jornada, equipoLocal, getNumeroAleatorio(0, 4), equipoVisitante,
				getNumeroAleatorio(0, 4));
	}

	public static ArrayList<Partido> crearListaPartidosAleatorios(ArrayList<Equipo> equipos) {
		int[][][] jornadas = generarJornadas();
		ArrayList<Partido> partidos = new ArrayList<Partido>();

		for (int i = 0; i < jornadas.length; i++)
			for (int j = 0; j < jornadas[i].length; j++) {
				partidos.add(crearPartido((j + 1) + i * 10, i + 1, equipos.get(jornadas[i][j][0] - 1).getNombreCorto(),
						equipos.get(jornadas[i][j][1] - 1).getNombreCorto()));
			}

		return partidos;
	}
}