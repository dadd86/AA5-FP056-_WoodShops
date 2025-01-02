package aa4_woodshops;

import java.math.BigDecimal;

/**
 * Clase que representa un cliente profesional en el sistema de gestión de la tienda.
 * Los clientes profesionales disfrutan de un descuento en sus compras, especificado al crear el cliente.
 * Esta clase extiende de Cliente para reutilizar la funcionalidad común.
 */
public class ClienteProfesional extends Cliente {
    private final BigDecimal descuento;

    /**
     * Constructor para crear un nuevo cliente profesional con un descuento específico.
     *
     * @param nif El número de identificación fiscal del cliente, debe ser válido y no nulo.
     * @param nombre El nombre del cliente, no puede ser nulo ni vacío.
     * @param descuento El porcentaje de descuento que el cliente tiene derecho a recibir, debe estar entre 0 y 100.
     * @throws IllegalArgumentException Si el descuento no está en el rango permitido (0-100).
     */
    public ClienteProfesional(String nif, String nombre, BigDecimal descuento) {
        super(nif, nombre);
        if (descuento == null || descuento.compareTo(BigDecimal.ZERO) < 0 || descuento.compareTo(new BigDecimal("100")) > 0) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        this.descuento = descuento;
    }

    /**
     * Obtiene el descuento del cliente profesional.
     *
     * @return El descuento como un valor de BigDecimal que representa el porcentaje de descuento.
     */
    public BigDecimal getDescuento() {
        return descuento;
    }
}
