package br.com.fiap.soulupsociety.dto;

import java.time.LocalDate;

public record ComentarioDTO(
        Integer id,
        String textoComentario,
        LocalDate dataComentario
) {
}