package aa4_woodshops;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Clase abstracta Producto que define los atributos y métodos comunes a todos los productos.
 * Esta clase sirve como base para diferentes tipos de productos en el sistema de gestión de inventario.
 */
public abstract class Producto {
    private String codigo;
    private String descripcion;
    private Proveedor proveedor;
    private BigDecimal precioVenta;
    private int stock;

    /**
     * Constructor para inicializar un producto con sus detalles básicos.
     * @param codigo Código único del producto.
     * @param descripcion Descripción del producto.
     * @param proveedor Proveedor del producto.
     * @param precioVenta Precio de venta del producto.
     * @param stock Cantidad en stock del producto.
     * @throws IllegalArgumentException si algún parámetro no cumple con las condiciones básicas.
     */
    public Producto(String codigo, String descripcion, Proveedor proveedor, BigDecimal precioVenta, int stock) {
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código no puede ser nulo o vacío.");
        }
        if (descripcion == null || descripcion.isEmpty()) {
            throw new IllegalArgumentException("La descripción no puede ser nula o vacía.");
        }
        if (proveedor == null) {
            throw new IllegalArgumentException("El proveedor no puede ser nulo.");
        }
        if (precioVenta == null || precioVenta.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser negativo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.proveedor = proveedor;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    // Getters
    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public Proveedor getProveedor() { return proveedor; }
    public BigDecimal getPrecioVenta() { return precioVenta; }
    public int getStock() { return stock; }

    // Setters
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    // Métodos equals y hashCode para mejorar la gestión de instancias en colecciones.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Producto producto = (Producto) obj;
        return codigo.equals(producto.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
    public void ajustarStock(int cantidad) {
        this.stock += cantidad;
    }

    
}
