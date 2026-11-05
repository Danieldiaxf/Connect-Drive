package br.com.connectdrive.Services;

import br.com.connectdrive.dto.req.AlunoRequestDTO;
import br.com.connectdrive.entities.Aluno;
import br.com.connectdrive.enums.CategoriaPretendido;
import br.com.connectdrive.enums.Sexo;
import br.com.connectdrive.mapper.AlunoMapper;
import br.com.connectdrive.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @Mock
    private AlunoMapper mapper;

    @InjectMocks
    private AlunoService alunoService;

    @Test
    void deveGerarMatriculaESalvarAlunoQuandoDadosForemValidos() {
        AlunoRequestDTO dto = criarRequestValido();
        Aluno aluno = new Aluno();

        when(mapper.toEntity(dto)).thenReturn(aluno);

        alunoService.criarAluno(dto);

        assertNotNull(aluno.getMatricula());
        assertTrue(
                aluno.getMatricula().matches("[0-9A-F]{11}"),
                "A matrícula deve conter 11 caracteres hexadecimais maiúsculos"
        );

        verify(mapper).toEntity(dto);
        verify(alunoRepository).save(aluno);
    }

    @Test
    void naoDeveSalvarAlunoQuandoMapeamentoFalhar() {
        AlunoRequestDTO dto = criarRequestValido();
        IllegalArgumentException erro =
                new IllegalArgumentException("Falha no mapeamento");

        when(mapper.toEntity(dto)).thenThrow(erro);

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> alunoService.criarAluno(dto)
        );

        assertSame(erro, excecao);
        verify(alunoRepository, never()).save(any(Aluno.class));
    }

    @Test
    void devePropagarErroQuandoPersistenciaFalhar() {
        AlunoRequestDTO dto = criarRequestValido();
        Aluno aluno = new Aluno();
        IllegalStateException erro =
                new IllegalStateException("Falha na persistência");

        when(mapper.toEntity(dto)).thenReturn(aluno);
        when(alunoRepository.save(aluno)).thenThrow(erro);

        IllegalStateException excecao = assertThrows(
                IllegalStateException.class,
                () -> alunoService.criarAluno(dto)
        );

        assertSame(erro, excecao);
        verify(alunoRepository).save(aluno);
    }

    private AlunoRequestDTO criarRequestValido() {
        return new AlunoRequestDTO(
                "Aluno de Teste",
                "52998224725",
                LocalDate.of(2000, 1, 15),
                Sexo.MASCULINO,
                "aluno@example.com",
                "SenhaTeste123!",
                "61999990000",
                "Brasília",
                "DF",
                null,
                CategoriaPretendido.B
        );
    }
}