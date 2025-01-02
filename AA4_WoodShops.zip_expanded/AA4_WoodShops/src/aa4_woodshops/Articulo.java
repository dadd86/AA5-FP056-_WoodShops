package aa4_woodshops;

import java.math.BigDecimal;

/**
 * Clase Articulo que extiende de Producto y añade un atributo específico para artículos.
 * Esta clase maneja la creación y gestión de artículos específicos en el inventario, incluyendo
 * información sobre el tipo de artículo según una enumeración definida.
 */
public class Articulo extends Producto {
    private TipoArticulo tipoArticulo;

    /**
     * Constructor para un artículo.
     * @param codigo Código del artículo.
     * @param descripcion Descripción del artículo.
     * @param proveedor Proveedor del artículo.
     * @param precioVenta Precio de venta del artículo.
     * @param stock Cantidad en stock del artículo.
     * @param tipoArticulo Tipo de artículo (enumeración), no puede ser nulo.
     * @throws IllegalArgumentException si el tipo de artículo es nulo.
     */
    public Articulo(String codigo, String descripcion, Proveedor proveedor, BigDecimal precioVenta, int stock, TipoArticulo tipoArticulo) {
        super(codigo, descripcion, proveedor, precioVenta, stock);
        if (tipoArticulo == null) {
            throw new IllegalArgumentException("El tipo de artículo no puede ser nulo.");
        }
        this.tipoArticulo = tipoArticulo;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        if (tipoArticulo == null) {
            throw new IllegalArgumentException("El tipo de artículo no puede ser nulo.");
        }
        this.tipoArticulo = tipoArticulo;
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo de Artículo: " + tipoArticulo;
    }
}
