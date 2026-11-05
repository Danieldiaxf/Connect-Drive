package br.com.connectdrive.Controller;

import br.com.connectdrive.Services.InstrutorService;
import br.com.connectdrive.dto.req.InstrutorRequestDTO;
import br.com.connectdrive.dto.resp.InstrutorResponseDTO;
import br.com.connectdrive.enums.Sexo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instrutor")
public class InstrutorController {

    private final InstrutorService service;

    @PostMapping
    public void salvarInstrutor( @RequestBody InstrutorRequestDTO instrutor ){
        service.salvarInstrutor( instrutor );
    }

    @GetMapping("/cnh/{cnh}")
    public Optional<InstrutorResponseDTO> findByNumCnh( @PathVariable String cnh ) {
        return service.findByNumCnh( cnh );
    }

    @GetMapping("/sexo/{sexo}")
    public List<InstrutorResponseDTO> findBySexo( @PathVariable Sexo sexo ) {
        return service.findBySexo( sexo );
    }

    @GetMapping("/nota/{nota}")
    public List<InstrutorResponseDTO> findByNotaMedia( @PathVariable Double nota ) {
        return service.findByNotaMedia( nota );
    }

}
