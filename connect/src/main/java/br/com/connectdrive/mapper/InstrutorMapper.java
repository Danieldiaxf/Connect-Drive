package br.com.connectdrive.mapper;


import br.com.connectdrive.dto.req.InstrutorRequestDTO;
import br.com.connectdrive.entities.Instrutor;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper( componentModel = "string" )
public interface InstrutorMapper {

    List<Instrutor> toListUsuario( List<InstrutorRequestDTO> usuariosRequestDTOS );

    Optional<Instrutor> toUsuarioOptional( Optional<Instrutor> usuarioEntity );

}
