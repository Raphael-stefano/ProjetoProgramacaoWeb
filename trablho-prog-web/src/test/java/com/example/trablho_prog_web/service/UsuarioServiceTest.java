package com.example.trablho_prog_web.service;

import com.example.trablho_prog_web.model.Usuario;
import com.example.trablho_prog_web.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService service;

    @Test
    @DisplayName("Verifica se foi retornado um usuário e se o usuário retornado é o correto")
    void testFindById() {

        String id = "123";
        Usuario usuarioMock = new Usuario(id, "Raphael", "raphael@email.com", "senha123");
        when(repository.findById(id)).thenReturn(Optional.of(usuarioMock));

        Optional<Usuario> resultado = service.findById(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.get().getId());
        assertEquals("Raphael", resultado.get().getNome());
        assertEquals("raphael@email.com", resultado.get().getEmail());
        assertEquals("senha123", resultado.get().getSenha());

        verify(repository).findById(id);
    }

}
