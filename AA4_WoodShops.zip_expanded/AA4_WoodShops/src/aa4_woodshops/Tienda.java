package aa4_woodshops;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * Clase que representa una tienda en el sistema de gestión de inventario.
 * Es inmutable en cuanto a sus propiedades de identificación, pero maneja listas mutables de ventas y productos.
 */
public class Tienda {
    private final String nombre;
    private final String direccion;
    private final Almacen almacen;
    private final List<Venta> ventas;

    /**
     * Constructor para crear una nueva tienda.
     *
     * @param nombre El nombre de la tienda, no puede ser nulo ni vacío.
     * @param direccion La dirección de la tienda, no puede ser nula ni vacía.
     * @throws IllegalArgumentException si el nombre o la dirección son nulos o están vacíos.
     */
    public Tienda(String nombre, String direccion) {
        validarString(nombre, "El nombre de la tienda no puede ser nulo ni vacío.");
        validarString(direccion, "La dirección de la tienda no puede ser nula ni vacía.");
        this.nombre = nombre;
        this.direccion = direccion;
        this.almacen = new Almacen();
        this.ventas = new ArrayList<>();
    }

    /**
     * Añade un producto al almacén de la tienda.
     *
     * @param producto El producto a añadir, no puede ser nulo.
     * @throws IllegalArgumentException si el producto es nulo.
     */
    public void añadirProducto(Producto producto) {
        validarObjeto(producto, "El producto no puede ser nulo.");
        almacen.añadirProducto(producto);
    }

    /**
     * Elimina un producto del almacén de la tienda basado en su código.
     *
     * @param codigoProducto Código del producto a eliminar.
     * @throws IllegalArgumentException si el código del producto es nulo o vacío.
     */
    public void removeProducto(String codigoProducto) {
        validarString(codigoProducto, "El código del producto no puede ser nulo ni vacío.");
        if (!almacen.eliminarProducto(codigoProducto)) {
            System.out.println("Producto con código " + codigoProducto + " no encontrado.");
        }
    }

    /**
     * Registra una venta en la tienda.
     *
     * @param venta La venta a registrar, no puede ser nula.
     * @throws IllegalArgumentException si la venta es nula.
     */
    public synchronized void realizarVenta(Venta venta) {
        validarObjeto(venta, "La venta no puede ser nula.");
        ventas.add(venta);
    }

    /**
     * Muestra todas las ventas realizadas en la tienda.
     */
    public void mostrarVentas() {
        if (ventas.isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }
        ventas.forEach(System.out::println);
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public Almacen getAlmacen() {
        return almacen;
    }

    public List<Venta> getVentas() {
        return new ArrayList<>(ventas);  // Defensiva copia para preservar la encapsulación
    }

    /**
     * Lista todos los productos disponibles en el almacén de la tienda.
     */
    public void listarProductos() {
        List<Producto> productos = almacen.listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles en el almacén.");
            return;
        }
        productos.forEach(producto -> System.out.println("Código: " + producto.getCodigo() + ", Descripción: " + producto.getDescripcion() + ", Precio: $" + producto.getPrecioVenta() + ", Stock: " + producto.getStock()));
    }

    /**
     * Muestra el stock de un producto específico basado en su código.
     *
     * @param codigo Código del producto para consultar el stock.
     * @throws IllegalArgumentException si el código del producto es nulo o vacío.
     */
    public void mostrarStockProducto(String codigo) {
        validarString(codigo, "El código del producto no puede ser nulo ni vacío.");
        Producto producto = almacen.mostrarStockProducto(codigo);
        if (producto != null) {
            System.out.println("Código: " + producto.getCodigo() + ", Descripción: " + producto.getDescripcion() + ", Precio: $" + producto.getPrecioVenta() + ", Stock: " + producto.getStock());
        } else {
            System.out.println("Producto con código " + codigo + " no encontrado.");
        }
    }

    /**
     * Muestra el ticket de una venta específica basado en su número de ticket.
     *
     * @param numeroTicket Número del ticket a mostrar.
     * @throws IllegalArgumentException si el número del ticket es no válido.
     */
    public void mostrarTicket(int numeroTicket) {
        validarNumeroPositivo(numeroTicket, "El número del ticket debe ser positivo.");
        Venta ventaEncontrada = ventas.stream()
                                      .filter(venta -> venta.getNumeroTicket() == numeroTicket)
                                      .findFirst()
                                      .orElse(null);
        if (ventaEncontrada != null) {
            System.out.println(ventaEncontrada);
        } else {
            System.out.println("No se encontró un ticket con el número proporcionado.");
        }
    }

    /**
     * Muestra un resumen de ventas en un rango de fechas específico.
     *
     * @param inicio Fecha de inicio del rango.
     * @param fin Fecha de fin del rango.
     * @throws IllegalArgumentException si alguna de las fechas es nula.
     */
    public void mostrarResumenVentas(LocalDate inicio, LocalDate fin) {
        validarObjeto(inicio, "La fecha de inicio no puede ser nula.");
        validarObjeto(fin, "La fecha de fin no puede ser nula.");
        boolean ventasEncontradas = ventas.stream()
                                           .filter(venta -> !venta.getFecha().isBefore(inicio) && !venta.getFecha().isAfter(fin))
                                           .peek(System.out::println)
                                           .count() > 0;
        if (!ventasEncontradas) {
            System.out.println("No se encontraron ventas en el rango de fechas especificado.");
        }
    }

    // Métodos privados de validación
    private void validarString(String valor, String mensajeError) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    private void validarObjeto(Object objeto, String mensajeError) {
        if (objeto == null) {
            throw new IllegalArgumentException(mensajeError);
        }
    }

    private void validarNumeroPositivo(int numero, String mensajeError) {
        if (numero <= 0) {
            throw new IllegalArgumentException(mensajeError);
        }
    }
}
