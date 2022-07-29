/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Persistencia;

import reservalibreria.Entidades.Autor;

/**
 *
 * @author marti
 */
public class AutorDao extends DAO<Autor> {

    @Override
    public void guardar(Autor autor) {
        super.guardar(autor);
    }

    public Autor buscarPorNombre(String nombre) {
        try {
            conectar();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE :nombre ")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            desconectar();
            return autor;
        } catch (Exception e) {
            System.out.println("error en autor dao buscar por nombre");
            return null;
        }
    }

}
