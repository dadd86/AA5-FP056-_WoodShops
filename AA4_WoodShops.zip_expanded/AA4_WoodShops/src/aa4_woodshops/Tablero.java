package aa4_woodshops;

import java.math.BigDecimal;

/**
 * Clase Tablero que extiende de Producto y añade características específicas de tableros, como su altura, anchura y tipo.
 */
public class Tablero extends Producto {
    private BigDecimal altura;
    private BigDecimal anchura;
    private TipoTablero tipoTablero;

    /**
     * Constructor para un tablero.
     * @param codigo Código del tablero.
     * @param descripcion Descripción del tablero.
     * @param proveedor Proveedor del tablero.
     * @param precioVenta Precio de venta del tablero.
     * @param stock Cantidad en stock del tablero.
     * @param altura Altura del tablero en centímetros, debe ser un valor positivo.
     * @param anchura Anchura del tablero en centímetros, debe ser un valor positivo.
     * @param tipoTablero Tipo de tablero (enumeración), no puede ser nulo.
     * @throws IllegalArgumentException si la altura o la anchura son valores negativos.
     */
    public Tablero(String codigo, String descripcion, Proveedor proveedor, BigDecimal precioVenta, int stock, BigDecimal altura, BigDecimal anchura, TipoTablero tipoTablero) {
        super(codigo, descripcion, proveedor, precioVenta, stock);
        if (altura.compareTo(BigDecimal.ZERO) < 0 || anchura.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La altura y la anchura deben ser valores positivos.");
        }
        this.altura = altura;
        this.anchura = anchura;
        if (tipoTablero == null) {
            throw new IllegalArgumentException("El tipo de tablero no puede ser nulo.");
        }
        this.tipoTablero = tipoTablero;
    }

    public BigDecimal getAltura() {
        return altura;
    }

    public void setAltura(BigDecimal altura) {
        if (altura.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La altura debe ser un valor positivo.");
        }
        this.altura = altura;
    }

    public BigDecimal getAnchura() {
        return anchura;
    }

    public void setAnchura(BigDecimal anchura) {
        if (anchura.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("La anchura debe ser un valor positivo.");
        }
        this.anchura = anchura;
    }

    public TipoTablero getTipoTablero() {
        return tipoTablero;
    }

    public void setTipoTablero(TipoTablero tipoTablero) {
        if (tipoTablero == null) {
            throw new IllegalArgumentException("El tipo de tablero no puede ser nulo.");
        }
        this.tipoTablero = tipoTablero;
    }

    @Override
    public String toString() {
        return super.toString() + " | Altura: " + altura + " | Anchura: " + anchura + " | Tipo: " + tipoTablero;
    }
}
