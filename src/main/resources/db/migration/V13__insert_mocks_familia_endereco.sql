INSERT INTO minha_ubs.pessoa (nome,fone,cpf) 
VALUES
('João Silva','11912345678','12345678900'),
('Maria Oliveira','21923456789','23456789101'),
('Carlos Pereira','31934567890','34567891202'),
('Ana Costa','41945678901','45678912303'),
('Patricia Santos','51956789012','56789123404'),
('Lucas Souza','61967890123','67891234505'),
('Paula Rocha','71978901234','78912345606'),
('Rafael Dias','81989012345','89123456707'),
('Fernanda Gomes','91990123456','91234567808'),
('Gabriel Martins','11991234567','12345678909'),
('Lindalva Lira','81987654321','11234456789');

INSERT INTO minha_ubs.endereco (pais,uf,cidade,bairro,rua,numero,CEP,complemento,latitude,longitude) 
VALUES
('Brasil','PE','Moreno','Centro','Av. Dr. Sofrenio Portela','3754','54800000','Prefeitura Municipal de Moreno','-8.1087064743042','-35.08354187011719'),
('Brasil','PE','Moreno','Centro','Av. Dr. Sofrenio Portela','3790','54800000','Compras Mix Atacarejo','-8.1087064743042','-35.08354187011719'),
('Brasil','PE','Moreno','Centro','Av. Dr. Sofrenio Portela','3910','54800000','Supermercado Pernambucano Moreno','-8.1087064743042','-35.08354187011719'),
('Brasil','PE','Moreno','Centro','Rua Quintino Bocaiúva','241','54800000','Compesa Loja de Atendimento Moreno','-8.119686138656748','-35.09710723823727'),
('Brasil','PE','Moreno','Centro','Rua Primeiro de Maio','332','54800000','Casarão Catende Moreno','-8.12000757540068','-35.09700075835775'),
('Brasil','PE','Moreno','Centro','Rua 15 de Novembro','175','54800000','Oficina Serve Diesel Moreno','-8.118558677526785','-35.09663701700321'),
('Brasil','PE','Moreno','Centro','Rua das Pedreiras','120','54800000','','-8.11034403562939','-35.09772179360201');


INSERT INTO minha_ubs.familia (nome,endereco) 
VALUES
('Família Silva', 1),
('Família Costa', 2),
('Família Martins', 3),
('Família Pereira', 4),
('Família Gomes', 5);


INSERT INTO minha_ubs.familia_pessoa (id_pessoa,id_familia) 
VALUES
(1,1),
(2,1),
(3,2),
(4,2),
(5,3),
(6,3),
(7,4),
(8,4),
(9,5),
(10,5);


INSERT INTO minha_ubs.posto (nome,endereco) VALUES
('PSF Pedreira Mangueira',7);

INSERT INTO minha_ubs.posto_pessoas (id_pessoa,id_posto,id_tipo) VALUES
(1,1,2),
(2,1,2),
(3,1,2),
(4,1,2),
(5,1,2),
(6,1,2),
(7,1,2),
(8,1,2),
(9,1,2),
(10,1,2),
(11,1,1);
