package reservalibreria.Servicios;

import java.util.Date;
import reservalibreria.Entidades.Cliente;
import reservalibreria.Entidades.Libro;
import reservalibreria.Entidades.Prestamos;
import reservalibreria.Persistencia.PrestamoDao;

public class ServiciosPrestamo {

    private PrestamoDao DAO;

    public ServiciosPrestamo() {
        this.DAO = new PrestamoDao();
    }

    public void crearPrestamo(Date fechaPrestamo, Libro libro, Cliente cliente) {
        Prestamos prestamo = new Prestamos();
        try {
            if (fechaPrestamo == null) {
                throw new Exception("Debe indicar la fecha de prestamo");
            }
            if (libro == null) {
                throw new Exception("Debe indicar el libro prestado");
            }
            if (cliente == null) {
                throw new Exception("Debe indicar el cliente que ha tomado prestado el libro");
            }
            prestamo.setLibro(libro);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setCliente(cliente);
            DAO.guardar(prestamo);
        } catch (Exception e) {
            System.out.println("error al intentar cargar el prestamo");
        }
    }

    public void devolucionLibro(Date devolucion, Libro libro, Cliente cliente) {
        Prestamos prestamo = new Prestamos();
        try {
            prestamo.setCliente(cliente);
            prestamo.setFechaDevolucion(devolucion);
            prestamo.setLibro(libro);
            DAO.eliminar(prestamo);

        } catch (Exception e) {
            System.out.println("error en devolucion libro servicio prestamo");
        }
    }

    public Iterable<Prestamos> listarPrestamos() {
        try {
            return DAO.listarPrestamos();
        } catch (Exception e) {
            System.out.println("Error en buscar prestamo");
            return null;
        }
    }

    public Iterable<Prestamos> listarPrestamosPorId(Cliente cliente) {
        try {
            return DAO.buscarPorIdCliente(cliente);
        } catch (Exception e) {
            
            return null;
        }
    }
}
