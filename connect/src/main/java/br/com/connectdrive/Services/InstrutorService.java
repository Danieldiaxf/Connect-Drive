package br.com.connectdrive.Services;

import br.com.connectdrive.entities.Instrutor;
import br.com.connectdrive.repository.InstrutorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class InstrutorService {

    private final InstrutorRepository repository;


    public Instrutor findByNumCnh( String cnh ) {

        log.info( "Requisicao findByNumCnh solicitada as {} para a cnh {}", LocalTime.now( ), cnh );

        return repository.findByNumCnh( cnh )
                .orElseThrow( ( ) -> new IllegalArgumentException( "Usuario com o ID fornecido não encontrado!" ) );

    }


}
