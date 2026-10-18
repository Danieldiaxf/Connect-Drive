package br.com.connectdrive.dto.req;

import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.enums.Sexo;
import java.time.LocalDate;
import java.util.UUID;

public record AlunoRequestDTO(
        String nome, String cpf, LocalDate dataNasc, Sexo sexo,
        String email, String senha, String telefone, String cidade,
        String uf, String fotoUrl, CategoriaPretendido categoriaPretendida) {

}
