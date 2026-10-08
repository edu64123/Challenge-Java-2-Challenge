package br.com.fiap.soulupsociety.dto;

import java.time.LocalDate;

public record UsuarioDTO(
        Integer id,
        String nome,
        LocalDate dataNascimento,
        Long numeroCpf
) {
}