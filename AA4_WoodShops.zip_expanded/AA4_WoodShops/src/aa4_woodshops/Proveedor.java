package aa4_woodshops;

/**
 * Representa a un proveedor con su identificación fiscal y nombre.
 * La clase es inmutable para asegurar que la información del proveedor no se modifique una vez establecida.
 */
public class Proveedor {
    private final String nif; // Número de Identificación Fiscal del proveedor.
    private final String nombre; // Nombre del proveedor.

    /**
     * Constructor para crear un nuevo proveedor.
     * @param nif NIF del proveedor, no debe ser nulo ni vacío.
     * @param nombre Nombre del proveedor, no debe ser nulo ni vacío.
     * @throws IllegalArgumentException si el nif o el nombre son nulos o vacíos.
     */
    public Proveedor(String nif, String nombre) {
        if (nif == null || nif.trim().isEmpty()) {
            throw new IllegalArgumentException("El NIF no puede ser nulo ni vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío.");
        }
        this.nif = nif;
        this.nombre = nombre;
    }
    

    // Getters, sin setters para mantener la inmutabilidad.
    public String getNif() { return nif; }
    public String getNombre() { return nombre; }
}
