package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Comentario;
import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.repository.ComentarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
    private ComentarioRepository repository;

    @InjectMocks
    private ComentarioService service;

    @Test
    @DisplayName("Verifica se foi retornado um comentário e se o comentário retornado é o correto")
    void testFindById() {
        String id = "456";
        Usuario autor = new Usuario("123", "Raphael", "raphael@email.com", "senha123");
        Comentario comentarioMock = new Comentario();
        comentarioMock.setId(id);
        comentarioMock.setTexto("Este é um comentário de teste.");
        comentarioMock.setAutor(autor);
        comentarioMock.setAtivo(true);

        when(repository.findById(id)).thenReturn(Optional.of(comentarioMock));

        Optional<Comentario> resultado = service.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.get().getId());
        assertEquals("Este é um comentário de teste.", resultado.get().getTexto());
        assertEquals(autor, resultado.get().getAutor());
        assertTrue(resultado.get().isAtivo());

        verify(repository).findById(id);
    }
}

