-- Create sequences and tables

-- Banco
CREATE SEQUENCE IF NOT EXISTS banco_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS banco (
    id BIGINT PRIMARY KEY DEFAULT nextval('banco_id_seq'::regclass),
    nome VARCHAR(255)
);

-- Usuario
CREATE SEQUENCE IF NOT EXISTS usuario_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT PRIMARY KEY DEFAULT nextval('usuario_id_seq'::regclass),
    nome VARCHAR(255) NOT NULL,
    data_nascimento DATE,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

-- Carteira
CREATE SEQUENCE IF NOT EXISTS carteira_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS carteira (
    id BIGINT PRIMARY KEY DEFAULT nextval('carteira_id_seq'::regclass),
    nome VARCHAR(255) NOT NULL
);

-- usuario_carteira join table (no sequence needed as PK is composite)
CREATE TABLE IF NOT EXISTS usuario_carteira (
    usuario_id BIGINT,
    carteira_id BIGINT,
    PRIMARY KEY (usuario_id, carteira_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),
    FOREIGN KEY (carteira_id) REFERENCES carteira(id)
);

-- Responsavel
CREATE SEQUENCE IF NOT EXISTS responsavel_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS responsavel (
    id BIGINT PRIMARY KEY DEFAULT nextval('responsavel_id_seq'::regclass),
    nome VARCHAR(255),
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- Mes
CREATE SEQUENCE IF NOT EXISTS mes_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS mes (
    id BIGINT PRIMARY KEY DEFAULT nextval('mes_id_seq'::regclass),
    nome VARCHAR(255),
    ano INT,
    porcentagem_investimento INT,
    carteira_id BIGINT NOT NULL,
    FOREIGN KEY (carteira_id) REFERENCES carteira(id)
);

-- Transacao
CREATE SEQUENCE IF NOT EXISTS transacao_id_seq START WITH 1 INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS transacao (
    id BIGINT PRIMARY KEY DEFAULT nextval('transacao_id_seq'::regclass),
    data DATE,
    descricao VARCHAR(255),
    valor DECIMAL(19, 2),
    quantas_vezes INT,
    is_receita BOOLEAN,
    banco_id BIGINT NOT NULL,
    forma_pagamento VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    responsavel_id BIGINT NOT NULL,
    mes_id BIGINT NOT NULL,
    tipo_transacao VARCHAR(255) NOT NULL,
    FOREIGN KEY (banco_id) REFERENCES banco(id),
    FOREIGN KEY (responsavel_id) REFERENCES responsavel(id),
    FOREIGN KEY (mes_id) REFERENCES mes(id)
);

-- Insert initial data

-- Banks
INSERT INTO banco (id, nome) VALUES (1, 'Banco do Brasil');
INSERT INTO banco (id, nome) VALUES (2, 'Itaú Unibanco');
INSERT INTO banco (id, nome) VALUES (3, 'Bradesco');
INSERT INTO banco (id, nome) VALUES (4, 'Santander');
INSERT INTO banco (id, nome) VALUES (5, 'Caixa Econômica Federal');

-- Admin User (ID: 1)
INSERT INTO usuario (id, nome, data_nascimento, email, senha) VALUES (1, 'Admin User', '1980-01-01', 'admin@example.com', 'admin_password');
INSERT INTO carteira (id, nome) VALUES (1, 'Admin Wallet');
INSERT INTO usuario_carteira (usuario_id, carteira_id) VALUES (1, 1);
INSERT INTO responsavel (id, nome, usuario_id) VALUES (1, 'Admin User', 1);
INSERT INTO mes (id, nome, ano, porcentagem_investimento, carteira_id) VALUES (1, 'DEZEMBRO', 2023, 10, 1);

-- Additional Users

-- User 2 (ID: 2)
INSERT INTO usuario (id, nome, data_nascimento, email, senha) VALUES (2, 'Alice Wonderland', '1990-05-15', 'alice@example.com', 'alicepass');
INSERT INTO carteira (id, nome) VALUES (2, 'Alice Personal');
INSERT INTO usuario_carteira (usuario_id, carteira_id) VALUES (2, 2);
INSERT INTO responsavel (id, nome, usuario_id) VALUES (2, 'Alice Wonderland', 2);
INSERT INTO mes (id, nome, ano, porcentagem_investimento, carteira_id) VALUES (2, 'DEZEMBRO', 2023, 15, 2);

-- User 3 (ID: 3)
INSERT INTO usuario (id, nome, data_nascimento, email, senha) VALUES (3, 'Bob The Builder', '1985-08-20', 'bob@example.com', 'bobpass');
INSERT INTO carteira (id, nome) VALUES (3, 'Bob Savings');
INSERT INTO usuario_carteira (usuario_id, carteira_id) VALUES (3, 3);
INSERT INTO responsavel (id, nome, usuario_id) VALUES (3, 'Bob The Builder', 3);
INSERT INTO mes (id, nome, ano, porcentagem_investimento, carteira_id) VALUES (3, 'DEZEMBRO', 2023, 20, 3);

-- User 4 (ID: 4)
INSERT INTO usuario (id, nome, data_nascimento, email, senha) VALUES (4, 'Charlie Brown', '2000-12-01', 'charlie@example.com', 'charliepass');
INSERT INTO carteira (id, nome) VALUES (4, 'Charlie College Fund');
INSERT INTO usuario_carteira (usuario_id, carteira_id) VALUES (4, 4);
INSERT INTO responsavel (id, nome, usuario_id) VALUES (4, 'Charlie Brown', 4);
INSERT INTO mes (id, nome, ano, porcentagem_investimento, carteira_id) VALUES (4, 'DEZEMBRO', 2023, 5, 4);

-- User 5 (ID: 5)
INSERT INTO usuario (id, nome, data_nascimento, email, senha) VALUES (5, 'Diana Prince', '1975-03-22', 'diana@example.com', 'dianapass');
INSERT INTO carteira (id, nome) VALUES (5, 'Diana Investments');
INSERT INTO usuario_carteira (usuario_id, carteira_id) VALUES (5, 5);
INSERT INTO responsavel (id, nome, usuario_id) VALUES (5, 'Diana Prince', 5);
INSERT INTO mes (id, nome, ano, porcentagem_investimento, carteira_id) VALUES (5, 'DEZEMBRO', 2023, 25, 5);

-- User 6 (ID: 6)
INSERT INTO usuario (id, nome, data_nascimento, email, senha) VALUES (6, 'Ethan Hunt', '1988-07-10', 'ethan@example.com', 'ethanpass');
INSERT INTO carteira (id, nome) VALUES (6, 'Ethan Mission Funds');
INSERT INTO usuario_carteira (usuario_id, carteira_id) VALUES (6, 6);
INSERT INTO responsavel (id, nome, usuario_id) VALUES (6, 'Ethan Hunt', 6);
INSERT INTO mes (id, nome, ano, porcentagem_investimento, carteira_id) VALUES (6, 'DEZEMBRO', 2023, 12, 6);

-- Transactions for Admin User (User ID: 1, Responsavel ID: 1, Mes ID: 1)
-- Day 1-30
INSERT INTO transacao (data, descricao, valor, quantas_vezes, is_receita, banco_id, forma_pagamento, status, responsavel_id, mes_id, tipo_transacao) VALUES
('2023-12-01', 'Salary Deposit', 5000.00, 1, TRUE, 1, 'PIX', 'PAGO', 1, 1, 'CREDITO'),
('2023-12-01', 'Groceries Store A', 150.75, 1, FALSE, 2, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-02', 'Rent Payment', 1200.00, 1, FALSE, 3, 'PIX', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-03', 'Electricity Bill', 85.50, 1, FALSE, 1, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-04', 'Internet Bill', 99.90, 1, FALSE, 2, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-05', 'Coffee Shop', 15.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-06', 'Freelance Project Payment', 750.00, 1, TRUE, 5, 'PIX', 'PAGO', 1, 1, 'CREDITO'),
('2023-12-07', 'Gasoline', 60.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-08', 'Dinner with Friends', 120.00, 1, FALSE, 2, 'CARTAO', 'NAO_PAGO', 1, 1, 'DEBITO'),
('2023-12-09', 'Online Course Subscription', 49.99, 1, FALSE, 3, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-10', 'Pharmacy', 30.25, 1, FALSE, 4, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-11', 'Book Purchase', 45.00, 1, FALSE, 5, 'DINHEIRO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-12', 'Movie Tickets', 50.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-13', 'Lunch at Work', 22.50, 1, FALSE, 2, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-14', 'Public Transport Pass', 70.00, 1, FALSE, 3, 'DINHEIRO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-15', 'Investment Dividend', 200.00, 1, TRUE, 4, 'PIX', 'PAGO', 1, 1, 'CREDITO'),
('2023-12-16', 'Clothing Shopping', 180.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-17', 'Gym Membership', 60.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-18', 'Birthday Gift', 75.00, 1, FALSE, 2, 'DINHEIRO', 'NAO_PAGO', 1, 1, 'DEBITO'),
('2023-12-19', 'Phone Bill', 110.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-20', 'Bakery', 12.75, 1, FALSE, 4, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-21', 'Stock Purchase', 300.00, 1, FALSE, 5, 'PIX', 'PAGO', 1, 1, 'INVESTIMENTO'),
('2023-12-22', 'Charity Donation', 50.00, 1, FALSE, 1, 'PIX', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-23', 'Holiday Groceries', 250.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-24', 'Travel Ticket', 450.00, 1, FALSE, 3, 'CARTAO', 'NAO_PAGO', 1, 1, 'DEBITO'),
('2023-12-25', 'Christmas Bonus', 1000.00, 1, TRUE, 4, 'PIX', 'PAGO', 1, 1, 'CREDITO'),
('2023-12-26', 'Restaurant - Family Dinner', 220.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-27', 'Car Maintenance', 150.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-28', 'Streaming Service Renewal', 29.90, 1, FALSE, 2, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-29', 'Concert Ticket', 180.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-30', 'End of Year Party Contribution', 100.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 1, 1, 'DEBITO'),
('2023-12-31', 'New Year Eve Dinner', 300.00, 1, FALSE, 5, 'CARTAO', 'NAO_PAGO', 1, 1, 'DEBITO');

-- Transactions for Alice Wonderland (User ID: 2, Responsavel ID: 2, Mes ID: 2)
-- Day 1-30
INSERT INTO transacao (data, descricao, valor, quantas_vezes, is_receita, banco_id, forma_pagamento, status, responsavel_id, mes_id, tipo_transacao) VALUES
('2023-12-01', 'Monthly Salary', 4500.00, 1, TRUE, 2, 'PIX', 'PAGO', 2, 2, 'CREDITO'),
('2023-12-01', 'Supermarket Run', 120.50, 1, FALSE, 3, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-02', 'Apartment Rent', 1000.00, 1, FALSE, 1, 'PIX', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-03', 'Water Bill', 40.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-04', 'Mobile Plan', 70.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-05', 'Breakfast Cafe', 20.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-06', 'Consulting Fee Received', 600.00, 1, TRUE, 3, 'PIX', 'PAGO', 2, 2, 'CREDITO'),
('2023-12-07', 'Metro Card Top-up', 50.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-08', 'Cinema Night', 40.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-09', 'Language App Subscription', 19.99, 1, FALSE, 5, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-10', 'Painkillers', 10.50, 1, FALSE, 2, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-11', 'New Novel', 35.00, 1, FALSE, 3, 'DINHEIRO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-12', 'Theater Play', 70.00, 1, FALSE, 1, 'CARTAO', 'NAO_PAGO', 2, 2, 'DEBITO'),
('2023-12-13', 'Takeout Lunch', 18.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-14', 'Bus Fare', 20.00, 1, FALSE, 5, 'DINHEIRO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-15', 'Stock Dividends', 150.00, 1, TRUE, 2, 'PIX', 'PAGO', 2, 2, 'CREDITO'),
('2023-12-16', 'Winter Coat', 150.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-17', 'Yoga Class', 25.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-18', 'Anniversary Gift Purchase', 100.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-19', 'Cable TV Bill', 80.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-20', 'Cupcakes', 10.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-21', 'Mutual Fund Investment', 250.00, 1, FALSE, 3, 'PIX', 'PAGO', 2, 2, 'INVESTIMENTO'),
('2023-12-22', 'Local Charity Contribution', 30.00, 1, FALSE, 1, 'PIX', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-23', 'Christmas Dinner Ingredients', 200.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-24', 'Train Ticket Home', 120.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-25', 'Gift from Parents', 200.00, 1, TRUE, 2, 'PIX', 'PAGO', 2, 2, 'CREDITO'),
('2023-12-26', 'Holiday Brunch', 80.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-27', 'Bike Repair', 70.00, 1, FALSE, 1, 'CARTAO', 'NAO_PAGO', 2, 2, 'DEBITO'),
('2023-12-28', 'Music Streaming', 9.99, 1, FALSE, 4, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-29', 'Ice Skating Rink', 30.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-30', 'Office Party Dress', 90.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 2, 2, 'DEBITO'),
('2023-12-31', 'New Year Celebration With Friends', 150.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 2, 2, 'DEBITO');

-- Transactions for Bob The Builder (User ID: 3, Responsavel ID: 3, Mes ID: 3)
-- Day 1-30
INSERT INTO transacao (data, descricao, valor, quantas_vezes, is_receita, banco_id, forma_pagamento, status, responsavel_id, mes_id, tipo_transacao) VALUES
('2023-12-01', 'Project Payment 1', 3000.00, 1, TRUE, 3, 'PIX', 'PAGO', 3, 3, 'CREDITO'),
('2023-12-01', 'Hardware Store Supplies', 200.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-02', 'Mortgage', 1500.00, 1, FALSE, 2, 'PIX', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-03', 'Gas Bill', 120.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-04', 'Workshop Tools Insurance', 50.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-05', 'Diner Breakfast', 25.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-06', 'Project Payment 2', 2500.00, 1, TRUE, 4, 'PIX', 'PAGO', 3, 3, 'CREDITO'),
('2023-12-07', 'Truck Fuel', 100.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-08', 'Bowling with Team', 80.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-09', 'Safety Course', 150.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-10', 'First Aid Kit Refill', 25.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-11', 'Construction Magazine', 15.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-12', 'Local Fair Tickets', 30.00, 1, FALSE, 2, 'CARTAO', 'NAO_PAGO', 3, 3, 'DEBITO'),
('2023-12-13', 'Packed Lunch Supplies', 60.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-14', 'Van Maintenance', 200.00, 1, FALSE, 3, 'DINHEIRO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-15', 'Rental Income - Tools', 300.00, 1, TRUE, 1, 'PIX', 'PAGO', 3, 3, 'CREDITO'),
('2023-12-16', 'Work Boots', 120.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-17', 'Chiropractor Visit', 80.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-18', 'Tool for a Friend', 50.00, 1, FALSE, 5, 'DINHEIRO', 'NAO_PAGO', 3, 3, 'DEBITO'),
('2023-12-19', 'Workshop Electricity', 200.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-20', 'Pizza Night', 40.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-21', 'Savings Bond Purchase', 500.00, 1, FALSE, 4, 'PIX', 'PAGO', 3, 3, 'INVESTIMENTO'),
('2023-12-22', 'Community Project Materials', 100.00, 1, FALSE, 2, 'PIX', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-23', 'Holiday Feast Groceries', 300.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-24', 'Visit to Family - Fuel', 80.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-25', 'Bonus from Client', 500.00, 1, TRUE, 1, 'PIX', 'PAGO', 3, 3, 'CREDITO'),
('2023-12-26', 'Post-Christmas BBQ Supplies', 150.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-27', 'New Drill Bit Set', 90.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-28', 'Tool Magazine Subscription', 30.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-29', 'Local Football Game', 40.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-30', 'Year End Tool Maintenance', 120.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 3, 3, 'DEBITO'),
('2023-12-31', 'New Year Party at Home', 200.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 3, 3, 'DEBITO');

-- Transactions for Charlie Brown (User ID: 4, Responsavel ID: 4, Mes ID: 4)
-- Day 1-30
INSERT INTO transacao (data, descricao, valor, quantas_vezes, is_receita, banco_id, forma_pagamento, status, responsavel_id, mes_id, tipo_transacao) VALUES
('2023-12-01', 'Allowance from Parents', 500.00, 1, TRUE, 4, 'PIX', 'PAGO', 4, 4, 'CREDITO'),
('2023-12-01', 'Cafeteria Lunch', 10.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-02', 'Comic Book Store', 25.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-03', 'School Supplies', 15.50, 1, FALSE, 3, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-04', 'Mobile Game Purchase', 5.99, 1, FALSE, 1, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-05', 'Ice Cream', 7.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-06', 'Dog Walking Money', 50.00, 1, TRUE, 5, 'DINHEIRO', 'PAGO', 4, 4, 'CREDITO'),
('2023-12-07', 'Bus Pass', 30.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-08', 'Movie with Snoopy', 20.00, 1, FALSE, 3, 'DINHEIRO', 'NAO_PAGO', 4, 4, 'DEBITO'),
('2023-12-09', 'Library Late Fee', 2.50, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-10', 'Band-Aids for Kite Accident', 5.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-11', 'Peanuts Comic Collection', 30.00, 1, FALSE, 5, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-12', 'School Play Costume', 15.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-13', 'Pizza Slice', 3.50, 1, FALSE, 3, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-14', 'Skateboard Wheels', 20.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-15', 'Birthday Money from Grandma', 100.00, 1, TRUE, 4, 'PIX', 'PAGO', 4, 4, 'CREDITO'),
('2023-12-16', 'New T-Shirt', 22.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-17', 'Baseball Practice Fee', 10.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-18', 'Gift for Lucy', 12.00, 1, FALSE, 3, 'DINHEIRO', 'NAO_PAGO', 4, 4, 'DEBITO'),
('2023-12-19', 'Phone Credit Top-up', 20.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-20', 'Candy Bar', 2.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-21', 'Savings Account Deposit', 50.00, 1, FALSE, 5, 'PIX', 'PAGO', 4, 4, 'INVESTIMENTO'),
('2023-12-22', 'Donation to Animal Shelter', 10.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-23', 'Christmas Cookies Ingredients', 15.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-24', 'Bus to Grandma for Christmas', 10.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-25', 'Christmas Money', 150.00, 1, TRUE, 4, 'DINHEIRO', 'PAGO', 4, 4, 'CREDITO'),
('2023-12-26', 'Board Game Cafe', 18.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-27', 'Kite Repair Kit', 8.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-28', 'Online Game Subscription', 9.99, 1, FALSE, 3, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-29', 'Arcade Games', 15.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-30', 'Party Snacks for New Year', 20.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 4, 4, 'DEBITO'),
('2023-12-31', 'New Year Fireworks Contribution', 5.00, 1, FALSE, 5, 'DINHEIRO', 'NAO_PAGO', 4, 4, 'DEBITO');

-- Transactions for Diana Prince (User ID: 5, Responsavel ID: 5, Mes ID: 5)
-- Day 1-30
INSERT INTO transacao (data, descricao, valor, quantas_vezes, is_receita, banco_id, forma_pagamento, status, responsavel_id, mes_id, tipo_transacao) VALUES
('2023-12-01', 'Curator Salary', 6000.00, 1, TRUE, 5, 'PIX', 'PAGO', 5, 5, 'CREDITO'),
('2023-12-01', 'Organic Market Haul', 180.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-02', 'Penthouse Rent', 2000.00, 1, FALSE, 3, 'PIX', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-03', 'Utility Bills (All)', 250.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-04', 'Art Supplies', 150.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-05', 'Espresso Bar', 10.00, 1, FALSE, 5, 'DINHEIRO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-06', 'Antique Auction Purchase (Income)', 1200.00, 1, TRUE, 1, 'PIX', 'PAGO', 5, 5, 'CREDITO'),
('2023-12-07', 'Taxi for Gala Event', 70.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-08', 'Opera Tickets', 200.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-09', 'Historical Research Subscription', 70.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-10', 'Personal Trainer Session', 100.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-11', 'Rare Artifact Book', 90.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-12', 'Charity Gala Ticket', 300.00, 1, FALSE, 3, 'CARTAO', 'NAO_PAGO', 5, 5, 'DEBITO'),
('2023-12-13', 'Gourmet Lunch Meeting', 80.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-14', 'Chauffeur Service', 150.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-15', 'Amazonian Fund Dividend', 500.00, 1, TRUE, 5, 'PIX', 'PAGO', 5, 5, 'CREDITO'),
('2023-12-16', 'Designer Gown', 800.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-17', 'Spa Day', 250.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-18', 'Gift for Steve Trevor', 200.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-19', 'Security System Subscription', 100.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-20', 'Fine Dining Restaurant', 150.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-21', 'Art Investment', 1000.00, 1, FALSE, 1, 'PIX', 'PAGO', 5, 5, 'INVESTIMENTO'),
('2023-12-22', 'Donation to Museum Fund', 500.00, 1, FALSE, 3, 'PIX', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-23', 'Catering for Holiday Party', 1000.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-24', 'Flight to Themyscira (Private Jet Fuel)', 5000.00, 1, FALSE, 4, 'CARTAO', 'NAO_PAGO', 5, 5, 'DEBITO'),
('2023-12-25', 'Royalties from Likeness', 2000.00, 1, TRUE, 5, 'PIX', 'PAGO', 5, 5, 'CREDITO'),
('2023-12-26', 'Boxing Day Charity Auction', 700.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-27', 'Invisible Jet Maintenance', 1000.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-28', 'Exclusive Art Magazine Subscription', 50.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-29', 'Philanthropy Conference Ticket', 400.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-30', 'End of Year Gala Dress', 1200.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 5, 5, 'DEBITO'),
('2023-12-31', 'New Years Eve at Wayne Manor', 500.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 5, 5, 'DEBITO');

-- Transactions for Ethan Hunt (User ID: 6, Responsavel ID: 6, Mes ID: 6)
-- Day 1-30
INSERT INTO transacao (data, descricao, valor, quantas_vezes, is_receita, banco_id, forma_pagamento, status, responsavel_id, mes_id, tipo_transacao) VALUES
('2023-12-01', 'IMF Mission Payment', 10000.00, 1, TRUE, 1, 'PIX', 'PAGO', 6, 6, 'CREDITO'),
('2023-12-01', 'Survival Gear Shop', 300.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-02', 'Safe House Rent (Anonymous)', 1000.00, 1, FALSE, 3, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-03', 'Burner Phone Credit', 50.00, 1, FALSE, 4, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-04', 'Secure Comm Line Subscription', 200.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-05', 'Steak Dinner - Recon', 80.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-06', 'Payment for Intel', 2000.00, 1, TRUE, 2, 'DINHEIRO', 'PAGO', 6, 6, 'CREDITO'),
('2023-12-07', 'Untraceable Motorcycle Fuel', 40.00, 1, FALSE, 3, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-08', 'Disguise Kit Components', 150.00, 1, FALSE, 4, 'CARTAO', 'NAO_PAGO', 6, 6, 'DEBITO'),
('2023-12-09', 'Advanced Driving Course', 500.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-10', 'Field Medical Supplies', 70.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-11', 'Spy Thriller Novel (Research)', 20.00, 1, FALSE, 2, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-12', 'Escape Room (Team Building)', 60.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-13', 'Protein Bars & Rations', 50.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-14', 'Armored Car Rental', 300.00, 1, FALSE, 5, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-15', 'Recovered Asset Bonus', 5000.00, 1, TRUE, 1, 'PIX', 'PAGO', 6, 6, 'CREDITO'),
('2023-12-16', 'Tactical Suit', 1200.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-17', 'Combat Medic Training', 200.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-18', 'Flowers for Ex-Wife (Attempted)', 80.00, 1, FALSE, 4, 'DINHEIRO', 'NAO_PAGO', 6, 6, 'DEBITO'),
('2023-12-19', 'Encrypted Satellite Phone Bill', 150.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-20', 'Quick Meal - On The Run', 15.00, 1, FALSE, 1, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-21', 'Offshore Account Transfer (Expense)', 2000.00, 1, FALSE, 2, 'PIX', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-22', 'Donation to Whistleblower Fund', 100.00, 1, FALSE, 3, 'PIX', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-23', 'Emergency Supplies Cache', 400.00, 1, FALSE, 4, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-24', 'Charter Flight - Extraction', 3000.00, 1, FALSE, 5, 'CARTAO', 'NAO_PAGO', 6, 6, 'DEBITO'),
('2023-12-25', 'Holiday Pay (Hazardous Duty)', 2500.00, 1, TRUE, 1, 'PIX', 'PAGO', 6, 6, 'CREDITO'),
('2023-12-26', 'Debriefing Dinner with Team', 200.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-27', 'Gadget Repairs', 250.00, 1, FALSE, 3, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-28', 'Secure VPN Subscription', 19.99, 1, FALSE, 4, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-29', 'Rock Climbing Gym (Training)', 50.00, 1, FALSE, 5, 'CARTAO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-30', 'Ammunition Restock', 150.00, 1, FALSE, 1, 'DINHEIRO', 'PAGO', 6, 6, 'DEBITO'),
('2023-12-31', 'Low Key New Year Observation', 100.00, 1, FALSE, 2, 'CARTAO', 'PAGO', 6, 6, 'DEBITO');
