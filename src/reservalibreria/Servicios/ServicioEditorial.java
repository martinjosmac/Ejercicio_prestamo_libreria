/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Servicios;

import reservalibreria.Entidades.Editorial;
import reservalibreria.Persistencia.EditorialDAo;

/**
 *
 * @author marti
 */
public class ServicioEditorial {

    private EditorialDAo DAO;

    public ServicioEditorial() {
        this.DAO = new EditorialDAo();
    }

    public Editorial buscarEditorialPorNombre(String nombre) {
        if (nombre == null) {
            System.out.println("Debe ingresar el nombre de la editorial");
        }
        try {
            Editorial editorial = DAO.buscarPorNombre(nombre);
            return editorial;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error en servicio editorial buscar editorial por nombre");
            return null;
        }
    }

    public Editorial crearEditorial(String nombre) {
        Editorial editorial = new Editorial();
        try {
            if (nombre == null) {
                System.out.println("Debe ingresar el nombre de la nueva editorial");
            }
            editorial.setNombre(nombre);
            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println("error en crear editorial servicio editorial");
            return null;
        }
    }

}
