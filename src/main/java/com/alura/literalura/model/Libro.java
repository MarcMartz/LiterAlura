package com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    //private List<String> idiomas;
    private String idioma;
    private Integer numeroDescargas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "autor_libro",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        //this.idiomas = datosLibro.idiomas();
        this.idioma = datosLibro.idiomas().get(0);
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Titulo: ").append(titulo).append("\n");
        sb.append("Autores:\n");
        for (Autor autor : autores) {
            sb.append(autor).append("\n");
        }
        sb.append("Idiomas: ").append(String.join(", ", idioma)).append("\n");
        sb.append("Numero de Descargas: ").append(numeroDescargas).append("\n");
        return sb.toString();
    }
}