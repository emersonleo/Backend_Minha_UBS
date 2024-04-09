ALTER TABLE minha_ubs.pessoa MODIFY COLUMN fone VARCHAR(11) NOT NULL;
ALTER TABLE minha_ubs.pessoa MODIFY COLUMN cpf VARCHAR(11) NOT NULL;
ALTER TABLE minha_ubs.endereco ADD latitude varchar(100) NULL;
ALTER TABLE minha_ubs.endereco ADD longitude varchar(100) NULL;

INSERT INTO minha_ubs.pessoa (cpf, fone, nome)
VALUES
('12345678900', '11912345678', 'João Silva'),
('23456789101', '21923456789', 'Maria Oliveira'),
('34567891202', '31934567890', 'Carlos Pereira'),
('45678912303', '41945678901', 'Ana Costa'),
('56789123404', '51956789012', 'Patricia Santos'),
('67891234505', '61967890123', 'Lucas Souza'),
('78912345606', '71978901234', 'Paula Rocha'),
('89123456707', '81989012345', 'Rafael Dias'),
('91234567808', '91990123456', 'Fernanda Gomes'),
('12345678909', '11991234567', 'Gabriel Martins');

INSERT INTO endereco (pais, uf, cidade, bairro, rua, numero, CEP, complemento, latitude, longitude)
VALUES
('Brasil', 'PE', 'Moreno', 'Centro', 'Av. Dr. Sofrenio Portela', '3754', '54800000', 'Prefeitura Municipal de Moreno', '-8.1087064743042', '-35.08354187011719'),
('Brasil', 'PE', 'Moreno', 'Centro', 'Av. Dr. Sofrenio Portela', '3790', '54800000', 'Compras Mix Atacarejo', '-8.1087064743042', '-35.08354187011719'),
('Brasil', 'PE', 'Moreno', 'Centro', 'Av. Dr. Sofrenio Portela', '3910', '54800000', 'Supermercado Pernambucano Moreno', '-8.1087064743042', '-35.08354187011719');
('Brasil', 'PE', 'Moreno', 'Centro', 'Rua Quintino Bocaiúva', '241', '54800-000', 'Compesa Loja de Atendimento Moreno', '-8.1203', '-35.0927');
('Brasil', 'PE', 'Moreno', 'Centro', 'Rua Primeiro de Maio', '332', '54800-000', 'Casarão Catende Moreno', '-8.1195', '-35.0914');
('Brasil', 'PE', 'Moreno', 'Centro', 'Rua 15 de Novembro', '175', '54800-000', 'Oficina Serve Diesel Moreno', '-8.1197', '-35.0916');