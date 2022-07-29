/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reservalibreria.Servicios;

import java.util.Collection;
import java.util.List;
import reservalibreria.Entidades.Autor;
import reservalibreria.Entidades.Editorial;
import reservalibreria.Entidades.Libro;
import reservalibreria.Persistencia.LibroDAO;

public class ServicioLibro {

    private LibroDAO DAO;

    public ServicioLibro() {
        this.DAO = new LibroDAO();
    }

    public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Autor autor, Editorial editorial) {
        Libro libro = new Libro();
        try {
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(0);
            libro.setEjemplaresRestantes(ejemplares - libro.getEjemplaresPrestados());
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            DAO.guardar(libro);
            return libro;

        } catch (Exception e) {

            e.printStackTrace();

            System.out.println("Ha ocurrido error creando libro");
            System.out.println(e.toString());

            return null;
        }
    }

    public Libro buscarLibroPorNombre(String nombre) {
        try {
            if (nombre == null) {
                throw new Exception("EL libro no aparece en la base de datos");
            }
            Libro libro = DAO.buscarLibroNombre(nombre);
            return libro;
        } catch (Exception e) {
            return null;
        }
    }

    public void editarLibroPorDevolucion(Libro libroNombre) {

        try {
            libroNombre.setEjemplaresPrestados(libroNombre.getEjemplaresPrestados() - 1);
            libroNombre.setEjemplaresRestantes(libroNombre.getEjemplaresRestantes() + 1);
            DAO.modificarLibro(libroNombre);
        } catch (Exception e) {
            System.out.println("error en editar libro por devolucion");
        }
    }

    public void prestamoLibro(Libro libroPrestamo) {

        try {
            libroPrestamo.setEjemplaresPrestados(libroPrestamo.getEjemplaresPrestados() + 1);
            libroPrestamo.setEjemplaresRestantes(libroPrestamo.getEjemplaresRestantes() - 1);
            DAO.modificarLibro(libroPrestamo);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("erro en la carga del prestamo " + e.getMessage());
        }
    }

    public Iterable<Libro> listarLibros() {
        try {
            return DAO.listarTodos();
        } catch (Exception e) {
            return null;
        }
    }

}
