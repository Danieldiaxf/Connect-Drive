package br.com.connectdrive.Services;

import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public void deleteById(UUID id ) throws Exception {

        if( id == null ) {
            throw new Exception( "Usuário não encontrado" );
        }

        usuarioRepository.deleteById( id );

        log.info( "Usuário com id {} deletado do sistema!", id );

    }

}
