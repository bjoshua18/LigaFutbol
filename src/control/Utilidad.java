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
	
	public Jugador crearJugador(String nif, String nombre, int id) {
		Jugador jugador = new Jugador(nif, nombre, aleatorio(130, 180), generarFechaAleatoria(), 'M', id, aleatorio(1, 11), aleatorio(1, 20));
		return jugador;
	}
	
	public ArrayList<String> generarListaNombres(int cantidad) {
		ArrayList<String> nombres = generarListaString("ficheros/nombres.txt");
		ArrayList<String> apellidos = generarListaString("ficheros/apellidos.txt");
		
		ArrayList<String> listaNombres = new ArrayList<String>();
		
		for (int i = 0; i < cantidad; i++)
			listaNombres.add(generarNombreAleatorio(nombres, apellidos));
		
		return listaNombres;
	}
	
	public String getStringDatosJugador(Jugador jugador, String separador) {
		String datos = jugador.getNif()+separador+jugador.getNombre()+separador+jugador.getLongitudPaso()+separador+jugador.getFecha_nac()+separador+jugador.getSexo()+separador+jugador.getIdJugador()+separador+jugador.getDorsal()+separador+jugador.getIdEquipo();
		return datos;
	}
	
	public String generarNombreAleatorio(ArrayList<String> nombres, ArrayList<String> apellidos) {
		String nombre = nombres.get(aleatorio(0,nombres.size()-1));
		String apellido = apellidos.get(aleatorio(0,nombres.size()-1));
		return nombre+" "+apellido;
	}
	
	public String generarFechaAleatoria() {
		DecimalFormat df = new DecimalFormat("00");
		
		String año = Integer.toString(aleatorio(1970, 2000));
		String mes = df.format(aleatorio(01, 12));
		String dia = df.format(aleatorio(01, 31));
		
		return año+mes+dia;
	}
	
	public ArrayList<String> generarListaString(String ruta) {
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
	
	public String getStringDatosPartido(Partido partido, String separador) {
		String datos = partido.getIdPartido()+separador+partido.getJornada()+separador+partido.getEquipoLocal()+separador+partido.getGolesLocal()+separador+partido.getEquipoVisitante()+separador+partido.getGolesVisitante();
		return datos;
	}
	
	public ArrayList<Partido> crearListaPartidos(int cantidad, ArrayList<Equipo> equipos) {
		
		String[][] paresEquipos = generarEquiposContrarios(cantidad, equipos);
		
		ArrayList<Partido> partidos = new ArrayList<Partido>();
		
		for (int i = 0; i < cantidad; i++) 
			partidos.add(crearPartido(i+1, paresEquipos[i][0], paresEquipos[i][1]));
		
		return partidos;
	}
	
	public Partido crearPartido(int id, String equipoLocal, String equipoVisitante) {
		Partido partido = new Partido(id, aleatorio(1, 38), equipoLocal, aleatorio(0, 10), equipoVisitante, aleatorio(0, 10));
		return partido;
	}
	
	public String[][] generarEquiposContrarios(int numPartidos, ArrayList<Equipo> equipos) {
		String[][] paresEquipos = new String[numPartidos][2];
		
		for (int i = 0; i < paresEquipos.length; i++) {
			paresEquipos[i][0] = equipos.get(aleatorio(0, equipos.size()-1)).getNombreCorto();
			paresEquipos[i][1] = equipos.get(aleatorio(0, equipos.size()-1)).getNombreCorto();
			
			while(paresEquipos[i][0] == paresEquipos[i][1])
				paresEquipos[i][1] = equipos.get(aleatorio(0, equipos.size()-1)).getNombreCorto();
		}
		
		return paresEquipos;
	}
	
	public ArrayList<Equipo> crearListaEquipos(String ruta,String separador) {
		try {
			BufferedReader fichero = new BufferedReader(new FileReader(ruta));
			
			ArrayList<Equipo> lista = new ArrayList<Equipo>();
			
			String registro;
			
			while ((registro = fichero.readLine()) != null)
				añadirEquipoEnLista(lista, registro.split(separador));
			
			fichero.close();
			
			return lista;
			
		} catch (IOException e) {
			System.out.println("IO Exception");
			return null;
		}
	}
	
	public void añadirEquipoEnLista(ArrayList<Equipo> lista,String[] campos) {
		lista.add(new Equipo(Integer.parseInt(campos[0]), campos[1], campos[2]));
	}
	
	public int aleatorio(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
}