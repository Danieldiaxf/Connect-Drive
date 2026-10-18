package br.com.connectdrive.mapper;


import br.com.connectdrive.dto.req.InstrutorRequestDTO;
import br.com.connectdrive.dto.resp.InstrutorResponseDTO;
import br.com.connectdrive.entities.Instrutor;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper( componentModel = "spring" )
public interface InstrutorMapper {

    List<InstrutorResponseDTO> toListInstrutor( List<Instrutor> usuariosRequestDTOS );

    Instrutor toEntity( InstrutorRequestDTO instrutor );

    InstrutorResponseDTO toDTO( Instrutor instrutor );

    default Optional<InstrutorResponseDTO> toOptionalDTO( Optional<Instrutor> instrutorOptional ) {
        return instrutorOptional.map( this::toDTO );
    }

}
