-- Criar tabela Usuario
CREATE TABLE usuario (
    id long NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Criar tabela Grupo
CREATE TABLE grupo (
    id long NOT NULL PRIMARY KEY,
    main_admin_id long NOT NULL,
    FOREIGN KEY (main_admin_id) REFERENCES usuario(id)
);

-- Criar tabela Postagem
CREATE TABLE postagem (
    id long NOT NULL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    texto TEXT NOT NULL,
    autor_id long NOT NULL,
    grupo_id long NOT NULL,
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id)
);

CREATE TABLE comentario (
    id LONG NOT NULL PRIMARY KEY,
    texto VARCHAR(500) NOT NULL,
    autor_id LONG NOT NULL,
    postagem_id LONG NOT NULL,
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id),
    FOREIGN KEY (postagem_id) REFERENCES postagem(id)
);

-- Criar tabela que relaciona Grupo e Usuario (membros do grupo)
CREATE TABLE grupo_usuarios (
    grupo_id long NOT NULL,
    usuario_id long NOT NULL,
    PRIMARY KEY (grupo_id, usuario_id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Criar tabela que relaciona Grupo e Administradores
CREATE TABLE grupo_admins (
    grupo_id long NOT NULL,
    usuario_id long NOT NULL,
    PRIMARY KEY (grupo_id, usuario_id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Criar tabela que relaciona Comentario com Comentario (respostas)
CREATE TABLE comentario_respostas (
    comentario_id long NOT NULL,
    resposta_id long NOT NULL,
    PRIMARY KEY (comentario_id, resposta_id),
    FOREIGN KEY (comentario_id) REFERENCES comentario(id),
    FOREIGN KEY (resposta_id) REFERENCES comentario(id)
);

-- Inserir 5 usuários
INSERT INTO usuario (id, nome, email, senha) VALUES
    (1, 'João Silva', 'joao@email.com', 'senha123'),
    (2, 'Maria Souza', 'maria@email.com', 'senha456'),
    (3, 'Carlos Oliveira', 'carlos@email.com', 'senha789'),
    (4, 'Ana Pereira', 'ana@email.com', 'senha101'),
    (5, 'Pedro Costa', 'pedro@email.com', 'senha112');

-- Inserir 5 grupos
INSERT INTO grupo (id, main_admin_id) VALUES
    (1, 1),  -- Grupo 1 administrado por João
    (2, 2),  -- Grupo 2 administrado por Maria
    (3, 3),  -- Grupo 3 administrado por Carlos
    (4, 4),  -- Grupo 4 administrado por Ana
    (5, 5);  -- Grupo 5 administrado por Pedro

-- Inserir membros dos grupos (tabela grupo_usuarios)
INSERT INTO grupo_usuarios (grupo_id, usuario_id) VALUES
    -- Grupo 1 tem todos os usuários como membros
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5),
    -- Grupo 2 tem alguns membros
    (2, 1), (2, 3), (2, 5),
    -- Grupo 3 tem outros membros
    (3, 2), (3, 4),
    -- Grupo 4 e 5 têm apenas seus administradores principais
    (4, 4),
    (5, 5);

-- Inserir administradores adicionais (tabela grupo_admins)
INSERT INTO grupo_admins (grupo_id, usuario_id) VALUES
    -- Grupo 1 tem 2 administradores (João e Maria)
    (1, 1), (1, 2),
    -- Grupo 2 tem 3 administradores (Maria, Carlos e Pedro)
    (2, 2), (2, 3), (2, 5),
    -- Grupo 3 tem 1 administrador (Carlos)
    (3, 3),
    -- Grupo 4 e 5 mantêm apenas seus administradores principais
    (4, 4),
    (5, 5);

-- Inserir 5 postagens
INSERT INTO postagem (id, titulo, texto, autor_id, grupo_id, ativo) VALUES
    (1, 'Bem-vindos ao Grupo 1', 'Este é o primeiro post no grupo 1!', 1, 1, true),
    (2, 'Dicas de Programação', 'Compartilhando algumas dicas úteis...', 2, 2, true),
    (3, 'Evento no Próximo Fim de Semana', 'Teremos um encontro no sábado...', 3, 3, true),
    (4, 'Atualização de Regras', 'Por favor, leiam as novas regras...', 4, 4, true),
    (5, 'Fechamento Temporário', 'O grupo ficará fechado por manutenção...', 5, 5, false);

-- Inserir 5 comentários
INSERT INTO comentario (id, texto, autor_id, postagem_id, ativo) VALUES
    (1, 'Ótimo post, João!', 2, 1, true),
    (2, 'Obrigado pelas dicas, Maria!', 3, 2, true),
    (3, 'Não poderei comparecer ao evento', 5, 3, true),
    (4, 'Entendido, Ana!', 1, 4, true),
    (5, 'Quando reabrirá o grupo?', 2, 5, false);

-- Inserir respostas a comentários (tabela comentario_respostas)
INSERT INTO comentario_respostas (comentario_id, resposta_id) VALUES
    -- Resposta ao comentário 1
    (1, 2),
    -- Resposta ao comentário 3
    (3, 4),
    -- Resposta ao comentário 5
    (5, 1);