package br.com.connectdrive.Services;

import br.com.connectdrive.entities.Usuario;
import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario findById( Long id ) {

        log.info( "Requisicao findById solicitada as {} para o id {}", LocalTime.now( ), id );

        return usuarioRepository.findById( id )
                .orElseThrow( ( ) -> new RuntimeException( "Usuario com o ID fornecido não foi encontrado!" ) );
    }

    public Usuario findByCpf( String cpf ) {

        log.info( "Requisicao findByCpf solicitada as {} para o usuario {}", LocalTime.now( ), cpf );

        return usuarioRepository.findByCpf( cpf )
                .orElseThrow( ( ) -> new RuntimeException( "Usuario com o CPF fornecido não foi encontrado!" ) );
    }

    public Usuario findByEmail( String email ) {

        log.info( "Requisicao findByEmail solicitada as {} para o usuario {}", LocalTime.now( ), email );

        return usuarioRepository.findByEmail( email )
                .orElseThrow( ( ) -> new RuntimeException( "Usuario com o Email fornecido não foi encontrado!" ) );
    }

    @Transactional
    public void deleteById( Long id ) {

        log.info( "Requisicao deleteById solicitada as {} para o id {}", LocalTime.now( ), id );

        boolean existe = usuarioRepository.existsById( id );

        if ( !existe ) {
            throw new RuntimeException( "Usuário com o ID fornecido não foi encontrado!" );
        }

        usuarioRepository.deleteById( id );

        log.info( "Usuário com id {} deletado do sistema com sucesso!", id );
    }
}
