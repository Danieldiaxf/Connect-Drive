package br.com.connectdrive.dto.req;

import br.com.connectdrive.enums.CategoriaCNH;
import br.com.connectdrive.enums.Sexo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InstrutorRequestDTO(
        String nome, String cpf, LocalDate dataNasc, Sexo sexo,
        String email, String senha, String telefone, String cidade,
        String uf, String fotoUrl, String numCnh, CategoriaCNH categoriaCnh,
        String carro, String placaCarro, BigDecimal precoHora, String bio,
        int anosExperiencia

        ) {
}
