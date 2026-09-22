package br.com.connectdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.connectdrive.entities.Instrutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.connectdrive.enums.StatusVerificacao;
import br.com.connectdrive.enums.CategoriaCNH;
import org.springframework.stereotype.Repository;
import br.com.connectdrive.enums.Sexo;


@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, UUID> {

    Optional<Instrutor> findByNumCnh(String numCnh);
    boolean existsByNumCnh(String numCnh);


    List<Instrutor> findByNotaMedia(double notaMedia);
    List<Instrutor> findByPrecoHoraBetween(BigDecimal precoIni, BigDecimal precoFim);
    List<Instrutor> findByCidadeContainingIgnoreCase(String cidade);
    List<Instrutor> findByCarroContainingIgnoreCase(String carro);
    List<Instrutor> findByNomeContainingIgnoreCase(String nome);
    List<Instrutor> findBySexo(Sexo sexo);
    List<Instrutor> findByStatusVerificacao(StatusVerificacao status);

    List<Instrutor> findByStatusVerificacaoAndCidadeContainingIgnoreCase(StatusVerificacao status, String cidade);
    List<Instrutor> findByStatusVerificacaoAndCategoriaCnh(StatusVerificacao status, CategoriaCNH categoria);


}
