package br.com.connectdrive.dto.req;

import br.com.connectdrive.enums.CategoriaPretendido;

public record AlunoRequestDTO(
        String matricula, CategoriaPretendido categoriaPretendido
) {
}
