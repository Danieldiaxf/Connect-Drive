package br.com.connectdrive.dto.resp;

import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.enums.Sexo;

import java.time.LocalDate;

public record AlunoResponseDTO(
        String nome, LocalDate dataNasc, Sexo sexo,
        String telefone, String cidade, String uf,
        String fotoUrl, String matricula, CategoriaPretendido categoriaPretendido
) {
}
