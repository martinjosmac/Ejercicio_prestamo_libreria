/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Persistencia;

import reservalibreria.Entidades.Editorial;

/**
 *
 * @author marti
 */
public class EditorialDAo extends DAO<Editorial> {

    @Override
    public void guardar(Editorial editorial) {
        super.guardar(editorial);
    }

    public Editorial buscarPorNombre(String nombre) {
        try {
            conectar();
            Editorial edit = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre LIKE :nombre")
                    .setParameter("nombre", nombre)
                    .getSingleResult();
            desconectar();
            return edit;

        } catch (Exception e) {
            System.out.println("error en editorial dao buscar por nombre");
            System.out.println("no se encontro editorial");
            return null;
        }

    }

}
