package br.com.connectdrive.Services;

import br.com.connectdrive.dto.req.InstrutorRequestDTO;
import br.com.connectdrive.entities.Instrutor;
import br.com.connectdrive.enums.CategoriaCNH;
import br.com.connectdrive.enums.Sexo;
import br.com.connectdrive.mapper.InstrutorMapper;
import br.com.connectdrive.repository.InstrutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InstrutorServiceTest {

    @Mock
    private InstrutorRepository repository;

    @Mock
    private InstrutorMapper mapper;

    @InjectMocks
    private InstrutorService service;

    @Test
    void deveSalvarInstrutorQuandoDadosForemValidos() {
        InstrutorRequestDTO dto = criarRequestValido();
        Instrutor instrutor = new Instrutor();

        when(mapper.toEntity(dto)).thenReturn(instrutor);

        service.salvarInstrutor(dto);

        verify(mapper).toEntity(dto);
        verify(repository).save(instrutor);
    }

    @Test
    void deveLancarExcecaoSemSalvarQuandoMapeamentoFalhar() {
        InstrutorRequestDTO dto = criarRequestValido();

        when(mapper.toEntity(dto))
                .thenThrow(new IllegalStateException("Falha no mapeamento"));

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> service.salvarInstrutor(dto)
        );

        assertEquals(
                "Erro ao tentar criar Usuario Instrutor",
                excecao.getMessage()
        );

        verify(repository, never()).save(any(Instrutor.class));
    }

    @Test
    void deveLancarExcecaoQuandoPersistenciaFalhar() {
        InstrutorRequestDTO dto = criarRequestValido();
        Instrutor instrutor = new Instrutor();

        when(mapper.toEntity(dto)).thenReturn(instrutor);
        when(repository.save(instrutor))
                .thenThrow(new IllegalStateException("Falha na persistência"));

        IllegalArgumentException excecao = assertThrows(
                IllegalArgumentException.class,
                () -> service.salvarInstrutor(dto)
        );

        assertEquals(
                "Erro ao tentar criar Usuario Instrutor",
                excecao.getMessage()
        );

        verify(repository).save(instrutor);
    }

    private InstrutorRequestDTO criarRequestValido() {
        return new InstrutorRequestDTO(
                "Instrutor de Teste",
                "52998224725",
                LocalDate.of(1990, 5, 20),
                Sexo.MASCULINO,
                "instrutor@example.com",
                "SenhaTeste123!",
                "61999990000",
                "Brasília",
                "DF",
                null,
                "12345678901",
                CategoriaCNH.B,
                "Fiat Argo",
                "ABC1D23",
                new BigDecimal("100.00"),
                "Instrutor de direção",
                5
        );
    }
}