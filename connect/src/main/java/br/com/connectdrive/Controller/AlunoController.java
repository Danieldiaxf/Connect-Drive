package br.com.connectdrive.Controller;

import br.com.connectdrive.entities.Aluno;
import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {


    private final AlunoRepository alunoRepository;

    @PostMapping()
    public void criarAluno( @RequestBody Aluno aluno ) {
        alunoRepository.save( aluno );
    }

    @GetMapping("/matricula/{matricula}")
    public Aluno findByMatricula( @PathVariable String matricula ) {
        return alunoRepository.findByMatricula( matricula )
                .orElseThrow( ( ) -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Aluno não encontrado para matrícula inserida!" ) );
    }

    @GetMapping("/categoria/{categoria}")
    public List<Aluno> findByCategoriaPretendida( @PathVariable CategoriaPretendido categoria ) {
        return alunoRepository.findByCategoriaPretendida( categoria );
    }
}
    