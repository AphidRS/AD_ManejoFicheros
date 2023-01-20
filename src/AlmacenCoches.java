import java.io.*;
import java.util.*;

public class AlmacenCoches {
    public ArrayList<Coche> coches;
    public static final String FICHERO = "coches.dat";
    @SuppressWarnings("unchecked")
    public AlmacenCoches() {

        coches = new ArrayList<>();
        File data = new File(FICHERO);
		if (!data.exists()) {
            try {
                System.out.print("[Warning] Archivo no localizado, generando uno");
                FileOutputStream fos = new FileOutputStream(FICHERO);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(coches);
                oos.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
		} else {
            System.out.println("[Info] Leyendo datos del archivo -> coches.dat");
            try {
                FileInputStream fis = new FileInputStream(FICHERO);
                ObjectInputStream ois = new ObjectInputStream(fis);
                coches = (ArrayList<Coche>) ois.readObject();
                ois.close();
                System.out.println("[Info] Fichero leido correctamente");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void addCoche(Coche coche) {
        if(idExists(coche.getId(),coches) || matriculaExists(coche.getMatricula(),coches))
            System.out.println("El id o la matrícula ya existen.");
        else
            coches.add(coche);
    }

    public boolean idExists(int id, ArrayList<Coche> coches) {
        for (Coche c : coches) {
            if (c.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean matriculaExists(String matricula, ArrayList<Coche> coches) {
        for (Coche c : coches) {
            if (c.getMatricula().equals(matricula)) {
                return true;
            }
        }
        return false;
    }

    public void deleteCoche(int id) {
        coches.removeIf(coche -> coche.getId() == id);
    }
    public Coche getCoche(int id) {
        return coches.stream()
                .filter(coche -> coche.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void listCoches() {
        for (Coche coche : coches) {
            System.out.println("ID: " + coche.getId() + " - Matricula: " + coche.getMatricula() + " - Marca: " + coche.getMarca() + " - Modelo: " + coche.getModelo() + " - Color: " + coche.getColor());
        }
    }

    public void exportarCochesCSV() {
        try (FileWriter fw = new FileWriter("coches.csv")) {
            for (Coche coche : coches)
                fw.append(String.valueOf(coche.getId())).append(",").append(coche.getMatricula()).append(",").append(coche.getMarca()).append(",").append(coche.getModelo()).append(",").append(coche.getColor()).append("\n");

            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void terminarPrograma() {
        try {
            FileOutputStream fos = new FileOutputStream(FICHERO);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(coches);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
