package br.com.connectdrive.mapper;

import br.com.connectdrive.dto.req.AlunoRequestDTO;
import br.com.connectdrive.dto.req.UsuarioRequestDTO;
import br.com.connectdrive.dto.resp.AlunoResponseDTO;
import br.com.connectdrive.entities.Aluno;
import br.com.connectdrive.entities.Usuario;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper( componentModel = "string" )
public interface AlunoMapper {

    List<UsuarioRequestDTO> toListUsuario( List<Usuario> usuariosRequestDTOS );

    Optional<AlunoResponseDTO> toUsuarioOptional( Optional<Aluno> usuarioEntity );
}
