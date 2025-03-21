-- Tabela Banco
CREATE TABLE banco (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela FormaPagamento
CREATE TABLE forma_pagamento (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela Status
CREATE TABLE status (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela Responsavel
CREATE TABLE responsavel (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela TipoTransacao
CREATE TABLE tipo_transacao (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela Carteira
CREATE TABLE carteira (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

-- Tabela Mes
CREATE TABLE mes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(20) NOT NULL,
    ano INT NOT NULL,
    carteira_id INT NOT NULL,
    FOREIGN KEY (carteira_id) REFERENCES carteira(id)
);

-- Tabela Usuario
CREATE TABLE usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    data_nascimento DATE,
    email VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL
);

-- Tabela Usuario_Carteira (relação muitos-para-muitos entre Usuario e Carteira)
CREATE TABLE usuario_carteira (
    usuario_id INT NOT NULL,
    carteira_id INT NOT NULL,
    PRIMARY KEY (usuario_id, carteira_id), -- Chave composta
    FOREIGN KEY (carteira_id) REFERENCES carteira(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Tabela Transacao
CREATE TABLE transacao (
    id SERIAL PRIMARY KEY,
    data DATE NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    valor FLOAT NOT NULL,
    quantas_vezes INT NOT NULL,
    receita BOOLEAN NOT NULL,
    banco_id INT NOT NULL,
    forma_pagamento_id INT NOT NULL,
    status_id INT NOT NULL,
    responsavel_id INT NOT NULL,
    mes_id INT NOT NULL,
    tipo_transacao_id INT NOT NULL,
    FOREIGN KEY (banco_id) REFERENCES banco(id),
    FOREIGN KEY (forma_pagamento_id) REFERENCES forma_pagamento(id),
    FOREIGN KEY (status_id) REFERENCES status(id),
    FOREIGN KEY (responsavel_id) REFERENCES responsavel(id),
    FOREIGN KEY (mes_id) REFERENCES mes(id),
    FOREIGN KEY (tipo_transacao_id) REFERENCES tipo_transacao(id)
);
