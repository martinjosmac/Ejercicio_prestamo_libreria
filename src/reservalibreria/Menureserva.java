package reservalibreria;

import java.util.Date;
import java.util.Objects;
import java.util.Scanner;
import reservalibreria.Entidades.Cliente;
import reservalibreria.Entidades.Libro;
import reservalibreria.Entidades.Prestamos;
import reservalibreria.Servicios.ServicioEditorial;
import reservalibreria.Servicios.ServicioLibro;
import reservalibreria.Servicios.ServiciosAutor;
import reservalibreria.Servicios.ServiciosClientes;
import reservalibreria.Servicios.ServiciosPrestamo;

class Menureserva {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    //instancio las clases servicios
    ServiciosClientes servCli = new ServiciosClientes();
    ServicioEditorial servicioEdi = new ServicioEditorial();
    ServicioLibro serLibro = new ServicioLibro();
    ServiciosAutor serAu = new ServiciosAutor();
    ServiciosPrestamo serPre = new ServiciosPrestamo();

    void ejecucion() {
        //cargo los clientes
        try {
            servCli.crearCliente(32460557L, "Martin", "Machado", "3816902459");
            servCli.crearCliente(31254428L, "Josefina", "Lagos", "3816415878");
            servCli.crearCliente(35124334L, "Pedro", "Farias", "3815295247");

        } catch (Exception e) {
            System.out.println("Error en menu reserva crear cliente");
            throw e;

        }
        //cargo los autores
        try {
            serAu.crearAutor("Charles Dickens");
            serAu.crearAutor("Valerio Massimo Manfredi");
            serAu.crearAutor("Alexander Fullerton");
            serAu.crearAutor("Hugo Wast");
            serAu.crearAutor("Julio Verne");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error en menu ejecutar ingresar autores: " + e.getMessage());
        }
//        cargo las editoriales
        try {
            servicioEdi.crearEditorial("Mirahadas");
            servicioEdi.crearEditorial("Nordica");
            servicioEdi.crearEditorial("Pan");
            servicioEdi.crearEditorial("Impedimenta");
            servicioEdi.crearEditorial("DeBolsillo");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error en menu al ejecutar el ingreso editoriales: " + e.getMessage());
        }
//        cargo los libros
        try {
            serLibro.crearLibro(123456L, "Alexander", 1986, 20,
                    serAu.buscarAutor("Valerio Massimo Manfredi"),
                    servicioEdi.buscarEditorialPorNombre("DeBolsillo"));

            serLibro.crearLibro(789456132L, "60 Minutes for St. George", 1974, 15,
                    serAu.buscarAutor("Alexander Fullerton"),
                    servicioEdi.buscarEditorialPorNombre("Pan"));

            serLibro.crearLibro(654654L, "Valle negro", 1918, 10,
                    serAu.buscarAutor("Hugo Wast"),
                    servicioEdi.buscarEditorialPorNombre("Nordica"));

            serLibro.crearLibro(753159L, "La vuelta al mundo en 80 dias", 1872, 30,
                    serAu.buscarAutor("Julio Verne"),
                    servicioEdi.buscarEditorialPorNombre("Mirahadas"));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error en el menu reserva a la hora de pasar parametros para crear libro");
        }
        menu();
    }

    public void menu() {
        String resp = null;
        do {
            int opciones = opcionMenu();
            ejecucionOpciones(opciones);
            System.out.println("desea ingresar otra opcion");
            resp = leer.next();
        } while (!resp.equalsIgnoreCase("n"));
    }

    private int opcionMenu() {
        System.out.println("Elija la opcion deseada\n"
                + "1-Cargar prestamo\n"
                + "2-Cargar devolucion\n"
                + "3-Buscar prestamos de un cliente\n"
                + "4-Mostrar lista de libros");
        int opcion = leer.nextInt();
        return opcion;
    }
//        cargo los prestamos//        cargo los prestamos

    private void ejecucionOpciones(int opciones) {
        switch (opciones) {
            case 1:
                //cargar prestamo
                try {
                    System.out.println("ingrese año, mes y dia del prestamo");
                    System.out.println("año");
                    int anio = leer.nextInt();
                    System.out.println("mes");
                    int mes = leer.nextInt();
                    System.out.println("dia");
                    int dia = leer.nextInt();
                    Date fecha = new Date(anio - 1900, mes - 1, dia);
                    System.out.println("Ingrese nombre del libro");
                    String nombreLibro = leer.next();
                    System.out.println("Ingrese nombre y apellido del cliente");
                    System.out.println("Nombre");
                    String nombreCliente = leer.next();
                    System.out.println("Apellido");
                    String apellidoCliente = leer.next();
                    serPre.crearPrestamo(fecha, serLibro.buscarLibroPorNombre(nombreLibro), servCli.buscarClientePorNombreApellido(nombreCliente, apellidoCliente));
                    serLibro.prestamoLibro(serLibro.buscarLibroPorNombre(nombreLibro));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
//        cargo las devoluciones//        cargo las devoluciones
            case 2:
                try {
                    //pido datos del libro y del cliente para cargar
                    System.out.println("ingrese fecha de devolucion");
                    System.out.println("año");
                    int anio = leer.nextInt();
                    System.out.println("mes");
                    int mes = leer.nextInt();
                    System.out.println("dia");
                    int dia = leer.nextInt();
                    Date fecha = new Date(anio - 1900, mes, dia);
                    System.out.println("Ingrese el nombre del libro q devuelve");
                    String nombre = leer.next();
                    System.out.println("ingrese el nombre y apellido del cliente");
                    System.out.println("Nombre");
                    String nombreCliente = leer.next();
                    System.out.println("Apellido");
                    String apellidoCliente = leer.next();
                    //cargo la devolucion a traves del servicio, llamo a buscar por nombre para validar q exista el libro
                    //busco el cliente por nombre y apellido para validar tambien
                    serPre.devolucionLibro(fecha, serLibro.buscarLibroPorNombre(nombre), servCli.buscarClientePorNombreApellido(nombreCliente, apellidoCliente));
                    //edito el prestamo realizado anteriormente o lo borro..busco el libro por nombre para validarlo
                    serLibro.editarLibroPorDevolucion(serLibro.buscarLibroPorNombre(nombre));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 3:
                try {
                    System.out.println("Ingrese nombre y apellido del cliente");
                    System.out.println("Nombre");
                    String nombreCli = leer.next();
                    System.out.println("Apellido");
                    String apellidClie = leer.next();
                    Cliente cliente = servCli.buscarClientePorNombreApellido(nombreCli, apellidClie);
//                    listo el cliente recorriendo una lista de prestamos le paso como parametro
//                            el el cliente
                    for (Prestamos aux : serPre.listarPrestamosPorId(cliente)) {
                        //voy getteando los atributos q quiero q aparezcan
                        System.out.println("Cliente: " + aux.getCliente().getNombre() + aux.getCliente().getApellido() + ""
                                + " Libro prestado: " + aux.getLibro().getTitulo());
                    }
                } catch (Exception e) {
                    System.out.println("No existe un cliente con ese nombre que haya pedido un prestamo");
                }
                break;
            case 4:
                try {
                    for (Libro aux : serLibro.listarLibros()) {
                        System.out.println(aux);
                    }
                } catch (Exception e) {
                }

        }
    }
}
