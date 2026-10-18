package br.com.connectdrive.Services;

import br.com.connectdrive.dto.resp.UsuarioResponseDTO;
import br.com.connectdrive.entities.Usuario;
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

        return mapper.toResponse( usuarioRepository.findById( id ) );
    }

    public Usuario findByCpf( String cpf ) {

        log.info( "Requisicao findByCpf solicitada as {} para o usuario {}", LocalTime.now( ), cpf );

        return usuarioRepository.findByCpf( cpf )
                .orElseThrow( ( ) -> new IllegalArgumentException( "Usuario com o CPF fornecido não encontrado!" ) );
    }

    public Usuario findByEmail( String email ) {

        log.info( "Requisicao findByEmail solicitada as {} para o usuario {}", LocalTime.now( ), email );

        return usuarioRepository.findByEmail( email )
                .orElseThrow( ( ) -> new IllegalArgumentException( "Usuario com o Email fornecido não encontrado!" ) );
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
