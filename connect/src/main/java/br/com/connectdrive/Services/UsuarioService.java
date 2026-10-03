package br.com.connectdrive.Services;

import br.com.connectdrive.entity.Usuario;
import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public Optional<Usuario> findById(UUID id ) {

        boolean existe = usuarioRepository.existsById(id);

        if( !existe ) {
            throw new RuntimeException( "Usuario com o ID fornecido não foi encontrado!" );
        }

        return usuarioRepository.findById( id );

    }

    @Transactional
    public void deleteById( UUID id ) {

        boolean existe = usuarioRepository.existsById(id);

        if ( !existe ) {
            throw new RuntimeException("Usuário com o ID fornecido não foi encontrado!");
        }

        usuarioRepository.deleteById(id);

        log.info("Usuário com id {} deletado do sistema com sucesso!", id);
    }
}
