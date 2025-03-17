package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.repository.PostagemRepository;
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
public class PostagemServiceTest {

    @Mock
    private PostagemRepository repository;

    @InjectMocks
    private PostagemService service;

    @Test
    @DisplayName("Deve retornar uma postagem quando ID existe")
    void testFindById_Success() {
        String id = "123";
        Postagem postagemMock = new Postagem();
        postagemMock.setId(id);
        postagemMock.setTitulo("Título Teste");
        postagemMock.setTexto("Texto da postagem");
        when(repository.findById(id)).thenReturn(Optional.of(postagemMock));

        Optional<Postagem> resultado = service.findById(id);

        assertTrue(resultado.isPresent());
        assertEquals("Título Teste", resultado.get().getTitulo());
        assertEquals("Texto da postagem", resultado.get().getTexto());

        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar vazio quando ID não existe")
    void testFindById_NotFound() {
        String id = "999";
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Postagem> resultado = service.findById(id);

        assertFalse(resultado.isPresent());
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve salvar uma postagem corretamente")
    void testSavePostagem() {
        Postagem postagemMock = new Postagem();
        postagemMock.setId("123");
        postagemMock.setTitulo("Nova Postagem");
        postagemMock.setTexto("Conteúdo da nova postagem");
        when(repository.save(postagemMock)).thenReturn(postagemMock);

        Postagem resultado = service.save(postagemMock);

        assertNotNull(resultado);
        assertEquals("Nova Postagem", resultado.getTitulo());
        assertEquals("Conteúdo da nova postagem", resultado.getTexto());

        verify(repository, times(1)).save(postagemMock);
    }

    @Test
    @DisplayName("Deve excluir uma postagem corretamente")
    void testDeleteById() {
        String id = "123";
        doNothing().when(repository).deleteById(id);

        service.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
