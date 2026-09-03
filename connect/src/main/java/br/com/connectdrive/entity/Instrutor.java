package br.com.connectdrive.model.entity;

import br.com.connectdrive.model.enums.CategoriaCNH;
import br.com.connectdrive.model.enums.StatusVerificacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "instrutores")
@PrimaryKeyJoinColumn(name = "usuario_id")
@Getter
@Setter
@NoArgsConstructor
public class Instrutor extends Usuario {

    @Column(name = "numero_cnh", nullable = false, unique = true, length = 20)
    private String numCnh;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_cnh", nullable = false, length = 2)
    private CategoriaCNH categoriaCnh;

    @Column(nullable = false, length = 100)
    private String carro;

    @Column(name = "placa_carro", nullable = false, length = 10)
    private String placaCarro;

    @Column(name = "preco_hora", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoHora;

    @Column(length = 1000)
    private String bio;

    @Column(name = "anos_experiencia", nullable = false)
    private int anosExperiencia;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_verificacao", nullable = false, length = 15)
    private StatusVerificacao statusVerificacao = StatusVerificacao.PENDENTE;

    @Column(name = "nota_media", nullable = false)
    private double notaMedia;
}
