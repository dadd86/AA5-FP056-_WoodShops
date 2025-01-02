package aa4_woodshops;
import java.math.BigDecimal;
/**
 * Clase que extiende de Producto para representar los detalles específicos de un barniz.
 * Incluye atributos para la cantidad en mililitros y el color del barniz.
 * Esta clase asegura que se manejen barnices con propiedades validadas, evitando inconsistencias en los atributos.
 *
 * @author Diego Armando Diaz Devia
 * @version 1.0
 * @since 2024-03-15
 */

public class Barniz extends Producto {
    private int mililitros;
    private ColorBarniz color;

    /**
     * Constructor para crear un objeto Barniz con todos los detalles necesarios.
     * @param codigo Código único del barniz.
     * @param descripcion Descripción textual del barniz.
     * @param proveedor Proveedor que suministra el barniz.
     * @param precioVenta Precio al que se vende el barniz.
     * @param stock Cantidad disponible en stock.
     * @param mililitros Cantidad de barniz en mililitros, debe ser un valor positivo.
     * @param color Color del barniz, no puede ser nulo.
     * @throws IllegalArgumentException si los mililitros son negativos o el color es nulo.
     */
    public Barniz(String codigo, String descripcion, Proveedor proveedor, BigDecimal precioVenta, int stock, int mililitros, ColorBarniz color) {
        super(codigo, descripcion, proveedor, precioVenta, stock);
        if (mililitros < 0) {
            throw new IllegalArgumentException("Los mililitros no pueden ser negativos.");
        }
        if (color == null) {
            throw new IllegalArgumentException("El color del barniz no puede ser nulo.");
        }
        this.mililitros = mililitros;
        this.color = color;
    }

    // Getters y setters
    public int getMililitros() {
        return mililitros;
    }

    public void setMililitros(int mililitros) {
        if (mililitros < 0) {
            throw new IllegalArgumentException("Los mililitros no pueden ser negativos.");
        }
        this.mililitros = mililitros;
    }

    public ColorBarniz getColor() {
        return color;
    }

    @Override
    public String toString() {
        return super.toString() + " | Mililitros: " + mililitros + " | Color: " + color;
    }
}
