package br.com.connectdrive.dto.resp;

import br.com.connectdrive.enums.CategoriaCNH;
import br.com.connectdrive.enums.Sexo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InstrutorResponseDTO(
        String nome, LocalDate dataNasc, Sexo sexo,
        String telefone, String cidade, String uf,
        String fotoUrl, CategoriaCNH categoriaCNH, String carro,
        BigDecimal precoHora, String bio, int anosExperiencia,
        double notaMedia
) {
}
