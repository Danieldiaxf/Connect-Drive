package br.com.connectdrive.Controller;

import br.com.connectdrive.Services.AlunoService;
import br.com.connectdrive.Services.InstrutorService;
import br.com.connectdrive.dto.req.AlunoRequestDTO;
import br.com.connectdrive.dto.req.InstrutorRequestDTO;
import br.com.connectdrive.enums.CategoriaCNH;
import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.enums.Sexo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CadastroUsuarioControllerTest {

    @Mock
    private AlunoService alunoService;

    @Mock
    private InstrutorService instrutorService;

    private MockMvc mockMvc;

    @BeforeEach
    void configurar() {
        mockMvc = MockMvcBuilders.standaloneSetup(
                new AlunoController(alunoService),
                new InstrutorController(instrutorService)
        ).build();
    }

    @Test
    void deveEncaminharDadosDoAlunoERetornar204() throws Exception {
        String json = """
                {
                    "nome": "Aluno de Teste",
                    "cpf": "52998224725",
                    "dataNasc": "2000-01-15",
                    "sexo": "MASCULINO",
                    "email": "aluno@example.com",
                    "senha": "SenhaTeste123!",
                    "telefone": "61999990000",
                    "cidade": "Brasilia",
                    "uf": "DF",
                    "fotoUrl": null,
                    "categoriaPretendida": "B"
                }
                """;

        mockMvc.perform(post("/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNoContent());

        AlunoRequestDTO esperado = new AlunoRequestDTO(
                "Aluno de Teste",
                "52998224725",
                LocalDate.of(2000, 1, 15),
                Sexo.MASCULINO,
                "aluno@example.com",
                "SenhaTeste123!",
                "61999990000",
                "Brasilia",
                "DF",
                null,
                CategoriaPretendido.B
        );

        verify(alunoService).criarAluno(esperado);
        verifyNoInteractions(instrutorService);
    }

    @Test
    void deveRejeitarCategoriaInvalidaNoCadastroDeAluno() throws Exception {
        String json = """
                {
                    "categoriaPretendida": "INVALIDA"
                }
                """;

        mockMvc.perform(post("/aluno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(alunoService, instrutorService);
    }

    @Test
    void deveEncaminharDadosDoInstrutorERetornar200() throws Exception {
        String json = """
                {
                    "nome": "Instrutor de Teste",
                    "cpf": "52998224725",
                    "dataNasc": "1990-05-20",
                    "sexo": "MASCULINO",
                    "email": "instrutor@example.com",
                    "senha": "SenhaTeste123!",
                    "telefone": "61999990000",
                    "cidade": "Brasilia",
                    "uf": "DF",
                    "fotoUrl": null,
                    "numCnh": "12345678901",
                    "categoriaCnh": "B",
                    "carro": "Fiat Argo",
                    "placaCarro": "ABC1D23",
                    "precoHora": 100.00,
                    "bio": "Instrutor de direcao",
                    "anosExperiencia": 5
                }
                """;

        mockMvc.perform(post("/instrutor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        InstrutorRequestDTO esperado = new InstrutorRequestDTO(
                "Instrutor de Teste",
                "52998224725",
                LocalDate.of(1990, 5, 20),
                Sexo.MASCULINO,
                "instrutor@example.com",
                "SenhaTeste123!",
                "61999990000",
                "Brasilia",
                "DF",
                null,
                "12345678901",
                CategoriaCNH.B,
                "Fiat Argo",
                "ABC1D23",
                new BigDecimal("100.00"),
                "Instrutor de direcao",
                5
        );

        verify(instrutorService).salvarInstrutor(esperado);
        verifyNoInteractions(alunoService);
    }

    @Test
    void deveRejeitarCategoriaInvalidaNoCadastroDeInstrutor() throws Exception {
        String json = """
                {
                    "categoriaCnh": "INVALIDA"
                }
                """;

        mockMvc.perform(post("/instrutor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());

        verifyNoInteractions(alunoService, instrutorService);
    }
}