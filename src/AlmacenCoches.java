import java.io.*;
import java.util.*;

public class AlmacenCoches {
    public ArrayList<Coche> coches;

    public AlmacenCoches() {
        coches = new ArrayList<Coche>();
        try {
            FileInputStream fis = new FileInputStream("coches.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            coches = (ArrayList<Coche>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            // Si no existe el archivo, se crea una ArrayList vacía
        }
    }

    public void addCoche(Coche coche) {
        coches.add(coche);
    }

    public void deleteCoche(int id) {
        Iterator<Coche> iterator = coches.iterator();
        while (iterator.hasNext()) {
            Coche coche = iterator.next();
            if (coche.getId() == id)
                iterator.remove();
        }
    }
    public Coche getCoche(int id) {
        for (Coche coche : coches) {
            if (coche.getId() == id) {
                return coche;
            }
        }
        return null;
    }

    public void listCoches() {
        for (Coche coche : coches) {
            System.out.println("ID: " + coche.getId() + " - Matricula: " + coche.getMatricula() + " - Marca: " + coche.getMarca() + " - Modelo: " + coche.getModelo() + " - Color: " + coche.getColor());
        }
    }

    public void exportarCochesCSV() {
        try {
            FileWriter fw = new FileWriter("coches.csv");
            for (Coche coche : coches) {
                fw.append(coche.getId() + "," + coche.getMatricula() + "," + coche.getMarca() + "," + coche.getModelo() + "," + coche.getColor() + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void terminarPrograma() {
        try {
            FileOutputStream fos = new FileOutputStream("coches.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(coches);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
