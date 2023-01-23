import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		AlmacenCoches almacen = new AlmacenCoches();
		Scanner scanner = new Scanner(System.in);
		boolean continuar = true;

		while (continuar) {
			System.out.println("");
			System.out.println("     ======");
			System.out.println("    | Menú |");
			System.out.println("     ======");
			System.out.println("");
			System.out.println("1. Añadir nuevo coche");
			System.out.println("2. Borrar coche por id");
			System.out.println("3. Consultar coche por id");
			System.out.println("4. Listado de coches");
			System.out.println("5. Terminar el programa");
			System.out.println("6. Exportar coches a archivo CSV");
			System.out.println("");
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
				System.out.println("[Info] Coche: " + coche + " se ha añadido correctamente.");
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
					System.out.println("[Info] El coche con ID " + id + " se ha borrado correctamente.");
				} else {
					System.out.println("[Warning] El ID introducido no existe");
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
					System.out.println("");
					System.out.println("[Info] Consulta ID: " + cocheConsultado.getId() + " - Matrícula: "
							+ cocheConsultado.getMatricula() + " - Marca: " + cocheConsultado.getMarca() + " - Modelo: "
							+ cocheConsultado.getModelo() + " - Color: " + cocheConsultado.getColor());
					System.out.println("");
				} else {
					System.out.println("[Warning] No existe un coche con ese ID.");
				}
				break;
			case 4:
				almacen.listCoches();
				break;
			case 5:
				almacen.terminarPrograma();
				continuar = false;
				break;
			case 6:
				almacen.exportarCochesCSV();
				System.out.println("");
				System.out.println("[Info] Coches exportados con éxito.");
				System.out.println("");
				break;
			default:
				System.out.println("[Warning] Opción no válida, por favor seleccione una opción del menú.");
				break;
			}
		}
		scanner.close();
	}
}
