package br.com.connectdrive.mapper;

import br.com.connectdrive.dto.req.UsuarioRequestDTO;
import br.com.connectdrive.dto.resp.UsuarioResponseDTO;
import br.com.connectdrive.entities.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper( componentModel = "spring" )
public interface UsuarioMapper {

    Usuario toUsuarioEnity ( UsuarioRequestDTO usuarioRequestDTO );

    Usuario toUsuarioEntity( UsuarioResponseDTO usuarioResponseDTO );

    List<Usuario> toListUsuario( List<UsuarioRequestDTO> usuariosRequestDTOS );

}
