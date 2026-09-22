package br.com.connectdrive.dto.resp;

import br.com.connectdrive.enums.Sexo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UsuarioResponseDTO(
        String nome, LocalDate dataMas, Sexo sexo,
        String email, String telefone, String cidade, String uf,
        String fotoUrl, LocalDateTime dataCadastro ) {
}
