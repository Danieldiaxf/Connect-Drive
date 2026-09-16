package br.com.connectdrive.Controller;

import br.com.connectdrive.entity.Instrutor;
import br.com.connectdrive.enums.Sexo;
import br.com.connectdrive.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instrutor")
public class InstrutorController {

    private final InstrutorRepository instrutorRepository;

    @PostMapping
    public void salvarInstrutor( @RequestBody Instrutor instrutor ){
        instrutorRepository.save( instrutor );
    }

    @GetMapping
    public Instrutor findByNumCnh( String cnh ) {
        return instrutorRepository.findByNumCnh( cnh )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Instrutor não encontrado para CNH inserida!" ) );
    }

    @GetMapping
    public List<Instrutor> findBySexo( Sexo sexo ) {
        return instrutorRepository.findBySexo( sexo );
    }

    @GetMapping
    public List<Instrutor> findByNotaMedia( double nota ) {
        return instrutorRepository.findByNotaMedia( nota );
    }

}
