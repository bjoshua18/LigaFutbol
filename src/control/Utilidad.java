package control;

import java.io.*;
import java.util.*;

import modelo.*;

public class Utilidad {

	public boolean comprobarEquipoRepetido(ArrayList<Partido> partidos) {
		for (int i = 0; i < partidos.size(); i++)
			if (partidos.get(i).getEquipoLocal().equals(partidos.get(i).getEquipoVisitante())) {
				System.out.println("Se repite " + partidos.get(i).getEquipoLocal() + " en partido "
						+ partidos.get(i).getIdPartido());
				partidos.get(i).setEquipoLocal("Otro");
				return true;
			}

		return false;
	}
}