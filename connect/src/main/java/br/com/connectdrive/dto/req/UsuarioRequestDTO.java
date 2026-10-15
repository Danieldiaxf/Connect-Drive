package br.com.connectdrive.dto.req;


import java.time.LocalDate;

public record UsuarioRequestDTO(
        Long id, String nome, String cpf,
        LocalDate dataNasc, String email
) {
}
