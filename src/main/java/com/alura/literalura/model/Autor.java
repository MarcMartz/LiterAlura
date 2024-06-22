package com.alura.literalura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
//@ToString(exclude = "libros")
@NoArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @ManyToMany(mappedBy = "autores", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Libro> libros = new HashSet<>();

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaFallecimiento = datosAutor.fechaFallecimiento();
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
                ", Fecha de Nacimiento: " + fechaNacimiento +
                ", Fecha de Fallecimiento: " + fechaFallecimiento;
    }

}