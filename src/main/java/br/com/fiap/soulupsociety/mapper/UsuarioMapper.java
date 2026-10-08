package br.com.fiap.soulupsociety.mapper;

import br.com.fiap.soulupsociety.dto.UsuarioDTO;
import br.com.fiap.soulupsociety.models.Usuario;

public class UsuarioMapper {

    public static Usuario dtoToEntity(UsuarioDTO dto) {

        Usuario usuario = new Usuario();

        usuario.setId(dto.id());
        usuario.setNome(dto.nome());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setNumeroCpf(dto.numeroCpf());

        return usuario;
    }

    public static UsuarioDTO toRecordDTO(Usuario usuario) {

        UsuarioDTO dto = new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getNumeroCpf()
        );

        return dto;
    }
}