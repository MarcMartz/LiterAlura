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

    // Método para contar la cantidad de libros por idioma
    public int listarLibrosPorIdioma(String idioma) {
        return libroRepository.countByIdiomaContaining(idioma);
    }

}
