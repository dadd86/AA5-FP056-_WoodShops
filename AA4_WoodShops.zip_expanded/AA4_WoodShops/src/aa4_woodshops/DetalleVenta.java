package aa4_woodshops;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Clase DetalleVenta que representa un elemento individual dentro de una transacción de venta.
 * Esta clase se encarga de gestionar y calcular el subtotal de cada producto vendido.
 * Utiliza BigDecimal para el manejo preciso de cálculos financieros.
 */
public class DetalleVenta {
    // Variables de instancia final para asegurar que no sean modificadas después de su inicialización
    private final Producto producto;         // Producto comprado
    private final int cantidad;              // Cantidad del producto comprado
    private final BigDecimal precioUnitario; // Precio unitario del producto
    private BigDecimal subtotal;             // Subtotal calculado del detalle de venta

    /**
     * Constructor que inicializa una nueva instancia de DetalleVenta.
     * @param producto Producto vendido.
     * @param cantidad Cantidad vendida, debe ser mayor que cero.
     * @param precioUnitario Precio por unidad, no debe ser nulo ni negativo.
     * @throws IllegalArgumentException Si los argumentos no cumplen con las condiciones de validación.
     */
    public DetalleVenta(Producto producto, int cantidad, BigDecimal precioUnitario) {
        this.producto = validarProducto(producto);
        this.cantidad = validarCantidad(cantidad);
        this.precioUnitario = validarPrecioUnitario(precioUnitario);
        calcularSubtotal();
    }

    /**
     * Valida el producto proporcionado.
     * @param producto Producto a validar.
     * @return Producto validado.
     * @throws IllegalArgumentException Si el producto es nulo.
     */
    private Producto validarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        return producto;
    }

    /**
     * Valida la cantidad proporcionada.
     * @param cantidad Cantidad a validar.
     * @return Cantidad validada.
     * @throws IllegalArgumentException Si la cantidad es menor o igual a cero.
     */
    private int validarCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que cero.");
        }
        return cantidad;
    }

    /**
     * Valida el precio unitario proporcionado.
     * @param precioUnitario Precio unitario a validar.
     * @return Precio unitario validado.
     * @throws IllegalArgumentException Si el precio unitario es nulo o negativo.
     */
    private BigDecimal validarPrecioUnitario(BigDecimal precioUnitario) {
        if (precioUnitario == null || precioUnitario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser nulo ni negativo.");
        }
        return precioUnitario.setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Calcula el subtotal para el detalle de venta multiplicando el precio unitario por la cantidad.
     */
    private void calcularSubtotal() {
        subtotal = precioUnitario.multiply(BigDecimal.valueOf(cantidad)).setScale(2, RoundingMode.HALF_EVEN);
    }

    /**
     * Obtiene el subtotal del detalle de venta.
     * @return Subtotal como BigDecimal.
     */
    public BigDecimal getSubtotal() {
        return subtotal;
    }

    /**
     * Genera una representación en cadena del detalle de venta, incluyendo descripción del producto,
     * cantidad, precio unitario y subtotal.
     * @return Descripción del detalle de venta.
     */
    @Override
    public String toString() {
        return "Producto: " + producto.getDescripcion() +
               ", Cantidad: " + cantidad +
               ", Precio Unitario: $" + precioUnitario +
               ", Subtotal: $" + subtotal;
    }
}
