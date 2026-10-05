package br.com.connectdrive.Controller;

import br.com.connectdrive.Services.AlunoService;
import br.com.connectdrive.entity.Aluno;
import br.com.connectdrive.enums.CategoriaPretendido;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {


    private final AlunoService alunoService;

    @PostMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void criarAluno( @RequestBody Aluno aluno ) {
        alunoService.criarAluno( aluno );
    }

    @GetMapping("/matricula/{matricula}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Aluno findByMatricula( @PathVariable String matricula ) {
        return alunoService.findByMatricula( matricula );
    }

    @GetMapping("/categoria/{categoria}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Aluno> findByCategoriaPretendida( @PathVariable CategoriaPretendido categoria ) {
        return alunoService.findByCategoriaPretendida( categoria );
    }
}
    