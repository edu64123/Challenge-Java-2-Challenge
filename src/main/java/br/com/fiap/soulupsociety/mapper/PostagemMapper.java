package br.com.fiap.soulupsociety.mapper;

import br.com.fiap.soulupsociety.dto.PostagemDTO;
import br.com.fiap.soulupsociety.models.Postagem;

public class PostagemMapper {

    public static Postagem dtoToEntity(PostagemDTO dto) {

        Postagem postagem = new Postagem();

        postagem.setId(dto.id());
        postagem.setDescricaoTexto(dto.descricaoTexto());
        postagem.setQuantidadeCurtidas(dto.quantidadeCurtidas());
        postagem.setCompartilhamentos(dto.compartilhamentos());

        return postagem;
    }

    public static PostagemDTO toRecordDTO(Postagem postagem) {

        PostagemDTO dto = new PostagemDTO(
                postagem.getId(),
                postagem.getDescricaoTexto(),
                postagem.getQuantidadeCurtidas(),
                postagem.getCompartilhamentos()
        );

        return dto;
    }
}