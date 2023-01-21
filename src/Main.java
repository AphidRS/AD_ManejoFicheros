import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        AlmacenCoches almacen = new AlmacenCoches();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nMenú:");
            System.out.println("1. Añadir nuevo coche");
            System.out.println("2. Borrar coche por id");
            System.out.println("3. Consultar coche por id");
            System.out.println("4. Listado de coches");
            System.out.println("5. Terminar el programa");
            System.out.println("6. Exportar coches a archivo CSV");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: 
                    System.out.print("Introduzca el ID del coche: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, introduzca un número");
                        scanner.nextLine();
                        System.out.print("Introduzca el ID del coche: ");
                    }
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Introduzca la matrícula del coche: ");
                    String matricula = scanner.nextLine();
                    System.out.print("Introduzca la marca del coche: ");
                    String marca = scanner.nextLine();
                    System.out.print("Introduzca el modelo del coche: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Introduzca el color del coche: ");
                    String color = scanner.nextLine();
                    Coche coche = new Coche(id, matricula, marca, modelo, color);
                    almacen.addCoche(coche);
                    break;
            
                case 2: 
                    System.out.print("Introduzca el ID del coche a borrar: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, introduzca un número");
                        scanner.nextLine();
                        System.out.print("Introduzca el ID del coche a borrar: ");
                    }
                    id = scanner.nextInt();
                    scanner.nextLine();
                    if (almacen.idExists(id, almacen.coches)) {
                        almacen.deleteCoche(id);
                        System.out.println("Coche borrado con éxito.");
                    } else {
                        System.out.println("El ID introducido no existe");
                    }
                    break;
                case 3: 
                    System.out.print("Introduzca el ID del coche a consultar: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Por favor, introduzca un número");
                        scanner.nextLine();
                        System.out.print("Introduzca el ID del coche a consultar: ");
                    }
                    id = scanner.nextInt();
                    scanner.nextLine();
                    Coche cocheConsultado = almacen.getCoche(id);
                    if (cocheConsultado != null) {
                        System.out.println("ID: " + cocheConsultado.getId() + " - Matrícula: " + cocheConsultado.getMatricula() + " - Marca: " + cocheConsultado.getMarca() + " - Modelo: " + cocheConsultado.getModelo() + " - Color: " + cocheConsultado.getColor());
                    } else {
                        System.out.println("No existe un coche con ese ID.");
                    }
                    break;
                case 4: almacen.listCoches();break;
                case 5:
                    almacen.terminarPrograma();
                    continuar = false;
                    break;
                case 6:
                    almacen.exportarCochesCSV();
                    System.out.println("Coches exportados con éxito.");
                    break;
                default: System.out.println("Opción no válida, por favor seleccione una opción del menú.");break;
            }
        }
        scanner.close();
    }
}
