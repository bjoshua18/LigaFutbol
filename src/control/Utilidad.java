package control;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import modelo.*;

public class Utilidad {
	
	public void writeListaJugadores (ArrayList<Jugador> lista,String ruta, String separador) {
		try {
			BufferedWriter escritura = new BufferedWriter(new FileWriter(ruta));
			
			// Para escribir newLine() delante y que la última línea no quede vacía
			escritura.write(getStringDatosJugador(lista.get(0), separador));
			for (int i = 1; i < lista.size(); i++) {
				escritura.newLine();
				escritura.write(getStringDatosJugador(lista.get(i), separador));
			}
			
			escritura.close();
			
		} catch (IOException e) {
			System.out.println("IO Exception.");
		}
	}
	
	public ArrayList<Jugador> crearListaJugadores(int cantidad) {
		ArrayList<String> listaNombres = generarListaNombres(cantidad);
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		
		for (int i = 0; i < listaNombres.size(); i++)
			jugadores.add(crearJugador("nif"+(i+1),listaNombres.get(i),i+1));
		
		return jugadores;
	}
	
	private Jugador crearJugador(String nif, String nombre, int id) {
		Jugador jugador = new Jugador(nif, nombre, aleatorio(130, 180), generarFechaAleatoria(), 'M', id, aleatorio(1, 11), aleatorio(1, 20));
		return jugador;
	}
	
	private ArrayList<String> generarListaNombres(int cantidad) {
		ArrayList<String> nombres = generarListaString("ficheros/nombres.txt");
		ArrayList<String> apellidos = generarListaString("ficheros/apellidos.txt");
		
		ArrayList<String> listaNombres = new ArrayList<String>();
		
		for (int i = 0; i < cantidad; i++)
			listaNombres.add(generarNombreAleatorio(nombres, apellidos));
		
		return listaNombres;
	}
	
	private String getStringDatosJugador(Jugador jugador, String separador) {
		String datos = jugador.getNif()+separador+jugador.getNombre()+separador+jugador.getLongitudPaso()+separador+jugador.getFecha_nac()+separador+jugador.getSexo()+separador+jugador.getIdJugador()+separador+jugador.getDorsal()+separador+jugador.getIdEquipo();
		return datos;
	}
	
	private String generarNombreAleatorio(ArrayList<String> nombres, ArrayList<String> apellidos) {
		String nombre = nombres.get(aleatorio(0,nombres.size()-1));
		String apellido = apellidos.get(aleatorio(0,nombres.size()-1));
		return nombre+" "+apellido;
	}
	
	private String generarFechaAleatoria() {
		DecimalFormat df = new DecimalFormat("00");
		
		String año = Integer.toString(aleatorio(1970, 2000));
		String mes = df.format(aleatorio(01, 12));
		String dia = df.format(aleatorio(01, 31));
		
		return año+mes+dia;
	}
	
	private ArrayList<String> generarListaString(String ruta) {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			
			ArrayList<String> lista = new ArrayList<String>();
			
			String registro;
			
			while ((registro = fichero.readLine()) != null) {
				lista.add(registro);
			}
			
			fichero.close();
			
			return lista;
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			return null;
		}
		
	}
	
	public void writeListaPartidos(ArrayList<Partido> lista, String ruta, String separador) {
		try {
			BufferedWriter escritura = new BufferedWriter(new FileWriter(ruta));
			
			escritura.write(getStringDatosPartido(lista.get(0), separador));
			for (int i = 1; i < lista.size(); i++) {
				escritura.newLine();
				escritura.write(getStringDatosPartido(lista.get(i), separador));
			}
			
			escritura.close();
			
		} catch (IOException e) {
			System.out.println("IO Exception.");
		}
	}
	
	private String getStringDatosPartido(Partido partido, String separador) {
		String datos = partido.getIdPartido()+separador+partido.getJornada()+separador+partido.getEquipoLocal()+separador+partido.getGolesLocal()+separador+partido.getEquipoVisitante()+separador+partido.getGolesVisitante();
		return datos;
	}
	
	public ArrayList<Partido> crearListaPartidos(ArrayList<Equipo> equipos) {
		
		int[][][] jornadas = generarJornadas();
		
		ArrayList<Partido> partidos = new ArrayList<Partido>();
		
		for (int i = 0; i < jornadas.length; i++)
			for (int j = 0; j < jornadas[i].length; j++) {
				partidos.add(crearPartido((j+1)+i*10, i+1, equipos.get(jornadas[i][j][0]-1).getNombreCorto(), equipos.get(jornadas[i][j][1]-1).getNombreCorto()));
			}
					
		return partidos;
	}
	
	private Partido crearPartido(int id, int jornada, String equipoLocal, String equipoVisitante) {
		Partido partido = new Partido(id, jornada, equipoLocal, aleatorio(0, 4), equipoVisitante, aleatorio(0, 4));
		return partido;
	}
	
	public ArrayList<Equipo> crearListaEquipos(String ruta,String separador) {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			
			ArrayList<Equipo> lista = new ArrayList<Equipo>();
			
			String registro;
			
			while ((registro = fichero.readLine()) != null)
				addEquipoEnLista(lista, registro.split(separador));
			
			fichero.close();
			
			return lista;
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			return null;
		}
	}
	
	private void addEquipoEnLista(ArrayList<Equipo> lista,String[] campos) {
		lista.add(new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]));
	}
	
	public int[][][] generarJornadas() {
		int[][][] jornadasOrdenadas = generarJornadasOrdenadas();
		int[][][] jornadas = new int[38][10][2];
		
		// Para aleatorizar las jornadas
		int[] indicesJornadas = generarIndicesRandom(0, jornadas.length-1);
		
		for (int i = 0; i < jornadas.length; i++) {
		// Para aleatorizar partidas de una jornada
			int[] indicesPartidos = generarIndicesRandom(0, jornadas[i].length-1);
			for (int j = 0; j < jornadas[i].length; j++) {
				jornadas[indicesJornadas[i]][indicesPartidos[j]] = copiarArray(jornadasOrdenadas[i][j]);
			}
		}
		
		return jornadas;
	}
	
	private int[][][] generarJornadasOrdenadas() {
		int[][][] primeraMitadJornadas = generarMitadJornadas();
		int[][][] segundaMitadJornadas = invertirEquipos(primeraMitadJornadas);
		
		int[][][] jornadas = new int[38][10][2];
		
		for (int i = 0; i < primeraMitadJornadas.length; i++) {
			jornadas[i] = primeraMitadJornadas[i];
			jornadas[primeraMitadJornadas.length+i] = segundaMitadJornadas[i];
		}
		
		return jornadas;
	}
	
	// Para generar la primera parte de las jornadas
	private int[][][] generarMitadJornadas() {
		int[][][] jornadas = new int[19][10][2];
		int contadorEquipoID = 2;
		int contadorEquipoDI = 20;
		
		// Recorre cada jornada
		for (int jornada = 0; jornada < jornadas.length; jornada++) {
			// Recorre cada partido de cada jornada
			for (int partido = 0; partido < jornadas[jornada].length; partido++) {
				
				if(contadorEquipoID > 20)
					contadorEquipoID = 2;
				if(contadorEquipoDI < 2)
					contadorEquipoDI = 20;
				
				if(partido == 0) {
					jornadas[jornada][0][0] = 1;
				} else {
					jornadas[jornada][partido][0] = contadorEquipoDI;
					contadorEquipoDI--;
				}
				
				jornadas[jornada][partido][1] = contadorEquipoID;
				contadorEquipoID++;
			}
		}
		
		return jornadas;
	}
	
	// Para generar la segunda parte de las jornadas
	private int[][][] invertirEquipos(int[][][] mitadJornadas) {
		int[][][] segundaMitadJornadas = new int[19][10][2];
		
		for (int jornada = 0; jornada < mitadJornadas.length; jornada++) {
			// Recorre cada partido de cada jornada
			for (int partido = 0; partido < mitadJornadas[jornada].length; partido++) {
				
				segundaMitadJornadas[jornada][partido][0] = mitadJornadas[jornada][partido][1];
				segundaMitadJornadas[jornada][partido][1] = mitadJornadas[jornada][partido][0];
			}
		}
		
		return segundaMitadJornadas;
	}
	
	private int aleatorio(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
	
	public int[] generarIndicesRandom(int min, int max) {
		ArrayList<Integer> indices = new ArrayList<Integer>();
		int[] indicesRandom = new int[max-min+1];
		int indice;
		
		for (int j = 0; j < indicesRandom.length; j++)
			indices.add(j);
		
		for(int i = 0; i < indicesRandom.length; i++) {
			indice = aleatorio(0, indices.size()-1);
			indicesRandom[i] = indices.get(indice);
			indices.remove(indices.indexOf(indicesRandom[i]));
		}
		
		return indicesRandom;
	}
	
	public boolean comprobarEquipoRepetido(ArrayList<Partido> partidos) {
		for (int i = 0; i < partidos.size(); i++)
			if(partidos.get(i).getEquipoLocal().equals(partidos.get(i).getEquipoVisitante())) {
				System.out.println("Se repite "+partidos.get(i).getEquipoLocal()+" en partido "+partidos.get(i).getIdPartido());
				partidos.get(i).setEquipoLocal("Otro");
				return true;
			}
				
		return false;
	}
	
	public int[] copiarArray(int[] array) {
		int[] arrayCopy = new int[array.length];
		
		for (int i = 0; i < array.length; i++)
			arrayCopy[i] = array[i];
		
		return arrayCopy;
	}
	
	public int[][] copiarArray(int[][] array) {
		int[][] arrayCopy = new int[array.length][array[0].length];
		
		for (int i = 0; i < array.length; i++)
			arrayCopy[i] = copiarArray(array[i]);
		
		return arrayCopy;
	}
	
	public int[][][] copiarArray(int[][][] array) {
		int[][][] arrayCopy = new int[array.length][array[0].length][array[0][0].length];
		
		for (int i = 0; i < array.length; i++)
			arrayCopy[i] = copiarArray(array[i]);
		
		return arrayCopy;
	}
	
	public void imprimirArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			if(i == 0) {
				System.out.print("[" + array[i] + ", ");
			} else if(i == array.length-1) {
				System.out.print(array[i] + "]");
			} else {
				System.out.print(array[i] + ", ");
			}
		}
		
	}
	
	public void imprimirArray(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			if(i == 0) {
				System.out.print("[");
				imprimirArray(array[i]);
				System.out.print(", ");
			} else if(i == array.length-1) {
				imprimirArray(array[i]);
				System.out.print("]");
			} else {
				imprimirArray(array[i]);
				System.out.print(", ");
			}
		}

		System.out.println("");
	}
}