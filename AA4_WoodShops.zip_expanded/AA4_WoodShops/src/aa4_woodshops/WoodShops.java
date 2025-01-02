package aa4_woodshops;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Clase principal que gestiona las tiendas y ventas para WoodShops.
 * Contiene métodos para manejar tiendas, productos, clientes y ventas, asegurando que todas las operaciones
 * sean llevadas a cabo de manera coherente y segura.
 */
public class WoodShops {
    private List<Tienda> tiendas;
    private Map<String, Cliente> clientes;

    /**
     * Constructor que inicializa las colecciones para almacenar tiendas y clientes.
     */
    public WoodShops() {
        this.tiendas = new ArrayList<>();
        this.clientes = new HashMap<>();
    }

    /**
     * Añade una nueva tienda al sistema asegurándose de que no sea nula.
     *
     * @param tienda La tienda a añadir; no debe ser nula.
     * @throws IllegalArgumentException Si la tienda es nula.
     */
    public void añadirTienda(Tienda tienda) {
        if (tienda == null) {
            throw new IllegalArgumentException("La tienda no puede ser nula.");
        }
        tiendas.add(tienda);
    }

    /**
     * Registra un nuevo cliente en el sistema, asegurándose de que no sea nulo y no esté previamente registrado.
     *
     * @param cliente El cliente a registrar; no debe ser nulo y debe tener un NIF único.
     * @throws IllegalArgumentException Si el cliente es nulo o ya está registrado.
     */
    public void registrarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }
        if (clientes.containsKey(cliente.getNif())) {  
            throw new IllegalArgumentException("El cliente ya está registrado.");
        }
        clientes.put(cliente.getNif(), cliente);  
    }

    /**
     * Lista todos los clientes, mostrando detalles basados en el tipo de cliente.
     */
    public void listarClientes() {
        clientes.values().forEach(cliente -> {
            String tipo = cliente instanceof ClienteProfesional ? "Profesional" : 
                          cliente instanceof ClienteWoodFriend ? "WoodFriend" : "Anónimo";
            System.out.println(cliente.getNif() + " - " + cliente.getNombre() + " - " + tipo);
        });
    }

    /**
     * Añade un producto a una tienda específica asegurándose de que la tienda exista y el producto no sea nulo.
     *e
     * @param codigoTienda El código de la tienda donde añadir el producto; debe existir en el sistema.
     * @param producto El producto a añadir; no debe ser nulo.
     * @throws IllegalArgumentException Si el código de tienda no corresponde a ninguna tienda o el producto es nulo.
     */
    public void añadirProductoATienda(String codigoTienda, Producto producto) {
        Optional<Tienda> tienda = tiendas.stream()
                                         .filter(t -> t.getNombre().equals(codigoTienda))
                                         .findFirst();
        if (!tienda.isPresent()) {
            throw new IllegalArgumentException("No existe ninguna tienda con el código proporcionado: " + codigoTienda);
        }
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        tienda.get().getAlmacen().añadirProducto(producto);
    }

    /**
     * Realiza una venta en una tienda específica asegurándose de que la tienda exista y la venta no sea nula.
     *
     * @param codigoTienda El código de la tienda donde realizar la venta; debe existir en el sistema.
     * @param venta La venta a realizar; no debe ser nula.
     * @throws IllegalArgumentException Si el código de tienda no corresponde a ninguna tienda o la venta es nula.
     */
    public void realizarVenta(String codigoTienda, Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La venta no puede ser nula.");
        }
        Optional<Tienda> tienda = tiendas.stream()
                                         .filter(t -> t.getNombre().equals(codigoTienda))
                                         .findFirst();
        if (!tienda.isPresent()) {
            throw new IllegalArgumentException("No existe ninguna tienda con el código proporcionado: " + codigoTienda);
        }
        tienda.get().realizarVenta(venta);
    }

    /**
     * Muestra un resumen de ventas para una tienda específica dentro de un rango de fechas.
     *
     * @param codigoTienda El código de la tienda para mostrar el resumen; debe existir en el sistema.
     * @param inicio Fecha de inicio del rango; no debe ser nula.
     * @param fin Fecha de fin del rango; no debe ser nula.
     * @throws IllegalArgumentException Si el código de tienda no corresponde a ninguna tienda o las fechas son nulas.
     */
    public void mostrarResumenVentas(String codigoTienda, LocalDate inicio, LocalDate fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas.");
        }
        Optional<Tienda> tienda = tiendas.stream()
                                         .filter(t -> t.getNombre().equals(codigoTienda))
                                         .findFirst();
        if (!tienda.isPresent()) {
            throw new IllegalArgumentException("No existe ninguna tienda con el código proporcionado: " + codigoTienda);
        }
        List<Venta> ventas = tienda.get().getVentas();
        ventas.stream()
              .filter(venta -> !venta.getFecha().isBefore(inicio) && !venta.getFecha().isAfter(fin))
              .forEach(System.out::println);
    }
}
