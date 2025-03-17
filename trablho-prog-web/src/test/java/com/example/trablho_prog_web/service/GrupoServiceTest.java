package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Grupo;
import com.example.trablho_prog_web.repository.GrupoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GrupoServiceTest {

    @Mock
    private GrupoRepository repository;

    @InjectMocks
    private GrupoService service;

    private Grupo grupoMock;

    @BeforeEach
    void setUp() {
        grupoMock = new Grupo();
        grupoMock.setId("1");
    }

    @Test
    @DisplayName("Deve salvar um grupo")
    void testSalvarGrupo() {
        when(repository.save(grupoMock)).thenReturn(grupoMock);

        Grupo resultado = service.salvar(grupoMock);

        assertNotNull(resultado);
        assertEquals("1", resultado.getId());
        verify(repository, times(1)).save(grupoMock);
    }

    @Test
    @DisplayName("Deve buscar um grupo por ID")
    void testBuscarPorId() {
        when(repository.findById("1")).thenReturn(Optional.of(grupoMock));

        Optional<Grupo> resultado = service.buscarPorId("1");

        assertTrue(resultado.isPresent());
        assertEquals("1", resultado.get().getId());
        verify(repository, times(1)).findById("1");
    }

    @Test
    @DisplayName("Deve retornar lista de grupos")
    void testListarTodos() {
        when(repository.findAll()).thenReturn(List.of(grupoMock));

        List<Grupo> resultado = service.listarTodos();

        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("Deve excluir um grupo pelo ID")
    void testExcluirGrupo() {
        doNothing().when(repository).deleteById("1");

        service.excluir("1");

        verify(repository, times(1)).deleteById("1");
    }
}

