package br.com.connectdrive.mapper;

import br.com.connectdrive.dto.resp.UsuarioResponseDTO;
import br.com.connectdrive.entities.Usuario;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper( componentModel = "spring" )
public interface UsuarioMapper {

    UsuarioResponseDTO toDTO( Usuario usuario );

    default Optional<UsuarioResponseDTO> toOptionalDTO( Optional<Usuario> usuarioOptional ) {
        return usuarioOptional.map(this::toDTO);
    }

}
