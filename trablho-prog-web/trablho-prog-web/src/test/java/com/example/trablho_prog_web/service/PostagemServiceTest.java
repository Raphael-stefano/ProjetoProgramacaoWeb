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
import java.util.UUID;

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
    void testBuscarPorId_Sucesso() {
        String id = UUID.randomUUID().toString();
        Postagem postagemMock = new Postagem();
        postagemMock.setId(id);
        postagemMock.setTitulo("Título Teste");
        postagemMock.setTexto("Texto da postagem");
        when(repository.findById(id)).thenReturn(Optional.of(postagemMock));

        Optional<Postagem> resultado = service.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("Título Teste", resultado.get().getTitulo());
        assertEquals("Texto da postagem", resultado.get().getTexto());
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve retornar vazio quando ID não existe")
    void testBuscarPorId_NaoEncontrado() {
        String id = UUID.randomUUID().toString();
        when(repository.findById(id)).thenReturn(Optional.empty());

        Optional<Postagem> resultado = service.buscarPorId(id);

        assertFalse(resultado.isPresent());
        verify(repository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Deve salvar uma postagem corretamente")
    void testSalvarPostagem() {
        Postagem postagemMock = new Postagem();
        postagemMock.setId(UUID.randomUUID().toString());
        postagemMock.setTitulo("Nova Postagem");
        postagemMock.setTexto("Conteúdo da nova postagem");
        when(repository.save(postagemMock)).thenReturn(postagemMock);

        Postagem resultado = service.salvar(postagemMock);

        assertNotNull(resultado);
        assertEquals("Nova Postagem", resultado.getTitulo());
        assertEquals("Conteúdo da nova postagem", resultado.getTexto());
        verify(repository, times(1)).save(postagemMock);
    }

    @Test
    @DisplayName("Deve excluir uma postagem corretamente")
    void testExcluirPostagem() {
        String id = UUID.randomUUID().toString();
        doNothing().when(repository).deleteById(id);

        service.excluir(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("Deve retornar postagens ativas")
    void testBuscarAtivas() {
        Postagem postagemMock = new Postagem();
        postagemMock.setAtivo(true);
        when(repository.findByAtivoTrue()).thenReturn(List.of(postagemMock));

        List<Postagem> resultado = service.buscarAtivas();

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.get(0).isAtivo());
        verify(repository, times(1)).findByAtivoTrue();
    }

    @Test
    @DisplayName("Deve retornar postagens inativas")
    void testBuscarInativas() {
        Postagem postagemMock = new Postagem();
        postagemMock.setAtivo(false);
        when(repository.findByAtivoFalse()).thenReturn(List.of(postagemMock));

        List<Postagem> resultado = service.buscarInativas();

        assertFalse(resultado.isEmpty());
        assertFalse(resultado.get(0).isAtivo());
        verify(repository, times(1)).findByAtivoFalse();
    }

    @Test
    @DisplayName("Deve retornar postagens por autor")
    void testBuscarPorAutor() {
        Usuario usuarioMock = new Usuario();
        usuarioMock.setId(UUID.randomUUID().toString());

        Postagem postagemMock = new Postagem();
        postagemMock.setId(UUID.randomUUID().toString());
        postagemMock.setTitulo("Título da Postagem");
        postagemMock.setTexto("Texto da Postagem");
        postagemMock.setAutor(usuarioMock);
        postagemMock.setAtivo(true);

        when(repository.findByAutorId(usuarioMock.getId())).thenReturn(List.of(postagemMock));

        List<Postagem> resultado = service.buscarPorAutor(usuarioMock.getId());

        assertFalse(resultado.isEmpty());
        assertEquals("Título da Postagem", resultado.get(0).getTitulo());
        assertEquals("Texto da Postagem", resultado.get(0).getTexto());
        verify(repository, times(1)).findByAutorId(usuarioMock.getId());
    }
}
