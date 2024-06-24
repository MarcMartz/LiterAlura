package com.alura.literalura.dto;

import java.util.List;

public record LibroDto(
        Long id,
        String titulo,
        String idiomas,
        Integer numeroDescargas,
        List<AutorDto> autores
) {
}
