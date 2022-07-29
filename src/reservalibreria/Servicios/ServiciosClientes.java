package reservalibreria.Servicios;

import reservalibreria.Entidades.Cliente;
import reservalibreria.Persistencia.ClienteDao;

public class ServiciosClientes {

    private ClienteDao DAO;

    public ServiciosClientes() {
        this.DAO = new ClienteDao();
    }

    public void crearCliente(Long dni, String nombre, String apellido, String telefono) {
        Cliente cliente = new Cliente();
        if (dni == null) {
            System.out.println("Debe ingresar el dni");
        }
        if (nombre == null) {
            System.out.println("Debe ingresar el nombre del cliente");
        }
        if (apellido == null) {
            System.out.println("Debe ingresar el apellido del cliente");
        }

        if (telefono.length() != 10) {
            System.out.println("El telefono debe tener 10 digitos");
        }
        try {
            cliente.setDni(dni);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);

            DAO.guardar(cliente);

        } catch (Exception e) {
            System.out.print("Error en servicio cliente guardar cliente ");
            throw e;
        }
    }

    public Cliente buscarClientePorNombre(String nombre) {
        if (nombre == null) {
            System.out.println("Debe ingresar el nombre del cliente");
        }
        try {
            Cliente cliente = DAO.buscarPorNombre(nombre);
            return cliente;

        } catch (Exception e) {
            return null;
        }
    }

    public Cliente buscarClientePorNombreApellido(String nombreCliente, String apellidoCliente) {
        if (nombreCliente.equalsIgnoreCase(null)) {
            System.out.println("Debe ingresar el nombre del cliente");
        }
        if (apellidoCliente.equalsIgnoreCase(null)) {
            System.out.println("Debe ingresar el apellido del cliente");
        }
        try {
            Cliente cliente = DAO.buscarPorNombreApellido(nombreCliente, apellidoCliente);
            return cliente;
        } catch (Exception e) {
            return null;
        }
    }

}
