package br.com.connectdrive.dto.resp;

import br.com.connectdrive.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        String nome, LocalDate dataNasc, Sexo sexo,
        String telefone, String cidade, String uf,
        String fotoUrl
) {
}
