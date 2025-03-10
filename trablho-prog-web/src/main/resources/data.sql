-- Criar tabela Usuario
CREATE TABLE usuario (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Criar tabela Comentario
CREATE TABLE comentario (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    texto VARCHAR(500) NOT NULL,
    autor_id VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

-- Criar tabela Postagem
CREATE TABLE postagem (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    texto TEXT NOT NULL,
    autor_id VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL,
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
);

-- Criar tabela Grupo
CREATE TABLE grupo (
    id VARCHAR(255) NOT NULL PRIMARY KEY,
    main_admin_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (main_admin_id) REFERENCES usuario(id)
);

-- Criar tabela que relaciona Grupo e Usuario (membros do grupo)
CREATE TABLE grupo_usuarios (
    grupo_id VARCHAR(255) NOT NULL,
    usuario_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (grupo_id, usuario_id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Criar tabela que relaciona Grupo e Postagem (posts dentro do grupo)
CREATE TABLE grupo_posts (
    grupo_id VARCHAR(255) NOT NULL,
    postagem_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (grupo_id, postagem_id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    FOREIGN KEY (postagem_id) REFERENCES postagem(id)
);

-- Criar tabela que relaciona Grupo e Administradores
CREATE TABLE grupo_admins (
    grupo_id VARCHAR(255) NOT NULL,
    usuario_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (grupo_id, usuario_id),
    FOREIGN KEY (grupo_id) REFERENCES grupo(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Criar tabela que relaciona Comentario com Comentario (respostas)
CREATE TABLE comentario_respostas (
    comentario_id VARCHAR(255) NOT NULL,
    resposta_id VARCHAR(255) NOT NULL,
    PRIMARY KEY (comentario_id, resposta_id),
    FOREIGN KEY (comentario_id) REFERENCES comentario(id),
    FOREIGN KEY (resposta_id) REFERENCES comentario(id)
);
