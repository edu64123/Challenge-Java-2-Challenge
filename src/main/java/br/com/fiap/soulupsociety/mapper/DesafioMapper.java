package br.com.fiap.soulupsociety.mapper;

import br.com.fiap.soulupsociety.dto.DesafioDTO;
import br.com.fiap.soulupsociety.models.Desafio;

public class DesafioMapper {

    public static Desafio dtoToEntity(DesafioDTO dto) {

        Desafio desafio = new Desafio();

        desafio.setId(dto.id());
        desafio.setNome(dto.nome());
        desafio.setDescricaoDesafio(dto.descricaoDesafio());
        desafio.setQtdPontos(dto.qtdPontos());

        return desafio;
    }

    public static DesafioDTO toRecordDTO(Desafio desafio) {

        DesafioDTO dto = new DesafioDTO(
                desafio.getId(),
                desafio.getNome(),
                desafio.getDescricaoDesafio(),
                desafio.getQtdPontos()
        );

        return dto;
    }
}