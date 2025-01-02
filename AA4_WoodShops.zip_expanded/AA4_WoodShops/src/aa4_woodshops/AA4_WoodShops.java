/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aa4_woodshops;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
/**
 * Clase principal que gestiona las operaciones de las tiendas.
 * Permite agregar y eliminar tiendas, asegurando que los nombres de las tiendas sean únicos
 * y que las direcciones no estén vacías.
 */
public class AA4_WoodShops {
	private static Scanner scanner = new Scanner(System.in);
    private static GestorProveedores gestorProveedores = new GestorProveedores();
    private static List<Tienda> tiendas = new ArrayList<>(); // Lista de tiendas
    private static Map<String, Cliente> clientes = new HashMap<>();  // Almacenar los clientes


    public static void main(String[] args) {
    	
        boolean salir = false;

        while (!salir) {
        	mostrarMenuPrincipal();
            String input = scanner.nextLine();

            try {
                int opcion = Integer.parseInt(input);
	            switch (opcion) {
	                case 1:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                            addProductoTienda();
                        }
	                    break;
	                case 2:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	removeProductoTienda();
                        }	                    
	                    break;
	                case 3:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	listarProductosPorTienda();
                        }		                    
	                    break;
	                case 4:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	mostrarStockProducto();
                        }	                    
	                    break;
	                case 5:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	listarTodosProductos();
                        }	  	                    
	                    break;
	                case 6:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	listarProductosPorTipo();
                        }		                    
	                    break;
	                case 7:
	                    agregarTienda();
	                    break;
	                case 8:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	eliminarTienda();
                        }	                	
	                    break;
	                case 9:
	                	gestorProveedores.agregarProveedor(scanner);
	                    break;
	                case 10:
	                	gestorProveedores.eliminarProveedor(scanner);
	                    break;
	                case 11:
	                    cargarDatosIniciales();
	                    break;
	                case 12:
	                	añadirCliente();
	                    break;
	                case 13:
	                	if (clientes.isEmpty()) {
                            System.out.println("No hay clientes disponibles. Por favor, añada clientes primero.");
                        } else {
                            listarClientes();
                        }
	                    break;
	                case 14:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                            añadirVenta();
                        }
	                    break;
	                case 15:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	mostrarTicket();
                        }
	                    break;
	                case 16:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	mostrarResumenTicketsPorFecha();
                        }
	                    break;
	                case 17:
	                	if (tiendas.isEmpty()) {
                            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
                        } else {
                        	 mostrarResumenVentasPorFecha();
                        }
	                    break;
	                case 18:
	                    salir = true;
	                    System.out.println("Saliendo del programa...");
	                    break;
	                default:
	                    System.out.println("Opción no válida, por favor intente nuevamente.");
	            }
            }
            catch (NumberFormatException e) {
                System.out.println("Error: Por favor ingrese un número válido.");
            }
        }
     // Es importante cerrar el scanner.
        scanner.close();
    }
    
    /**
     * Muestra las opciones del menú principal en la consola.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\nMenú Principal:");
        System.out.println("1. Añadir Producto a una Tienda");
        System.out.println("2. Eliminar Producto de una Tienda");
        System.out.println("3. Listar Productos por Tienda");
        System.out.println("4. Mostrar Stock de un Producto");
        System.out.println("5. Listar Todos los Productos");
        System.out.println("6. Listar Productos por Tipo");
        System.out.println("7. Agregar Tienda");
        System.out.println("8. Eliminar Tienda");
        System.out.println("9. Agregar Proveedor");
        System.out.println("10. Eliminar Proveedor");
        System.out.println("11. Cargar Datos Iniciales");
        System.out.println("12. Añadir Cliente");
        System.out.println("13. Listar Clientes");
        System.out.println("14. Añadir Venta");
        System.out.println("15. Mostrar Ticket");
        System.out.println("16. Mostrar Resumen Tickets Por Fecha");
        System.out.println("17. Mostrar Resumen Ventas Por Fecha");
        System.out.println("18. Salir");
        System.out.print("Seleccione una opción: ");
    }
    /**
     * Permite al usuario añadir productos a una tienda seleccionada de la lista de tiendas disponibles.
     * Añade un manejo seguro de la entrada y permite seleccionar diferentes tipos de productos.
     */
    private static void addProductoTienda() {
        if (tiendas.isEmpty()) {
            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
            return;
        }
        
        System.out.println("Seleccione una tienda de la lista:");
        for (int i = 0; i < tiendas.size(); i++) {
            System.out.println((i + 1) + ". " + tiendas.get(i).getNombre());
        }
        System.out.print("Elija el número de la tienda: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= tiendas.size()) {
            System.out.println("Selección inválida. Intente de nuevo.");
            return;
        }

        if (gestorProveedores.getProveedores().isEmpty()) {
            System.out.println("No hay proveedores disponibles. Registre un proveedor antes de añadir productos.");
            return;
        }

        System.out.println("Proveedores disponibles:");
        List<Proveedor> proveedores = gestorProveedores.getProveedores();
        for (int i = 0; i < proveedores.size(); i++) {
            Proveedor proveedor = proveedores.get(i);
            System.out.println((i + 1) + ". " + proveedor.getNombre() + " - NIF: " + proveedor.getNif());
        }

        System.out.print("Seleccione un proveedor por su número: ");
        int proveedorIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (proveedorIndex < 0 || proveedorIndex >= proveedores.size()) {
            System.out.println("Selección de proveedor inválida.");
            return;
        }

        Proveedor proveedorSeleccionado = proveedores.get(proveedorIndex);
        Tienda tiendaSeleccionada = tiendas.get(index);
        System.out.println("Añadiendo producto a " + tiendaSeleccionada.getNombre());
        System.out.println("Seleccione el tipo de producto a añadir:");
        System.out.println("1. Tablero");
        System.out.println("2. Barniz");
        System.out.println("3. Artículo");
        int tipoProducto = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el código del producto: ");
        String codigo = scanner.nextLine();
        System.out.print("Ingrese la descripción del producto: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese el precio de venta: ");
        BigDecimal precioVenta = new BigDecimal(scanner.nextLine());
        System.out.print("Ingrese la cantidad de stock: ");
        int stock = Integer.parseInt(scanner.nextLine());

        try {
            Producto nuevoProducto = crearProductoSegunTipo(tipoProducto, codigo, descripcion, proveedorSeleccionado, precioVenta, stock);
            tiendaSeleccionada.getAlmacen().añadirProducto(nuevoProducto);
            System.out.println("Producto añadido exitosamente a " + tiendaSeleccionada.getNombre());
        } catch (IllegalArgumentException e) {
            System.out.println("Error al crear el producto: " + e.getMessage());
        }
    }
    
    /**
     * Crea un producto según el tipo especificado por el usuario.
     * @return Producto creado según el tipo.
     * @throws IllegalArgumentException si el tipo de producto no es válido.
     */
    private static Producto crearProductoSegunTipo(int tipoProducto, String codigo, String descripcion, Proveedor proveedor, BigDecimal precioVenta, int stock) throws IllegalArgumentException {
        switch (tipoProducto) {
            case 1:
                return new Tablero(codigo, descripcion, proveedor, precioVenta, stock, new BigDecimal("100"), new BigDecimal("50"), TipoTablero.AGLOMERADO);
            case 2:
                return new Barniz(codigo, descripcion, proveedor, precioVenta, stock, 500, ColorBarniz.INCOLORO);
            case 3:
                return new Articulo(codigo, descripcion, proveedor, precioVenta, stock, TipoArticulo.SILLA);
            default:
                throw new IllegalArgumentException("Tipo de producto no reconocido.");
        }
    }
    
    /**
     * Permite al usuario eliminar un producto de una tienda seleccionada.
     * Se elimina el producto basado en su código, después de que el usuario elija una tienda.
     */
    private static void removeProductoTienda() {
        if (tiendas.isEmpty()) {
            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
            return;
        }

        System.out.println("Seleccione una tienda de la lista para eliminar un producto:");
        for (int i = 0; i < tiendas.size(); i++) {
            System.out.println((i + 1) + ". " + tiendas.get(i).getNombre());
        }
        System.out.print("Elija el número de la tienda: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        if (index < 0 || index >= tiendas.size()) {
            System.out.println("Selección inválida. Intente de nuevo.");
            return;
        }

        Tienda tiendaSeleccionada = tiendas.get(index);
        List<Producto> productosDisponibles = tiendaSeleccionada.getAlmacen().listarProductos();
        if (productosDisponibles.isEmpty()) {
            System.out.println("No hay productos en la tienda seleccionada. Por favor, añada productos antes de intentar eliminar.");
            return;
        }

        System.out.println("Productos disponibles para eliminar:");
        for (Producto producto : productosDisponibles) {
            System.out.println("Código: " + producto.getCodigo() + ", Descripción: " + producto.getDescripcion());
        }

        System.out.print("Ingrese el código del producto a eliminar: ");
        String codigoProducto = scanner.nextLine();
        if (tiendaSeleccionada.getAlmacen().eliminarProducto(codigoProducto)) {
            System.out.println("Producto eliminado exitosamente de " + tiendaSeleccionada.getNombre());
        } else {
            System.out.println("No se encontró un producto con el código proporcionado en " + tiendaSeleccionada.getNombre());
        }
    }

    
    /**
     * Lista todos los productos disponibles en una tienda seleccionada, mostrando detalles como el código y la descripción.
     * Esta función asegura que solo se listan productos de tiendas existentes y verifica que haya productos disponibles para mostrar.
     */
    private static void listarProductosPorTienda() {
        if (tiendas.isEmpty()) {
            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
            return;
        }

        System.out.println("Seleccione una tienda de la lista para ver sus productos:");
        for (int i = 0; i < tiendas.size(); i++) {
            System.out.println((i + 1) + ". " + tiendas.get(i).getNombre());
        }
        System.out.print("Elija el número de la tienda: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        if (index < 0 || index >= tiendas.size()) {
            System.out.println("Selección inválida. Intente de nuevo.");
            return;
        }

        Tienda tiendaSeleccionada = tiendas.get(index);
        List<Producto> productos = tiendaSeleccionada.getAlmacen().listarProductos();
        if (productos.isEmpty()) {
            System.out.println("No hay productos en " + tiendaSeleccionada.getNombre() + ".");
            return;
        }

        System.out.println("Listando productos en " + tiendaSeleccionada.getNombre() + ":");
        for (Producto producto : productos) {
            System.out.println("Código: " + producto.getCodigo() + ", Descripción: " + producto.getDescripcion() + ", Precio: $" + producto.getPrecioVenta() + ", Stock: " + producto.getStock());
        }
    }

    
    /**
     * Muestra el stock de un producto específico en todas las tiendas donde está disponible.
     * Se agregan validaciones adicionales para garantizar que la entrada del usuario sea válida
     * y para manejar correctamente los casos en los que el producto no se encuentra en ninguna tienda.
     */
    private static void mostrarStockProducto() {
        if (tiendas.isEmpty()) {
            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
            return;
        }

        System.out.print("Ingrese el código del producto para ver su stock: ");
        String codigoProducto = scanner.nextLine().trim();
        if (codigoProducto.isEmpty()) {
            System.out.println("El código del producto no puede estar vacío. Por favor, ingrese un código válido.");
            return;
        }

        boolean productoEncontrado = false;
        System.out.println("Stock del producto en las tiendas:");
        for (Tienda tienda : tiendas) {
            try {
                Producto producto = tienda.getAlmacen().mostrarStockProducto(codigoProducto);
                if (producto != null) {
                    productoEncontrado = true;
                    System.out.println(tienda.getNombre() + ": " + producto.getStock() + " unidades");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
        }

        if (!productoEncontrado) {
            System.out.println("Producto no encontrado en ninguna tienda. Asegúrese de que el código está correcto o añada el producto a alguna tienda.");
        }
    }

    
    /**
     * Lista todos los productos de todas las tiendas, mostrando detalles como el código, la descripción, el precio y la tienda.
     * Esta función asegura que solo se listan productos si existen tiendas y productos disponibles para mostrar.
     */
    private static void listarTodosProductos() {
        if (tiendas.isEmpty()) {
            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
            return;
        }

        // Indicar si al menos una tienda tiene productos
        boolean hayProductos = false;

        System.out.println("Listando todos los productos de todas las tiendas:");
        for (Tienda tienda : tiendas) {
            List<Producto> productos = tienda.getAlmacen().listarProductos(); // Usar listarProductos() en lugar de getProductos()
            if (productos.isEmpty()) {
                System.out.println("Tienda: " + tienda.getNombre() + " - No hay productos disponibles.");
            } else {
                hayProductos = true;
                System.out.println("Tienda: " + tienda.getNombre());
                for (Producto producto : productos) {
                    System.out.println("Código: " + producto.getCodigo() +
                                        ", Descripción: " + producto.getDescripcion() +
                                        ", Precio: $" + producto.getPrecioVenta() +
                                        ", Stock: " + producto.getStock());
                }
            }
        }

        if (!hayProductos) {
            System.out.println("No hay productos disponibles en ninguna tienda. Por favor, añada productos antes de intentar listar.");
        }
    }


    
    
    /**
     * Lista todos los productos por tipo desde todas las tiendas, mostrando detalles como código, descripción, precio, stock y tienda.
     */
    
    private static void listarProductosPorTipo() {
        if (tiendas.isEmpty()) {
            System.out.println("No hay tiendas disponibles. Por favor, cree una tienda primero.");
            return;
        }

        System.out.println("Seleccione el tipo de producto a listar:");
        System.out.println("1. Tablero");
        System.out.println("2. Barniz");
        System.out.println("3. Artículo");
        System.out.print("Elija una opción (1-3): ");

        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine());
            if (tipo < 1 || tipo > 3) {
                System.out.println("Opción no válida. Los números válidos son del 1 al 3.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Por favor ingrese un número válido.");
            return;
        }

        Class<?> tipoClase = TipoProductoUtil.obtenerClaseTipoProducto(tipo);
        if (tipoClase == null) {
            System.out.println("Tipo de producto no reconocido. Intente de nuevo.");
            return;
        }

        boolean hayProductos = false;
        System.out.println("Listando todos los productos del tipo: " + tipoClase.getSimpleName());
        for (Tienda tienda : tiendas) {
            List<Producto> productos = tienda.getAlmacen().listarProductos(); // Correct method call
            long count = productos.stream()
                                  .filter(tipoClase::isInstance)
                                  .peek(p -> System.out.println("Tienda: " + tienda.getNombre() + " - Código: " + p.getCodigo() +
                                                               ", Descripción: " + p.getDescripcion() + ", Precio: $" + p.getPrecioVenta() +
                                                               ", Stock: " + p.getStock()))
                                  .count();
            if (count > 0) {
                hayProductos = true;
            }
        }

        if (!hayProductos) {
            System.out.println("No se encontraron productos del tipo " + tipoClase.getSimpleName() + " en ninguna tienda.");
        }
    }

    
    /**
     * Agrega una nueva tienda a la lista después de validar que el nombre y la dirección no estén vacíos.
     * Verifica que no exista ya una tienda con el mismo nombre para mantener la unicidad.
     */
    private static void agregarTienda() {
        System.out.print("Ingrese el nombre de la nueva tienda: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre de la tienda no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese la dirección de la nueva tienda: ");
        String direccion = scanner.nextLine().trim();
        if (direccion.isEmpty()) {
            System.out.println("La dirección de la tienda no puede estar vacía.");
            return;
        }

        // Verificar si ya existe una tienda con ese nombre
        if (tiendas.stream().anyMatch(tienda -> tienda.getNombre().equalsIgnoreCase(nombre))) {
            System.out.println("Ya existe una tienda con ese nombre. Por favor, elija un nombre diferente.");
            return;
        }

        Tienda nuevaTienda = new Tienda(nombre, direccion);
        tiendas.add(nuevaTienda);
        System.out.println("Tienda agregada exitosamente.");
    }
    
    
    /**
     * Elimina una tienda basándose en el nombre proporcionado por el usuario.
     * Antes de eliminar, se verifica que el nombre de la tienda proporcionado no esté vacío
     * y se confirma si la tienda existe en la lista.
     */
    private static void eliminarTienda() {
        System.out.print("Ingrese el nombre de la tienda a eliminar: ");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("El nombre de la tienda no puede estar vacío.");
            return;
        }

        boolean eliminado = tiendas.removeIf(tienda -> tienda.getNombre().equalsIgnoreCase(nombre));
        if (eliminado) {
            System.out.println("Tienda eliminada correctamente.");
        } else {
            System.out.println("No se encontró una tienda con ese nombre.");
        }
    }
    
    /**
     * Carga datos iniciales para el sistema, incluyendo proveedores, productos y tiendas.
     */
    private static void cargarDatosIniciales() {
        System.out.println("Cargando datos iniciales...");
        // Crear algunos proveedores
        Proveedor proveedor1 = new Proveedor("123456789", "Proveedor Uno");
        Proveedor proveedor2 = new Proveedor("987654321", "Proveedor Dos");

        // Crear algunos productos con BigDecimal para las dimensiones
        Tablero tablero1 = new Tablero(
                "T001",
                "Tablero Aglomerado",
                proveedor1,
                new BigDecimal("25.50"),
                10,
                new BigDecimal("250.0"),  // Altura en cm convertida a BigDecimal
                new BigDecimal("120.0"),  // Anchura en cm convertida a BigDecimal
                TipoTablero.AGLOMERADO
        );

        Barniz barniz1 = new Barniz(
                "B001",
                "Barniz para Madera",
                proveedor2,
                new BigDecimal("15.00"),
                20,
                500, // mililitros
                ColorBarniz.INCOLORO
        );

        Articulo articulo1 = new Articulo(
                "A001",
                "Silla de madera",
                proveedor1,
                new BigDecimal("45.75"),
                15,
                TipoArticulo.SILLA
        );

        // Crear tiendas y agregar productos a sus almacenes
        Tienda tienda1 = new Tienda("Tienda Central", "123 Calle Principal");
        tienda1.getAlmacen().añadirProducto(tablero1);
        tienda1.getAlmacen().añadirProducto(barniz1);

        Tienda tienda2 = new Tienda("Tienda Secundaria", "456 Calle Secundaria");
        tienda2.getAlmacen().añadirProducto(articulo1);

        // Ejemplo de uso de los datos cargados
        System.out.println("Productos en Tienda Central:");
        for (Producto prod : tienda1.getAlmacen().listarProductos()) { // Corregido para usar listarProductos
            System.out.println(prod.getDescripcion() + " - $" + prod.getPrecioVenta());
        }

        System.out.println("Productos en Tienda Secundaria:");
        for (Producto prod : tienda2.getAlmacen().listarProductos()) { // Corregido para usar listarProductos
            System.out.println(prod.getDescripcion() + " - $" + prod.getPrecioVenta());
        }
        System.out.println("Datos iniciales cargados con éxito.");
    }
    // Añadir un cliente nuevo
    private static void añadirCliente() {
        System.out.print("Ingrese el NIF del cliente: ");
        String nif = scanner.nextLine();
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el tipo de cliente (1 para Profesional, 2 para WoodFriend): ");
        int tipo = Integer.parseInt(scanner.nextLine());

        Cliente cliente = null;
        if (tipo == 1) {
            System.out.print("Ingrese el descuento del cliente profesional (0-100): ");
            BigDecimal descuento = new BigDecimal(scanner.nextLine()); 
            cliente = new ClienteProfesional(nif, nombre, descuento);
        } else if (tipo == 2) {
            System.out.print("Ingrese el código de socio WoodFriend: ");
            String codigoSocio = scanner.nextLine();
            cliente = new ClienteWoodFriend(nif, nombre, codigoSocio);
        }

        if (cliente != null) {
            clientes.put(cliente.getNif(), cliente);
            System.out.println("Cliente añadido exitosamente.");
        }
    }

    // Listar todos los clientes
    private static void listarClientes() {
        clientes.values().forEach(cliente -> {
            String tipo = cliente instanceof ClienteProfesional ? "Profesional" : 
                          cliente instanceof ClienteWoodFriend ? "WoodFriend" : "Anónimo";
            System.out.println(cliente.getNif() + " - " + cliente.getNombre() + " - " + tipo);
        });
    }

    // Añadir un ticket de venta
    private static void añadirVenta() {
        try {
            System.out.print("Seleccione una tienda de la lista:");
            for (int i = 0; i < tiendas.size(); i++) {
                System.out.println((i + 1) + ". " + tiendas.get(i).getNombre());
            }
            int indexTienda = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (indexTienda < 0 || indexTienda >= tiendas.size()) {
                System.out.println("Selección de tienda inválida.");
                return;
            }
            Tienda tiendaSeleccionada = tiendas.get(indexTienda);

            System.out.print("Ingrese el número de ticket: ");
            int numeroTicket = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("Ingrese la fecha de la venta (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Ingrese el NIF del cliente: ");
            String nifCliente = scanner.nextLine().trim();
            Cliente cliente = clientes.get(nifCliente);

            if (cliente == null) {
                System.out.println("Cliente no encontrado. Asegúrese de que el NIF es correcto.");
                return;
            }

            Venta venta = new Venta(numeroTicket, fecha, cliente);

            // Añadir productos a la venta
            while (true) {
                System.out.print("¿Desea añadir un producto a la venta? (s/n): ");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                if (!respuesta.equals("s")) {
                    break;
                }

                System.out.print("Ingrese el código del producto: ");
                String codigoProducto = scanner.nextLine().trim();
                Producto producto = tiendaSeleccionada.getAlmacen().mostrarStockProducto(codigoProducto);

                if (producto == null) {
                    System.out.println("Producto no encontrado. Asegúrese de que el código es correcto.");
                    continue;
                }

                System.out.print("Ingrese la cantidad del producto: ");
                int cantidad = Integer.parseInt(scanner.nextLine().trim());
                if (cantidad <= 0 || cantidad > producto.getStock()) {
                    System.out.println("Cantidad inválida. Asegúrese de que la cantidad es positiva y no excede el stock disponible.");
                    continue;
                }

                venta.agregarDetalleVenta(producto, cantidad);
                producto.ajustarStock(-cantidad);  // Reducir el stock del producto
            }

            tiendaSeleccionada.realizarVenta(venta);
            System.out.println("Venta añadida exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida. Por favor, ingrese valores válidos.");
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, ingrese la fecha en el formato YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al añadir la venta: " + e.getMessage());
        }
    }
    
    
    
    /**
     * Clase para manejar la obtención de tipos de productos.
     */
    public class TipoProductoUtil {

        // Mapa para almacenar la relación entre el tipo de producto y su clase correspondiente.
        private static final Map<Integer, Class<?>> tipoProductoMap = new HashMap<>();

        static {
            tipoProductoMap.put(1, Tablero.class);
            tipoProductoMap.put(2, Barniz.class);
            tipoProductoMap.put(3, Articulo.class);
        }

        /**
         * Retorna la clase correspondiente al tipo de producto seleccionado por el usuario.
         * 
         * @param tipo El tipo de producto como un entero.
         * @return La clase correspondiente al tipo de producto seleccionado.
         * @throws IllegalArgumentException si el tipo de producto no es válido.
         */
        public static Class<?> obtenerClaseTipoProducto(int tipo) {
            Class<?> claseProducto = tipoProductoMap.get(tipo);
            if (claseProducto == null) {
                throw new IllegalArgumentException("Tipo de producto no válido: " + tipo);
            }
            return claseProducto;
        }
    }
    /**
     * Muestra la información de un ticket de venta específico basado en su número.
     * Solicita al usuario el número del ticket y busca en todas las tiendas para encontrar la venta correspondiente.
     * Si se encuentra, se muestra la información del ticket; de lo contrario, se informa que no se encontró.
     */
    private static void mostrarTicket() {
        try {
            System.out.print("Ingrese el número de ticket: ");
            int numeroTicket = Integer.parseInt(scanner.nextLine().trim());

            boolean ticketEncontrado = false;
            for (Tienda tienda : tiendas) {
                for (Venta venta : tienda.getVentas()) {
                    if (venta.getNumeroTicket() == numeroTicket) {
                        System.out.println(venta.toString());
                        ticketEncontrado = true;
                        break;
                    }
                }
                if (ticketEncontrado) {
                    break;
                }
            }

            if (!ticketEncontrado) {
                System.out.println("No se encontró un ticket con el número proporcionado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número de ticket inválido. Por favor, ingrese un número válido.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al mostrar el ticket: " + e.getMessage());
        }
    }
    /**
     * Muestra un resumen de tickets de venta en un rango de fechas especificado.
     * Solicita al usuario que ingrese las fechas de inicio y fin, y busca en todas las tiendas las ventas
     * que se realizaron dentro de ese rango. Muestra la información de las ventas encontradas.
     * Si no se encuentran ventas, informa al usuario.
     */
    private static void mostrarResumenTicketsPorFecha() {
        try {
            System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
            LocalDate inicio = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
            LocalDate fin = LocalDate.parse(scanner.nextLine().trim());

            if (inicio.isAfter(fin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            boolean ventasEncontradas = false;
            for (Tienda tienda : tiendas) {
                List<Venta> ventas = tienda.getVentas();
                for (Venta venta : ventas) {
                    if (!venta.getFecha().isBefore(inicio) && !venta.getFecha().isAfter(fin)) {
                        System.out.println(venta.toString());
                        ventasEncontradas = true;
                    }
                }
            }

            if (!ventasEncontradas) {
                System.out.println("No se encontraron ventas en el rango de fechas especificado.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, ingrese la fecha en el formato YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al mostrar el resumen de tickets: " + e.getMessage());
        }
    }
    /**
     * Muestra un resumen de ventas por tienda en un rango de fechas especificado.
     * Solicita al usuario que ingrese las fechas de inicio y fin, y calcula el total de las ventas
     * realizadas dentro de ese rango por cada tienda. Muestra el nombre de la tienda y el total de las ventas.
     */
    private static void mostrarResumenVentasPorFecha() {
        try {
            System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
            LocalDate inicio = LocalDate.parse(scanner.nextLine().trim());
            System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
            LocalDate fin = LocalDate.parse(scanner.nextLine().trim());

            if (inicio.isAfter(fin)) {
                System.out.println("La fecha de inicio no puede ser posterior a la fecha de fin.");
                return;
            }

            boolean ventasEncontradas = false;
            for (Tienda tienda : tiendas) {
                BigDecimal totalVentas = BigDecimal.ZERO;
                List<Venta> ventas = tienda.getVentas();
                for (Venta venta : ventas) {
                    if (!venta.getFecha().isBefore(inicio) && !venta.getFecha().isAfter(fin)) {
                        totalVentas = totalVentas.add(venta.getTotal());
                        ventasEncontradas = true;
                    }
                }
                if (ventasEncontradas) {
                    System.out.println("Tienda: " + tienda.getNombre() + " - Total de ventas: $" + totalVentas);
                }
            }
            if (!ventasEncontradas) {
                System.out.println("No se encontraron ventas en el rango de fechas especificado.");
            }
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Por favor, ingrese la fecha en el formato YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al mostrar el resumen de ventas: " + e.getMessage());
        }
    }
}

