package br.com.connectdrive.Services;

import br.com.connectdrive.dto.resp.UsuarioResponseDTO;
import br.com.connectdrive.mapper.UsuarioMapper;
import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalTime;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper mapper;

    public Optional<UsuarioResponseDTO> findById( Long id ) {

        log.info( "Requisicao findById solicitada as {} para o id {}", LocalTime.now( ), id );

        return mapper.toOptionalDTO( usuarioRepository.findById( id ) );
    }

    public Optional<UsuarioResponseDTO> findByCpf( String cpf ) {

        log.info( "Requisicao findByCpf solicitada as {} para o usuario {}", LocalTime.now( ), cpf );

        return mapper.toOptionalDTO( usuarioRepository.findByCpf( cpf ) );

    }

    public Optional<UsuarioResponseDTO> findByEmail( String email ) {

        log.info( "Requisicao findByEmail solicitada as {} para o usuario {}", LocalTime.now( ), email );

        return mapper.toOptionalDTO( usuarioRepository.findByEmail( email ) );
    }

    @Transactional
    public void deleteById( Long id ) {

        log.info( "Requisicao deleteById solicitada as {} para o id {}", LocalTime.now( ), id );

        boolean existe = usuarioRepository.existsById( id );

        if ( !existe ) {
            throw new IllegalArgumentException( "Usuário com o ID fornecido não encontrado!" );
        }

        usuarioRepository.deleteById( id );

        log.info( "Usuário com id {} deletado do sistema com sucesso!", id );
    }
}
