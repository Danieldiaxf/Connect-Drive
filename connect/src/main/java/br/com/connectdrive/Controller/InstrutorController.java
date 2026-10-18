package br.com.connectdrive.Controller;

import br.com.connectdrive.Services.InstrutorService;
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

    private final InstrutorService service;

    @PostMapping
    public void salvarInstrutor( @RequestBody Instrutor instrutor ){
        service.salvarInstrutor( instrutor );
    }

    @GetMapping("/cnh/{cnh}")
    public Instrutor findByNumCnh( @PathVariable String cnh ) {
        return service.findByNumCnh( cnh );
    }

    @GetMapping("/sexo/{sexo}")
    public List<Instrutor> findBySexo( @PathVariable Sexo sexo ) {
        return service.findBySexo( sexo );
    }

    @GetMapping("/nota/{nota}")
    public List<Instrutor> findByNotaMedia( @PathVariable Double nota ) {
        return service.findByNotaMedia( nota );
    }

}
