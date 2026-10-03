package br.com.connectdrive.Services;

import br.com.connectdrive.entity.Usuario;
import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Usuario findById(UUID id) {
        return usuarioRepository.findById( id )
                .orElseThrow( ( ) -> new RuntimeException( "Usuario com o ID fornecido não foi encontrado!" ) );
    }

    public Usuario findBycpf( String cpf ) {
        return usuarioRepository.findByCpf( cpf )
                .orElseThrow( ( ) -> new RuntimeException( "Usuario com o CPF fornecido não foi encontrado!" ) );
    }

    public Usuario findByEmail( String email ) {

        return usuarioRepository.findByEmail( email )
                .orElseThrow( ( ) -> new RuntimeException( "Usuario com o Email fornecido não foi encontrado!" ) );
    }

    @Transactional
    public void deleteById( UUID id ) {

        boolean existe = usuarioRepository.existsById( id );

        if ( !existe ) {
            throw new RuntimeException( "Usuário com o ID fornecido não foi encontrado!" );
        }

        usuarioRepository.deleteById( id );

        log.info("Usuário com id {} deletado do sistema com sucesso!", id);
    }
}
