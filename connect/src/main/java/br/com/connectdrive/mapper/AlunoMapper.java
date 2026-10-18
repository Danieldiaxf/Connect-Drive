package br.com.connectdrive.mapper;

import br.com.connectdrive.dto.req.AlunoRequestDTO;
import br.com.connectdrive.dto.resp.AlunoResponseDTO;
import br.com.connectdrive.entities.Aluno;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper( componentModel = "spring" )
public interface AlunoMapper {

    List<AlunoResponseDTO> toListAluno( List<Aluno> usuariosRequestDTOS );

    AlunoResponseDTO toDTO( Aluno Aluno );

    Aluno toEntity( AlunoRequestDTO dto );

    default Optional<AlunoResponseDTO> toOptionalDTO( Optional<Aluno> alunoOptional ) {
        return alunoOptional.map( this::toDTO );
    }
}
