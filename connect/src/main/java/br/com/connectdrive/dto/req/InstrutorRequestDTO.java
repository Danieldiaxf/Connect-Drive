package br.com.connectdrive.dto.req;

import br.com.connectdrive.enums.Sexo;

public record InstrutorRequestDTO(
        String cnh, Sexo sexo, double notaMedia
) {
}
