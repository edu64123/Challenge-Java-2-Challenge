package br.com.fiap.soulupsociety.mapper;

import br.com.fiap.soulupsociety.dto.CarteiraDTO;
import br.com.fiap.soulupsociety.models.Carteira;

public class CarteiraMapper {

    public static Carteira dtoToEntity(CarteiraDTO dto) {

        Carteira carteira = new Carteira();

        carteira.setId(dto.id());
        carteira.setQuantidadePontos(dto.quantidadePontos());
        carteira.setValesDesconto(dto.valesDesconto());
        carteira.setQuantidadePassagens(dto.quantidadePassagens());

        return carteira;
    }

    public static CarteiraDTO toRecordDTO(Carteira carteira) {

        CarteiraDTO dto = new CarteiraDTO(
                carteira.getId(),
                carteira.getQuantidadePontos(),
                carteira.getValesDesconto(),
                carteira.getQuantidadePassagens()
        );

        return dto;
    }
}