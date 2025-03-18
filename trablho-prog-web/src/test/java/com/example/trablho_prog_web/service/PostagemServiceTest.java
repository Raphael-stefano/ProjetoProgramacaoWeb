package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Postagem;
import com.example.trablho_prog_web.model.Usuario;
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

    @Test
    @DisplayName("Deve retornar postagens ativas")
    void testFindByAtivoTrue() {
        Postagem postagemMock = new Postagem();
        postagemMock.setAtivo(true);
        when(repository.findByAtivoTrue()).thenReturn(List.of(postagemMock));

        List<Postagem> resultado = service.findByAtivoTrue();

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).isAtivo());
        verify(repository, times(1)).findByAtivoTrue();
    }

    @Test
    @DisplayName("Deve retornar postagens inativas")
    void testFindByAtivoFalse() {
        Postagem postagemMock = new Postagem();
        postagemMock.setAtivo(false);
        when(repository.findByAtivoFalse()).thenReturn(List.of(postagemMock));

        List<Postagem> resultado = service.findByAtivoFalse();

        assertFalse(resultado.isEmpty());
        assertFalse(resultado.get(0).isAtivo());
        verify(repository, times(1)).findByAtivoFalse();
    }

    @Test
    @DisplayName("Deve retornar postagens por autor")
    void testFindByAutorId() {
        // Cria um usuário com o ID especificado
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId("123");

        // Cria uma postagem e associa o usuário a ela
        Postagem postagemMock = new Postagem();
        postagemMock.setId("1");
        postagemMock.setTitulo("Título da Postagem");
        postagemMock.setTexto("Texto da Postagem");
        postagemMock.setAutor(usuarioMock); // Associa o usuário à postagem
        postagemMock.setAtivo(true);

        // Mocka a consulta no repositório
        when(repository.findByAutorId("123")).thenReturn(List.of(postagemMock));

        // Chama o método do serviço
        List<Postagem> resultado = service.findByAutorId("123");

        // Verifica o resultado
        assertFalse(resultado.isEmpty());
        assertEquals("123", resultado.get(0).getAutor().getId()); // Verifica o ID do autor
        verify(repository, times(1)).findByAutorId("123");
    }

    @Test
    @DisplayName("Deve retornar postagens por título")
    void testFindByTitulo() {
        String titulo = "Título Teste";
        Postagem postagemMock = new Postagem();
        postagemMock.setTitulo(titulo);
        when(repository.findByTitulo(titulo)).thenReturn(List.of(postagemMock));

        List<Postagem> resultado = service.findByTitulo(titulo);

        assertFalse(resultado.isEmpty());
        assertEquals(titulo, resultado.get(0).getTitulo());
        verify(repository, times(1)).findByTitulo(titulo);
    }
}