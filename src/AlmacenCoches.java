import java.io.*;
import java.util.*;

public class AlmacenCoches {
	public List<Coche> coches;
	public static final String FICHERO = "coches.dat";

	@SuppressWarnings("unchecked")
	public AlmacenCoches() {

		coches = new ArrayList<>();
		File data = new File(FICHERO);

		// creación de fichero si no existe o lectura del mismo si existe. Usamos try
		// with resources para que sea autoclosable
		if (!data.exists()) {
			try (FileOutputStream fos = new FileOutputStream(FICHERO);
					ObjectOutputStream oos = new ObjectOutputStream(fos);) {
				System.out.print("[Warning] Archivo no localizado, generando uno");
				oos.writeObject(coches);
			} catch (Exception e) {
				System.out.println("Error al crear archivo");
				e.printStackTrace();
			}
		} else {
			System.out.println("[Info] Leyendo datos del archivo -> coches.dat");
			try (FileInputStream fis = new FileInputStream(FICHERO);
					ObjectInputStream ois = new ObjectInputStream(fis);) {
				coches = (ArrayList<Coche>) ois.readObject();
				System.out.println("[Info] Fichero leido correctamente");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// Método para añadir coches a la lista comprobando si el id o la matrícula
	// están repetidos
	public void addCoche(Coche coche) {
		if (idExists(coche.getId(), coches) || matriculaExists(coche.getMatricula(), coches))
			System.out.println("El id o la matrícula ya existen.");
		else
			coches.add(coche);
	}

	// Método para comprobar si existe el id
	public boolean idExists(int id, List<Coche> coches) {
		for (Coche c : coches) {
			if (c.getId() == id) {
				return true;
			}
		}
		return false;
	}

	// Método para comprobar si existe la matrícula
	public boolean matriculaExists(String matricula, List<Coche> coches) {
		for (Coche c : coches) {
			if (c.getMatricula().equals(matricula)) {
				return true;
			}
		}
		return false;
	}

	// Método para borrar coche por id previa comprobación de la existencia de este
	public void deleteCoche(int id) {
		if (idExists(id, coches)) {
			for (Coche c : coches) {
				if (c.getId() == id) {
					coches.remove(c);
					return;
				}
			}
		}
	}

	// Método para buscar coche por id
	public Coche getCoche(int id) {
		return coches.stream().filter(coche -> coche.getId() == id).findFirst().orElse(null);
	}

	// Método para listar coches en FICHERO
	public void listCoches() {
		for (Coche coche : coches) {
			System.out.println("ID: " + coche.getId() + " - Matricula: " + coche.getMatricula() + " - Marca: "
					+ coche.getMarca() + " - Modelo: " + coche.getModelo() + " - Color: " + coche.getColor());
		}
	}

	// Método para exportar lista de coches a archivo CSV con separador ";"
	public void exportarCochesCSV() {
		try (FileWriter fw = new FileWriter("coches.csv")) {
			for (Coche coche : coches)
				fw.append(String.valueOf(coche.getId())).append(";").append(coche.getMatricula()).append(";")
						.append(coche.getMarca()).append(";").append(coche.getModelo()).append(";")
						.append(coche.getColor()).append("\n");

			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Método fin de programa
	public void terminarPrograma() {
		try (FileOutputStream fos = new FileOutputStream(FICHERO);
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			oos.writeObject(coches);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
