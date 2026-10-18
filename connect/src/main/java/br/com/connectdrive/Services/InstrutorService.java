package br.com.connectdrive.Services;

import br.com.connectdrive.dto.req.InstrutorRequestDTO;
import br.com.connectdrive.dto.resp.InstrutorResponseDTO;
import br.com.connectdrive.entities.Instrutor;
import br.com.connectdrive.enums.Sexo;
import br.com.connectdrive.mapper.InstrutorMapper;
import br.com.connectdrive.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class InstrutorService {

    private final InstrutorRepository repository;
    private final InstrutorMapper mapper;

    public void salvarInstrutor( InstrutorRequestDTO instrutorDTO ) {

        log.info( "Requisicao de criar Instrutopr solicitada as {}", LocalTime.now( ) );

        try {

            Instrutor instrutor = mapper.toEntity( instrutorDTO );
            repository.save( instrutor );

        } catch ( Exception e ) {

        throw new IllegalArgumentException( "Erro ao tentar criar Usuario Instrutor" );
    }

    }


    public Optional<InstrutorResponseDTO> findByNumCnh( String cnh ) {

        return mapper.toOptionalDTO( repository.findByNumCnh( cnh ) );

    }


    public List<InstrutorResponseDTO> findBySexo( Sexo sexo ) {

        return mapper.toListInstrutor( repository.findBySexo( sexo ) );

    }

    public List<InstrutorResponseDTO> findByNotaMedia( Double nota ) {

        return mapper.toListInstrutor( repository.findByNotaMedia( nota ) );

    }


}
