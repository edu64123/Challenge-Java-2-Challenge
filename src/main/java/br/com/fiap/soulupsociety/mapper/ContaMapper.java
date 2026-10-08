package br.com.fiap.soulupsociety.mapper;

import br.com.fiap.soulupsociety.dto.ContaDTO;
import br.com.fiap.soulupsociety.models.Conta;

public class ContaMapper {

    public static Conta dtoToEntity(ContaDTO dto) {

        Conta conta = new Conta();

        conta.setId(dto.id());
        conta.setNomeConta(dto.nomeConta());
        conta.setEmail(dto.email());
        conta.setDataCriacao(dto.dataCriacao());
        conta.setBio(dto.bio());
        conta.setTipoConta(dto.TipoConta());

        return conta;
    }

    public static ContaDTO toRecordDTO(Conta conta) {

        ContaDTO dto = new ContaDTO(
                conta.getId(),
                conta.getNomeConta(),
                conta.getEmail(),
                conta.getDataCriacao(),
                conta.getBio(),
                conta.getTipoConta()
        );

        return dto;
    }
}