package br.com.fiap.soulupsociety.dto;

import br.com.fiap.soulupsociety.enums.TipoContaEnum;

import java.time.LocalDate;

public record ContaDTO(
        Integer id,
        String nomeConta,
        String email,
        LocalDate dataCriacao,
        String bio,
        TipoContaEnum TipoConta
) {
}