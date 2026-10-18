package br.com.connectdrive.mapper;

import br.com.connectdrive.dto.resp.AlunoResponseDTO;
import br.com.connectdrive.entities.Aluno;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper( componentModel = "string" )
public interface AlunoMapper {

    List<AlunoResponseDTO> toListAluno( List<Aluno> usuariosRequestDTOS );

    AlunoResponseDTO toDTO( Aluno Aluno );

    default Optional<AlunoResponseDTO> toOptionalDTO( Optional<Aluno> alunoOptional ) {
        return alunoOptional.map( this::toDTO );
    }
}
