/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Persistencia;

import reservalibreria.Entidades.Cliente;

/**
 *
 * @author marti
 */
public class ClienteDao extends DAO<Cliente> {

    @Override
    public void guardar(Cliente cliente) {
        super.guardar(cliente);
    }

    public Cliente buscarPorNombre(String nombre) {
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("select c from Cliente c where c.nombre like :nombre")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            desconectar();
            return cliente;

        } catch (Exception e) {
            System.out.println("error en buscar por nombre dao cliente");
            return null;
        }
    }

    public Cliente buscarPorNombreApellido(String nombreCliente, String apellidoCliente) {
        try {
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT c from Cliente c where c.nombre like :nombre"
                    + " and c.apellido like :apellido")
                    .setParameter("nombre", nombreCliente)
                    .setParameter("apellido", apellidoCliente)
                    .getSingleResult();
            desconectar();
            return cliente;
        } catch (Exception e) {
            System.out.println("error en buscar por nombre y apellido dao cliente");
            return null;
        }
    }
}
