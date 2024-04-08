CREATE TABLE `familia` (
	`id` INT NULL AUTO_INCREMENT,
	`nome` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY  `Index 1` (`id`)
)
COLLATE='utf8mb4_general_ci';

CREATE TABLE `familia_pessoa` (
	`id` INT NULL AUTO_INCREMENT,
	`id_pessoa` INT NULL,
	`id_familia` INT NULL,
	PRIMARY KEY  `Index 1` (`id`),
	CONSTRAINT `FK__pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK__familia` FOREIGN KEY (`id_familia`) REFERENCES `familia` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_general_ci';


ALTER TABLE `familia`
ADD COLUMN `endereco` INT NULL AFTER `nome`,
ADD CONSTRAINT `FK_familia_endereco` FOREIGN KEY (`endereco`) REFERENCES `endereco` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;



CREATE TABLE `informacoes_saude` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`tipo` INT NULL,
	`descricao_tipo` VARCHAR(50) NULL DEFAULT NULL,
	`descricao_info` VARCHAR(50) NULL DEFAULT NULL,
	PRIMARY KEY  `Index 1` (`id`)
)
COLLATE='utf8mb4_general_ci';