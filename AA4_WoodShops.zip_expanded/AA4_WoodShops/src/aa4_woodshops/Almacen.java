package aa4_woodshops;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Clase que gestiona el inventario de productos en una tienda, utilizando un mapa para almacenar los productos por su código.
 * Proporciona métodos robustos para añadir, eliminar y listar productos, asegurando la integridad de los datos mediante validaciones.
 */
public class Almacen {
    private static final Logger logger = Logger.getLogger(Almacen.class.getName());
    private Map<String, Producto> productos;  // Mapa para almacenar productos usando su código como clave.

    /**
     * Constructor que inicializa el almacén con un mapa vacío.
     */
    public Almacen() {
        this.productos = new HashMap<>();
    }

    /**
     * Añade un producto al almacén. Si el producto ya existe (mismo código), será reemplazado.
     * 
     * @param producto El producto a añadir, no puede ser nulo.
     * @throws IllegalArgumentException si el producto es nulo.
     */
    public void añadirProducto(Producto producto) {
        if (producto == null) {
            logger.severe("Intento de añadir un producto nulo al almacén");
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        productos.put(producto.getCodigo(), producto);
        logger.info("Producto añadido: " + producto.getCodigo());
    }

    /**
     * Elimina un producto del almacén usando su código.
     *
     * @param codigo El código del producto a eliminar.
     * @return true si el producto fue eliminado exitosamente, false si el producto no se encontró.
     * @throws IllegalArgumentException si el código es nulo o vacío.
     */
    public boolean eliminarProducto(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            logger.severe("Intento de eliminar un producto con código nulo o vacío");
            throw new IllegalArgumentException("El código del producto no puede ser nulo ni vacío.");
        }
        boolean eliminado = productos.remove(codigo) != null;
        if (eliminado) {
            logger.info("Producto eliminado: " + codigo);
        } else {
            logger.warning("No se encontró el producto con código: " + codigo);
        }
        return eliminado;
    }

    /**
     * Devuelve una lista de todos los productos en el almacén.
     *
     * @return Una lista de productos.
     */
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos.values());
    }

    /**
     * Muestra el stock de un producto específico por su código.
     *
     * @param codigo El código del producto.
     * @return El producto, o null si no se encuentra.
     * @throws IllegalArgumentException si el código es nulo o vacío.
     */
    public Producto mostrarStockProducto(String codigo) {
        if (codigo == null || codigo.isEmpty()) {
            logger.severe("Intento de mostrar stock de un producto con código nulo o vacío");
            throw new IllegalArgumentException("El código del producto no puede ser nulo ni vacío.");
        }
        Producto producto = productos.get(codigo);
        if (producto == null) {
            logger.warning("No se encontró el producto con código: " + codigo);
            return null;
        }
        logger.info("Mostrando stock del producto: " + producto.getDescripcion() + " - Stock: " + producto.getStock());
        return producto;
    }
}
