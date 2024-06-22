package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.DatosAutor;
import com.alura.literalura.model.DatosLibro;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    // Método para guardar libro
    @Transactional
    public Libro guardarLibro(DatosLibro datosLibro) {
        Libro libro = new Libro(datosLibro);
        for (DatosAutor datosAutor : datosLibro.autores()) {
            Autor autor = autorRepository.findByNombre(datosAutor.nombre())
                    .orElseGet(() -> new Autor(datosAutor));
            libro.getAutores().add(autor);
            autor.getLibros().add(libro);
        }
        return libroRepository.save(libro);
    }

    // Método para listar todos los libros
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    // Método para obtener todos los autores de los libros
    public List<String> listarAutoresDeLibros() {
        List<Libro> libros = libroRepository.findAll();
        return libros.stream()
                .flatMap(libro -> libro.getAutores().stream())
                .map(autor -> autor.getNombre())
                .distinct()
                .collect(Collectors.toList());
    }

    // Método para listar autores vivos en un determinado año
    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        return autorRepository.findByFechaFallecimientoIsNullOrFechaNacimientoLessThanEqual(anio);
    }
    /*
    public List<Autor> listarAutoresVivosEnAnio(int anio) {
        List<Autor> autores = autorRepository.findAll();
        return autores.stream()
                .filter(autor -> autor.getFechaFallecimiento() == null || autor.getFechaFallecimiento() >= anio)
                .filter(autor -> autor.getFechaNacimiento() <= anio)
                .collect(Collectors.toList());
    }
     */

    // Método para contar la cantidad de libros por idioma
    public int listarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdiomaContaining(idioma);
    }



}
