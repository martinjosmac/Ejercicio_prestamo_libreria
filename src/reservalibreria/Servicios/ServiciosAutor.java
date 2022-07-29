/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Servicios;

import reservalibreria.Entidades.Autor;
import reservalibreria.Persistencia.AutorDao;

/**
 *
 * @author marti
 */
public class ServiciosAutor {

    private AutorDao DAO;

    public ServiciosAutor() {
        this.DAO = new AutorDao();
    }

    public Autor crearAutor(String nombre) {
        Autor autor = new Autor();
        try {
            autor.setNombre(nombre);

            DAO.guardar(autor);
            return autor;

        } catch (Exception e) {
            System.out.println(e.getMessage() + "el error esta en servicio autor crear autor");
            return null;
        }
    }

//    public Iterable<Autor> mostrarAutores() {
//        try {
//            return DAO.listarTodos();
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    public Autor buscarAutor(String nombre) {
        try {
            if (nombre == null) {
                throw new Exception("Debe Ingresar el nombre del autor");
            }
            Autor autor = DAO.buscarPorNombre(nombre);
//            System.out.println("Se encontro el autor llamado: " + autor.getNombre());
            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error en la busqueda del autor en el servicio Autor: " + e.toString());
            System.out.println("No se devolvio ninguna entrada para esta busqueda ");
            return null;

        }
    }
}
