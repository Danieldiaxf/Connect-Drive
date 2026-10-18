package br.com.connectdrive.mapper;

import br.com.connectdrive.dto.req.UsuarioRequestDTO;
import br.com.connectdrive.dto.resp.UsuarioResponseDTO;
import br.com.connectdrive.entities.Usuario;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper( componentModel = "spring" )
public interface UsuarioMapper {

    List<Usuario> toListUsuario( List<UsuarioRequestDTO> usuariosRequestDTOS );

    Optional<UsuarioResponseDTO> toResponse(Optional<Usuario> usuarioEntity );

}
