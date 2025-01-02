package aa4_woodshops;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una venta realizada en la tienda, incluyendo detalles como el número del ticket,
 * la fecha de la venta, el cliente asociado y los detalles de cada producto vendido.
 */
public class Venta {
    private final int numeroTicket;
    private final LocalDate fecha;
    private final Cliente cliente;
    private final List<DetalleVenta> detallesVenta;
    private BigDecimal total;
    private boolean totalCalculado;

    /**
     * Constructor para crear una nueva venta.
     * 
     * @param numeroTicket El número identificativo de la venta, debe ser positivo.
     * @param fecha La fecha en la que se realiza la venta, no debe ser nula.
     * @param cliente El cliente asociado a la venta, no debe ser nulo.
     * @throws IllegalArgumentException si alguno de los parámetros no cumple con las restricciones.
     */
    public Venta(int numeroTicket, LocalDate fecha, Cliente cliente) {
        if (numeroTicket <= 0) {
            throw new IllegalArgumentException("El número de ticket debe ser positivo.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        this.numeroTicket = numeroTicket;
        this.fecha = fecha;
        this.cliente = cliente;
        this.detallesVenta = new ArrayList<>();
        this.total = BigDecimal.ZERO;
        this.totalCalculado = false;
    }

    /**
     * Agrega un detalle de venta a la lista de detalles.
     * 
     * @param producto El producto que se está vendiendo, no debe ser nulo.
     * @param cantidad La cantidad del producto vendido, debe ser mayor que cero.
     * @throws IllegalArgumentException Si el producto es nulo o la cantidad es menor o igual a cero.
     */
    public void agregarDetalleVenta(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        BigDecimal precioUnitario = calcularPrecioConDescuento(producto.getPrecioVenta());
        DetalleVenta detalle = new DetalleVenta(producto, cantidad, precioUnitario);
        detallesVenta.add(detalle);
        totalCalculado = false;
    }

    /**
     * Calcula el precio con descuento aplicando cualquier descuento que el cliente tenga derecho.
     * 
     * @param precioBase Precio base del producto sin descuento.
     * @return Precio ajustado después de aplicar el descuento.
     */
    private BigDecimal calcularPrecioConDescuento(BigDecimal precioBase) {
        if (cliente instanceof ClienteProfesional) {
            BigDecimal descuento = ((ClienteProfesional) cliente).getDescuento();
            return precioBase.subtract(precioBase.multiply(descuento).divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP));
        }
        return precioBase;
    }

    /**
     * Calcula el total de la venta sumando los subtotales de cada detalle.
     * Solo recalcula si se han añadido nuevos detalles desde la última vez.
     */
    public void calcularTotal() {
        if (!totalCalculado) {
            total = detallesVenta.stream()
                                 .map(DetalleVenta::getSubtotal)
                                 .reduce(BigDecimal.ZERO, BigDecimal::add);
            totalCalculado = true;
        }
    }

    /**
     * Obtiene la fecha de la venta.
     * 
     * @return La fecha de la venta.
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Obtiene el número de ticket de la venta.
     * 
     * @return El número de ticket.
     */
    public int getNumeroTicket() {
        return numeroTicket;
    }

    /**
     * Devuelve el total de la venta. Calcula el total si no está ya calculado.
     * 
     * @return El total de la venta.
     */
    public BigDecimal getTotal() {
        calcularTotal();
        return total;
    }

    @Override
    public String toString() {
        calcularTotal();  // Asegura que el total está actualizado antes de imprimir
        StringBuilder sb = new StringBuilder();
        sb.append("Ticket No: ").append(numeroTicket)
          .append(", Fecha: ").append(fecha)
          .append(", Cliente: ").append(cliente != null ? cliente.getNombre() : "Anónimo")
          .append("\nDetalles de Venta:\n");
        for (DetalleVenta detalle : detallesVenta) {
            sb.append(detalle).append("\n");
        }
        sb.append("Total: ").append(total);
        return sb.toString();
    }
}
