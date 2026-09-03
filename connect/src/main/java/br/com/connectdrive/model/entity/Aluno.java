package br.com.connectdrive.model.entity;

import br.com.connectdrive.model.enums.CategoriaPretendido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "alunos")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
public class Aluno extends Usuario {

    @Column(nullable = false, unique = true, length = 30)
    private String matricula;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_pretendida", nullable = false, length = 2)
    private CategoriaPretendido categoriaPretendida;
}
