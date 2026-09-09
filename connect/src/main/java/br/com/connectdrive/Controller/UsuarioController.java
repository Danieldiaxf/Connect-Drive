package br.com.connectdrive.Controller;

import br.com.connectdrive.entity.Usuario;
import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @PostMapping
    public void salvarUsuario( @RequestBody Usuario usuario ) {
        usuarioRepository.save( usuario );
    }

    @GetMapping("/{id}")
    public Usuario findById( @PathVariable UUID id ) {
        return usuarioRepository.findById( id )
                .orElseThrow( () ->
                        new ResponseStatusException( HttpStatus.NOT_FOUND, "Usuário não encontrado para id inserido" ) );
    }

}
