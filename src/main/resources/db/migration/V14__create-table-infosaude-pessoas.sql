CREATE TABLE minha_ubs.registros_casos (
    id INT auto_increment NOT NULL,
    data_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_informacoes_saude INT NOT NULL,
    id_pessoa INT NOT NULL,
    id_agente INT NOT NULL,
    id_posto INT NOT NULL,
    CONSTRAINT registros_casos_pk PRIMARY KEY (id),
    CONSTRAINT caso FOREIGN KEY (id_informacoes_saude) REFERENCES minha_ubs.informacoes_saude(id),
    CONSTRAINT pessoa FOREIGN KEY (id_pessoa) REFERENCES minha_ubs.pessoa(id),
    CONSTRAINT agente FOREIGN KEY (id_agente) REFERENCES minha_ubs.pessoa(id),
    CONSTRAINT posto FOREIGN KEY (id_posto) REFERENCES minha_ubs.posto(id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;


