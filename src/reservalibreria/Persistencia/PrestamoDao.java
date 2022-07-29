package reservalibreria.Persistencia;

import java.util.List;
import reservalibreria.Entidades.Cliente;
import reservalibreria.Entidades.Prestamos;

public class PrestamoDao extends DAO<Prestamos> {

    @Override
    public void guardar(Prestamos prestamo) {
        super.guardar(prestamo);
    }

    @Override
    public void eliminar(Prestamos prestamo) {
        super.eliminar(prestamo);
    }

    public Iterable<Prestamos> listarPrestamos() {
        conectar();
        List<Prestamos> prestamo = em.createQuery("SELECT p FROM Prestamos p")
                .getResultList();
        desconectar();
        return prestamo;
    }

    public Iterable<Prestamos> buscarPorIdCliente(Cliente parametro) {
        try {
            conectar();

            List<Prestamos> prestamo = em.createQuery("SELECT p FROM Prestamos p WHERE "
                    + "p.cliente = :cliente")
                    .setParameter("cliente", parametro)
                    .getResultList();
            desconectar();

            return prestamo;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No existen prestamos del cliente id " + parametro);
            return null;
        }
    }
}
