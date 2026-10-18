package br.com.connectdrive.Services;

import br.com.connectdrive.entities.Aluno;
import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Transactional
    public void criarAluno( Aluno aluno ) {

        if ( aluno.getMatricula( ).trim( ).isBlank( )  ) {
            throw new RuntimeException( "Necessario criar usuario com matricula!!!" );
        }
        String matriculaGerada = UUID.randomUUID( ).toString( )
                        .replace("-", "")
                        .substring(0,11)
                        .toUpperCase();

        aluno.setMatricula( matriculaGerada );

        alunoRepository.save( aluno );
    }


    public Aluno findByMatricula( String matricula ) {

        return alunoRepository.findByMatricula( matricula )
                .orElseThrow( ( ) -> new RuntimeException( "Aluno com a matricula fornecida não foi encontrado!" ) );

    }

    public List<Aluno> findByCategoriaPretendida ( CategoriaPretendido categoria ) {
        return alunoRepository.findByCategoriaPretendida( categoria );
    }

}
