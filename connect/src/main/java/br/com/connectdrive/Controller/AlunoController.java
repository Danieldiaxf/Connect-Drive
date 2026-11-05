package br.com.connectdrive.Controller;

import br.com.connectdrive.Services.AlunoService;
import br.com.connectdrive.dto.req.AlunoRequestDTO;
import br.com.connectdrive.dto.resp.AlunoResponseDTO;
import br.com.connectdrive.enums.CategoriaPretendido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {


    private final AlunoService alunoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void criarAluno( @RequestBody AlunoRequestDTO alunoDTO ) {
        alunoService.criarAluno( alunoDTO );
    }

    @GetMapping("/matricula/{matricula}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<AlunoResponseDTO> findByMatricula(@PathVariable String matricula ) {
        return alunoService.findByMatricula( matricula );
    }

    @GetMapping("/categoria/{categoria}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<AlunoResponseDTO> findByCategoriaPretendida( @PathVariable CategoriaPretendido categoria ) {
        return alunoService.findByCategoriaPretendida( categoria );
    }
}
    