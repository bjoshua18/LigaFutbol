package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import modelo.*;

public class Ejercicio {
	
	// 07 febrero 2019
	
	public HashMap<String, ArrayList<Integer>> creaClasificacion(String rutaFichero) {
		HashMap<String, ArrayList<Integer>> mapa = new HashMap<String, ArrayList<Integer>>();
		HashMap<String, Equipo> mapaEquipos = Equipo.getMapEquipo(Equipo.crearListaFichero("ficheros/equipos.txt", "#"));
		HashMap<String, Clasificacion> mapaClasificaciones = Clasificacion.getMapClasificacion(Partido.crearListaFichero("D:/partidos.txt" , "#"));
		
		for(String clave: mapaEquipos.keySet())
			mapa.put(mapaEquipos.get(clave).getNombre(), getArrayListClasificacion(mapaClasificaciones.get(clave)));
		return mapa;
	}
	
	private ArrayList<Integer> getArrayListClasificacion(Clasificacion clasificacion) {
		ArrayList<Integer> listaClasificacion = new ArrayList<Integer>();
		listaClasificacion.add(clasificacion.getPuntuacion());
		listaClasificacion.add(clasificacion.getPartidosJugados());
		listaClasificacion.add(clasificacion.getPartidosGanados());
		listaClasificacion.add(clasificacion.getPartidosEmpate());
		listaClasificacion.add(clasificacion.getPartidosPerdidos());
		listaClasificacion.add(clasificacion.getNumGolesFavor());
		listaClasificacion.add(clasificacion.getNumGolesContra());
		return listaClasificacion;
	}
	
	public List<Entry<String, Integer>> ordenarMapaClasificacion(HashMap<String, ArrayList<Integer>> clasificaciones) {
		HashMap<String, Integer> puntuaciones = new HashMap<String, Integer>();
		for(String clave : clasificaciones.keySet())
			puntuaciones.put(clave, clasificaciones.get(clave).get(0));
			
		return ordenarMapaPuntosEquipos(puntuaciones);
	}

	// 05 febrero 2019
	
	public List<Entry<String, Integer>> ordenarMapaPuntosEquipos(HashMap<String, Integer> puntosEquipos) {
		Set<Entry<String, Integer>> set = puntosEquipos.entrySet();
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(set);
        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
        {
            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
            {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        for(Map.Entry<String, Integer> entry:list){
            System.out.println(entry.getKey()+" ==== "+entry.getValue());
        }
        
        return list;
	}
	
	// 31 enero 2019

	public void muestraPuntosOrdenadoEquipos(HashMap<String, ArrayList<Integer>> resultado) {
		HashMap<String, Integer> mapaOrdenadoPuntos = new HashMap<String, Integer>();
		
		for (String clave : resultado.keySet()) {
			ArrayList<Integer> datos = resultado.get(clave);
			int puntos = datos.get(0) * 3 + datos.get(1);
			mapaOrdenadoPuntos.put(clave, puntos);
		}
		// ahora si ordenamos...
		// https://stackoverflow.com/questions/8119366/sorting-hashmap-by-values
		// https://www.javacodegeeks.com/2017/09/java-8-sorting-hashmap-values-ascending-descending-order.html
	}
	
	// 30 enero 2019
	
	// prueba de SWING (MVC)
	
	public void pruebaSwing() {
		JFrame ventana;
		
		// Creamos los objetos que vamos a mostrar
		ventana = new JFrame("Mi primer SWING");
		JButton boton = new JButton("PulsaMe!");
		JPanel panel = new JPanel();
		
		// Añadimos los objetos para organizarlos
		ventana.add(panel);
		ArrayList<Clasificacion> listaClasificaciones = Clasificacion.crearListaFichero("ficheros/clasificacion.txt", ",");

		ArrayList<Equipo> equipos = Equipo.crearListaFichero("ficheros/equipos.txt", "#");
				
		// Creamos una lista
		JComboBox lista = new JComboBox(equipos.toArray());
		panel.add(lista);
		panel.add(boton);
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Hacer que escriba por consola el equipo que se ha pulsado
				// Al pulsar, que me escriba el valor seleccionado que tiene la lista
				System.out.println(lista.getSelectedItem());
				
			}
		});
		
		// Para que sea visible
		ventana.pack();
		ventana.setVisible(true);
	}
	
	// Por terminar
	public void mostrarClasificacion(HashMap<String, Integer> puntuaciones) {
		ArrayList<Object> clasificaciones = new ArrayList<Object>();
		ArrayList<String> clavesPuntuaciones = new ArrayList(puntuaciones.keySet());
		String equipoMayor;
		String comprobado;
		int puntuacionMayor;
		
		for(int i = 0; i < clavesPuntuaciones.size() - 1; i++) {
			equipoMayor = clavesPuntuaciones.get(i);
			puntuacionMayor = puntuaciones.get(equipoMayor);
			
			for(int j = i + 1; j < clavesPuntuaciones.size(); j++) {
				comprobado = clavesPuntuaciones.get(j);
				if(puntuacionMayor < puntuaciones.get(comprobado)) {
					equipoMayor = comprobado;
					puntuacionMayor = puntuaciones.get(comprobado);
				}
			}
			
			clasificaciones.add(equipoMayor);
			clasificaciones.add(puntuacionMayor);
		}
		
		for (int i = 0; i < clasificaciones.size()-1; i += 2)
			System.out.println(clasificaciones.get(i) + " => " + clasificaciones.get(i+1));
	}
	
	// Método que devuelve un mapa con los equipos y las puntuaciones correspondientes
	
	public HashMap<String, Integer> puntuacionEquipos(ArrayList<Partido> listaPartidos) {
		HashMap<String, Integer> puntuaciones = new HashMap<String, Integer>();
		HashMap<String, ArrayList<Integer>> mapaEstadisticas = getGEPMap(listaPartidos);
		Set<String> clavesMapa = mapaEstadisticas.keySet();
		
		for (String clave : clavesMapa)
			puntuaciones.put(clave, getPuntuacion(mapaEstadisticas.get(clave)));
		
		return puntuaciones;
	}
	
	// Método hecho en clase
	
	public HashMap<String, Integer> generaPuntosEquipos(HashMap<String, ArrayList<Integer>> mapaEstadisticas) {
		HashMap<String, Integer> puntuaciones = new HashMap<String, Integer>();
		Set<String> clavesMapa = mapaEstadisticas.keySet();
		
		for (String clave : clavesMapa)
			puntuaciones.put(clave, getPuntuacion(mapaEstadisticas.get(clave)));
		
		return puntuaciones;
	}
	
	private Integer getPuntuacion(ArrayList<Integer> listaGEP) {
		return 3 * listaGEP.get(0) + listaGEP.get(1);
	}

	// (Con Rita) Método que devuelve un HashMap con todos los equipos y sus
	// estadísticas en un ArrayList

	public HashMap<String, ArrayList<Integer>> getGEPMap(ArrayList<Partido> listaPartidos) {
		HashMap<String, ArrayList<Integer>> mapa = new HashMap<String, ArrayList<Integer>>();

		for (Partido partido : listaPartidos) {
			introduceEquiposEnMapa(partido, mapa);
			setEstadisticasEquipos(partido, mapa);
		}

		return mapa;
	}

	private void setEstadisticasEquipos(Partido partido, HashMap<String, ArrayList<Integer>> mapa) {
		switch(esGanador(partido.getGolesLocal(), partido.getGolesVisitante())) {
		case 1:
			setGEP(partido.getEquipoLocal(), partido.getEquipoVisitante(), mapa, false);
			break;
		case -1:
			setGEP(partido.getEquipoVisitante(), partido.getEquipoLocal(), mapa, false);
			break;
		case 0:
			setGEP(partido.getEquipoLocal(), partido.getEquipoVisitante(), mapa, true);
			break;
		}
	}

	private void setGEP(String equipo1, String equipo2, HashMap<String, ArrayList<Integer>> mapa, boolean esEmpate) {
		if(!esEmpate) {
			mapa.get(equipo1).set(0, mapa.get(equipo1).get(0)+1);
			mapa.get(equipo2).set(2, mapa.get(equipo2).get(2)+1);
		} else {
			mapa.get(equipo1).set(1, mapa.get(equipo1).get(1)+1);
			mapa.get(equipo2).set(1, mapa.get(equipo2).get(1)+1);
		}
	}

	private byte esGanador(int goles1, int goles2) {
		if (goles1 > goles2)
			return 1; // This partido gana
		else if (goles1 < goles2)
			return -1; // This partido pierde
		else
			return 0; // Empate
	}

	private void introduceEquiposEnMapa(Partido partido, HashMap<String, ArrayList<Integer>> mapa) {
		introduceEquipoEnMapa(partido.getEquipoLocal(), mapa);
		introduceEquipoEnMapa(partido.getEquipoVisitante(), mapa);
	}

	private void introduceEquipoEnMapa(String equipo, HashMap<String, ArrayList<Integer>> mapa) {
		if (!mapa.containsKey(equipo))
			mapa.put(equipo, new ArrayList<Integer>(Arrays.asList(0, 0, 0)));
	}

	// 22/01/19
	// Método que devuelve un HashMap con todos los equipos y sus partidos jugados
	public HashMap<String, Integer> comprobarPartidos(ArrayList<Partido> partidos) {
		HashMap<String, Integer> equipos = new HashMap<String, Integer>();

		for (int i = 0; i < partidos.size(); i++) {
			sumarPartidoAEquipo(equipos, partidos.get(i).getEquipoLocal());
			sumarPartidoAEquipo(equipos, partidos.get(i).getEquipoVisitante());
		}

		return equipos;
	}

	public void sumarPartidoAEquipo(HashMap<String, Integer> equipos, String equipo) {
		if (!equipos.containsKey(equipo))
			equipos.put(equipo, 1);
		else
			equipos.replace(equipo, equipos.get(equipo) + 1);
	}
}
