package aa4_woodshops;

/**
 * Clase abstracta que representa un cliente en el sistema de gestión de WoodShops.
 * Esta clase sirve como base para diferentes tipos de clientes, proporcionando
 * estructura común y validaciones necesarias para todos los clientes.
 */
public abstract class Cliente {
    protected String nif;
    protected String nombre;

    /**
     * Constructor para crear un nuevo cliente.
     * Verifica que el NIF y el nombre no sean nulos ni estén vacíos.
     * 
     * @param nif El número de identificación fiscal del cliente, no puede ser nulo ni vacío.
     * @param nombre El nombre del cliente, no puede ser nulo ni vacío.
     * @throws IllegalArgumentException Si el nif o el nombre son nulos o están vacíos.
     */
    public Cliente(String nif, String nombre) {
        if (nif == null || nif.trim().isEmpty()) {
            throw new IllegalArgumentException("El NIF no puede ser nulo o vacío.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }
        this.nif = nif;
        this.nombre = nombre;
    }

    /**
     * Devuelve el NIF del cliente.
     *
     * @return El NIF del cliente.
     */
    public String getNif() {
        return nif;
    }

    /**
     * Devuelve el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }
}




