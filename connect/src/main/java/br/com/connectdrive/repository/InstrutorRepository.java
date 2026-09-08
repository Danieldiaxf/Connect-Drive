package br.com.connectdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.connectdrive.model.entity.Instrutor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import br.com.connectdrive.model.enums.StatusVerificacao;
import br.com.connectdrive.model.enums.CategoriaCNH;
import org.springframework.stereotype.Repository;
import br.com.connectdrive.model.enums.Sexo;


@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    // Validação unica
    Optional<Instrutor> findByNumCnh(String numCnh);
    boolean existsByNumCnh(String numCnh);

    // Filtros de Busca
    List<Instrutor> findByNotaMedia(double notaMedia);
    List<Instrutor> findByPrecoHora(BigDecimal precoHora);
    List<Instrutor> findByCidadeContainingIgnoreCase(String cidade);
    List<Instrutor> findByCarroContainingIgnoreCase(String carro);
    List<Instrutor> findByNomeContainingIgnoreCase(String nome);
    List<Instrutor> findBySexo(Sexo sexo);
    List<Instrutor> findByStatusVerificacao(StatusVerificacao status);

    List<Instrutor> findByStatusVerificacaoAndCidadeContainingIgnoreCase(StatusVerificacao status, String cidade);
    List<Instrutor> findByStatusVerificacaoAndCategoriaCnh(StatusVerificacao status, CategoriaCNH categoria);


}
