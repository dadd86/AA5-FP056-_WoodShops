package aa4_woodshops;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Gestiona los proveedores de la tienda, permitiendo agregar y eliminar proveedores.
 */
public class GestorProveedores {
    private ArrayList<Proveedor> proveedores = new ArrayList<>();

    /**
     * Agrega un nuevo proveedor a la lista asegurándose de que el NIF y el nombre no estén duplicados.
     * @param scanner El scanner para leer la entrada del usuario.
     */
    public void agregarProveedor(Scanner scanner) {
        System.out.print("Ingrese NIF del proveedor: ");
        String nif = scanner.nextLine().trim();
        System.out.print("Ingrese nombre del proveedor: ");
        String nombre = scanner.nextLine().trim();

        // Verificar si el NIF o el nombre ya existen
        if (proveedorExiste(nif, nombre)) {
            System.out.println("Error: Un proveedor con este NIF o nombre ya existe.");
            return;
        }

        try {
            Proveedor nuevoProveedor = new Proveedor(nif, nombre);
            proveedores.add(nuevoProveedor);
            System.out.println("Proveedor agregado correctamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error al agregar proveedor: " + e.getMessage());
        }
    }

    /**
     * Verifica si ya existe un proveedor con el mismo NIF o nombre.
     * @param nif El NIF a verificar.
     * @param nombre El nombre a verificar.
     * @return true si existe un proveedor con el mismo NIF o nombre, false en caso contrario.
     */
    private boolean proveedorExiste(String nif, String nombre) {
        return proveedores.stream()
                          .anyMatch(proveedor -> proveedor.getNif().equals(nif) || proveedor.getNombre().equalsIgnoreCase(nombre));
    }


    /**
     * Elimina un proveedor de la lista usando el Scanner global.
     */
    public void eliminarProveedor(Scanner scanner) {
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
            return;
        }

        System.out.println("Seleccione el proveedor a eliminar:");
        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor proveedor = proveedores.get(i);
            System.out.println((i + 1) + ". " + proveedor.getNombre() + " - NIF: " + proveedor.getNif());
        }

        System.out.print("Ingrese el número del proveedor: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < proveedores.size()) {
                Proveedor proveedorAEliminar = proveedores.remove(index);
                System.out.println("Proveedor eliminado correctamente: " + proveedorAEliminar.getNombre());
            } else {
                System.out.println("Selección inválida. Intente de nuevo.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Por favor, ingrese un número válido.");
        }
    }

    public ArrayList<Proveedor> getProveedores() {
        return proveedores;
    }
}
