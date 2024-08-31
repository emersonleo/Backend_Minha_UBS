ALTER TABLE registros_casos
ADD COLUMN id_endereco INT NOT NULL;

ALTER TABLE registros_casos
ADD CONSTRAINT fk_id_endereco
FOREIGN KEY (id_endereco) REFERENCES endereco(id);