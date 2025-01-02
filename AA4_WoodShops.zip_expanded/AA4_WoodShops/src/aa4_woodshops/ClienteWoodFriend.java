package aa4_woodshops;

/**
 * Representa un tipo específico de cliente en el sistema de gestión de la tienda.
 * ClienteWoodFriend es un cliente registrado con beneficios adicionales.
 * Esta clase extiende de Cliente e incluye un código de socio único para identificar al cliente en promociones especiales.
 */
public class ClienteWoodFriend extends Cliente {
    private final String codigoSocio; // Inmutabilidad para garantizar la consistencia de la referencia.

    /**
     * Constructor para crear un nuevo cliente WoodFriend.
     * @param nif El número de identificación fiscal del cliente, debe ser válido y no nulo.
     * @param nombre El nombre del cliente, no puede ser nulo ni vacío.
     * @param codigoSocio Un código único que identifica al cliente dentro del programa de fidelización WoodFriend.
     * @throws IllegalArgumentException si el nif, nombre o código de socio son nulos, vacíos o no válidos.
     */
    public ClienteWoodFriend(String nif, String nombre, String codigoSocio) {
        super(nif, nombre);
        if (codigoSocio == null || codigoSocio.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de socio no puede ser nulo o vacío.");
        }
        this.codigoSocio = codigoSocio;
    }

    /**
     * Obtiene el código de socio del cliente.
     * @return El código de socio, que es un identificador único para promociones especiales.
     */
    public String getCodigoSocio() {
        return codigoSocio;
    }
}
