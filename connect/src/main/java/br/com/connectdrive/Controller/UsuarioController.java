package br.com.connectdrive.Controller;

import br.com.connectdrive.Services.UsuarioService;
import br.com.connectdrive.entity.Usuario;
import br.com.connectdrive.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario findById(@PathVariable UUID id ) {
        return usuarioService.findById( id );
    }

    @GetMapping("/CPF/{cpf}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario findByCpf( @PathVariable String cpf ) {
        return usuarioService.findBycpf( cpf );
    }

    @GetMapping("/Email/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Usuario findByEmail( @PathVariable String email ) {
        return usuarioService.findByEmail( email );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsuarioById(@PathVariable UUID id ) {
        usuarioService.deleteById( id );
    }
}
