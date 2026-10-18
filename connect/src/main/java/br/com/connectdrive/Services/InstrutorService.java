package br.com.connectdrive.Services;

import br.com.connectdrive.entities.Instrutor;
import br.com.connectdrive.enums.Sexo;
import br.com.connectdrive.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class InstrutorService {

    private final InstrutorRepository repository;

    public void salvarInstrutor( Instrutor instrutor ) {

        log.info( "Requisicao de criar Instrutopr solicitada as {}", LocalTime.now( ) );

        try {

            repository.save( instrutor );
        } catch (Exception e) {

        throw new IllegalArgumentException( "Erro ao tentar criar Usuario Instrutor" );
    }

    }


    public Instrutor findByNumCnh( String cnh ) {

        return repository.findByNumCnh( cnh )
                .orElseThrow( ( ) -> new IllegalArgumentException( "Usuario com o ID fornecido não encontrado!" ) );

    }


    public List<Instrutor> findBySexo( Sexo sexo ) {

        return repository.findBySexo( sexo );

    }

    public List<Instrutor> findByNotaMedia( Double nota ) {
        return repository.findByNotaMedia( nota );
    }


}
