package com.alura.literalura.dto;

import java.util.List;

public record AutorDto(
        Long id,
        String nombre,
        Integer fechaNacimiento,
        Integer fechaFallecimiento,
        List<LibroDto> libros
) {
}
