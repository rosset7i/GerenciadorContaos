CREATE TABLE IF NOT EXISTS contato(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    primeiro_nome VARCHAR(255) NOT NULL,
    ultimo_nome VARCHAR(255) NOT NULL,
    telefone VARCHAR(11) NOT NULL,
    email VARCHAR(255) NOT NULL,
    data_de_criacao TIMESTAMP NOT NULL,
    data_de_modificacao TIMESTAMP);