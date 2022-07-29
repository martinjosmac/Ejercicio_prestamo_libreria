/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Persistencia;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import reservalibreria.Entidades.Libro;
import reservalibreria.Servicios.ServicioLibro;

/**
 *
 * @author marti
 */
public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public Libro buscarLibroNombre(String nombre) {

        try {
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
                    .setParameter("titulo", nombre)
                    .getSingleResult();
            desconectar();
            return libro;

        } catch (Exception e) {
            System.out.println("Problemas en buscar libro en dao libro");
            return null;
        }
    }

    public Collection<Libro> listarTodos() {
        conectar();
        List<Libro> lib = em.createQuery("SELECT l FROM Libro l")
                .getResultList();
        desconectar();
        return lib;
    }

    public void modificarLibro(Libro libro) {
        ServicioLibro servicioLibro=new ServicioLibro();
        try {
            if (libro == null) {
                throw new Exception("El devolucion no se pudo cargar");
            }
           
            super.editar(libro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
