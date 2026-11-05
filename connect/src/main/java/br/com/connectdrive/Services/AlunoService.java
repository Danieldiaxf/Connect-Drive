package br.com.connectdrive.Services;

import br.com.connectdrive.dto.req.AlunoRequestDTO;
import br.com.connectdrive.dto.resp.AlunoResponseDTO;
import br.com.connectdrive.entities.Aluno;
import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.mapper.AlunoMapper;
import br.com.connectdrive.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper mapper;

    @Transactional
    public void criarAluno( AlunoRequestDTO alunoDTO ) {

        log.info( "Requisicao criarAluno solicitada as {}", LocalTime.now( ) );

        Aluno alunoEntity = mapper.toEntity( alunoDTO );

        String matriculaGerada = UUID.randomUUID().toString()
                .replace("-", "")
                .substring( 0, 11 )
                .toUpperCase( );

        alunoEntity.setMatricula( matriculaGerada );


        alunoRepository.save( alunoEntity );

        log.info( "Requisicao criarAluno feita! as {}. {}", LocalTime.now( ), alunoEntity );
    }


    public Optional<AlunoResponseDTO> findByMatricula( String matricula ) {

        return mapper.toOptionalDTO( alunoRepository.findByMatricula( matricula ) );

    }

    public List<AlunoResponseDTO> findByCategoriaPretendida( CategoriaPretendido categoria ) {

        return mapper.toListAluno( alunoRepository.findByCategoriaPretendida( categoria ) );
    }

}
