package br.com.connectdrive.Controller;

import br.com.connectdrive.entities.Instrutor;
import br.com.connectdrive.enums.Sexo;
import br.com.connectdrive.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/cnh/{cnh}")
    public Instrutor findByNumCnh( @PathVariable String cnh ) {
        return instrutorRepository.findByNumCnh( cnh )
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Instrutor não encontrado para CNH inserida!" ) );
    }

    @GetMapping("/sexo/{sexo}")
    public List<Instrutor> findBySexo( @PathVariable Sexo sexo ) {
        return instrutorRepository.findBySexo( sexo );
    }

    @GetMapping("/nota/{nota}")
    public List<Instrutor> findByNotaMedia( @PathVariable Double nota ) {
        return instrutorRepository.findByNotaMedia( nota );
    }

}
