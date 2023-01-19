import java.io.*;
import java.util.*;

public class AlmacenCoches {
    public ArrayList<Coche> coches;
    public static final String FICHERO = "coches.dat";

    public AlmacenCoches() {
        coches = new ArrayList<Coche>();
        File fis = new File(FICHERO);
		if (!fis.exists()) {
            try {
                System.out.print("[Warning] Archivo no localizado, generando uno");
                fis.createNewFile();
            } catch (Exception e) {
                // TODO: handle exception
            }
		} else {
            System.out.println("[Info] Leyendo datos del archivo -> coches.dat");
            try (FileReader lecturaFichero = new FileReader(FICHERO)) {
            BufferedReader bufferLectura = new BufferedReader(lecturaFichero);
            String frase = bufferLectura.readLine();
            while(frase != null){
                System.out.println("Frase del fichero: " + frase);
                frase = bufferLectura.readLine();
                System.out.println("LINEA: " + frase);
            }
            System.out.println("[Info] Fichero leido correctamente");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
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
