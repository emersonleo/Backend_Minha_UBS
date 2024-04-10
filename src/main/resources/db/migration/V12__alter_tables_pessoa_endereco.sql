ALTER TABLE minha_ubs.pessoa MODIFY COLUMN fone VARCHAR(11) NOT NULL;
ALTER TABLE minha_ubs.pessoa MODIFY COLUMN cpf VARCHAR(11) NOT NULL;

ALTER TABLE minha_ubs.endereco ADD latitude varchar(100) NULL;
ALTER TABLE minha_ubs.endereco ADD longitude varchar(100) NULL;
ALTER TABLE minha_ubs.endereco MODIFY COLUMN CEP VARCHAR(11) DEFAULT NULL NULL;