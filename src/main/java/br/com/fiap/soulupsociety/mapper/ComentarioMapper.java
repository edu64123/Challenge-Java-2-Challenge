package br.com.fiap.soulupsociety.mapper;

import br.com.fiap.soulupsociety.dto.ComentarioDTO;
import br.com.fiap.soulupsociety.models.Comentario;

public class ComentarioMapper {

    public static Comentario dtoToEntity(ComentarioDTO dto) {

        Comentario comentario = new Comentario();

        comentario.setId(dto.id());
        comentario.setTextoComentario(dto.textoComentario());
        comentario.setDataComentario(dto.dataComentario());

        return comentario;
    }

    public static ComentarioDTO toRecordDTO(Comentario comentario) {

        ComentarioDTO dto = new ComentarioDTO(
                comentario.getId(),
                comentario.getTextoComentario(),
                comentario.getDataComentario()
        );

        return dto;
    }
}