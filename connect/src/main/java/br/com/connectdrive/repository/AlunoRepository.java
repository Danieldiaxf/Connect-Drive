package br.com.connectdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.connectdrive.entity.Aluno;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import br.com.connectdrive.model.enums.CategoriaPretendido;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Optional<Aluno> findByMatricula (String matricula);
    boolean existsByMatricula(String matricula);

    List<Aluno> findByCategoriaPretendida(CategoriaPretendido categoria);

    List<Aluno> findByCidadeContainingIgnoreCase(String cidade);
    List<Aluno> findByNomeContainingIgnoreCase(String nome);
}
