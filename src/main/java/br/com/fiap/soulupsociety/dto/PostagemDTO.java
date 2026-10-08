package br.com.fiap.soulupsociety.dto;

public record PostagemDTO(
        Integer id,
        String descricaoTexto,
        int quantidadeCurtidas,
        int compartilhamentos
) {
}