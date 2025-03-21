-- V1.1__insert_data.sql

-- Inserção de dados na tabela banco
INSERT INTO banco (nome) VALUES
('Itau'),
('Picpay'),
('Nubank');

-- Inserção de dados na tabela forma_pagamento
INSERT INTO forma_pagamento (nome) VALUES
('Dinheiro'),
('Cartão'),
('Pix');

-- Inserção de dados na tabela status
INSERT INTO status (nome) VALUES
('Pago'),
('Não Pago');

-- Inserção de dados na tabela responsavel
INSERT INTO responsavel (nome) VALUES
('Gabrielle'),
('Terezinha'),
('Jocimar'),
('Caio');

-- Inserção de dados na tabela tipo_transacao
INSERT INTO tipo_transacao (nome) VALUES
('Débito'),
('Crédito'),
('Investimento');

-- Inserção de dados na tabela usuario
INSERT INTO usuario (nome, data_nascimento, email, senha) VALUES
('Wilson', '1997-02-06', 'wilson@example.com', 'teste123'),
('Gabrielle', '1995-06-15', 'gabrielle@example.com', 'teste123'),
('Lucas', '1998-09-22', 'lucas@example.com', 'teste123');
