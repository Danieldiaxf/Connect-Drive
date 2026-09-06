package br.com.connectdrive.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.connectdrive.model.entity.Usuario;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf (String cpf);
    Optional<Usuario> findByEmail (String email);

    // Validação Cpf e Email
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
}
