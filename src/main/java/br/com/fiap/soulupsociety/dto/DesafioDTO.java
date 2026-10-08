package br.com.fiap.soulupsociety.dto;

public record DesafioDTO(
        Integer id,
        String nome,
        String descricaoDesafio,
        int qtdPontos
) {
}