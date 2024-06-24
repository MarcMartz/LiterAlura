package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Datos;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.service.AutorService;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConvierteDatos;
import com.alura.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private LibroService libroService;

    @Autowired
    private AutorService autorService;

    public void muestraElMenu() {
        while (true) {
            System.out.println("\n*************** API LITERALURA ***************");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Mostrar cantidad de libros por idioma");
            System.out.println("0. Salir");
            System.out.println("***********************************************");

            int opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosBuscados();
                    break;
                case 3:
                    listarAutoresDeLibrosGuardados();
                    break;
                case 4:
                    listarAutoresVivosEnAnio();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida, por favor intente nuevamente.");
                    break;
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el titulo del libro que desea buscar:");
        var tituloLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()) {
            System.out.println("Libro Encontrado\n");

            Libro libroGuardado = libroService.guardarLibro(libroBuscado.get());
            System.out.println(libroGuardado + "\nLibro guardado con éxito");
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void listarLibrosBuscados() {
        List<Libro> libros = libroService.listarLibros();

        if (libros.isEmpty()) {
            System.out.println("Aún no has buscado libros");
        } else {
            System.out.println("Libros registrados:\n");
            for (Libro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    private void listarAutoresDeLibrosGuardados() {
        List<String> autores = autorService.listarAutoresDeLibros();

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores de libros.");
        } else {
            System.out.println("Autores de libros registrados:\n ");
            autores.forEach(System.out::println);
        }
    }

    public void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese el año para listar los autores vivos:");
        int anio = Integer.parseInt(teclado.nextLine());

        List<Autor> autores = autorService.listarAutoresVivosEnAnio(anio);

        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + anio);
        } else {
            System.out.println("Autores vivos en el año " + anio + ":");
            autores.forEach(autor -> System.out.println(autor.getNombre()));
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("Seleccione idioma:");
        System.out.println("1. Inglés");
        System.out.println("2. Español");
        int opcionIdioma = Integer.parseInt(teclado.nextLine());

        String idiomaSeleccionado = "";
        switch (opcionIdioma) {
            case 1:
                idiomaSeleccionado = "en";
                break;
            case 2:
                idiomaSeleccionado = "es";
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }
        int cantidadLibros = libroService.listarLibrosPorIdioma(idiomaSeleccionado);
        System.out.println("Cantidad de libros en idioma " + (opcionIdioma == 1 ? "inglés" : "español") + ": " + cantidadLibros);
    }

}
