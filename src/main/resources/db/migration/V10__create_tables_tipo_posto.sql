CREATE TABLE `tipo_pessoa` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`tipo` VARCHAR(50) NOT NULL DEFAULT '0',
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_general_ci';

INSERT INTO `minha_ubs`.`tipo_pessoa` (`tipo`) VALUES ('AGENTE');
INSERT INTO `minha_ubs`.`tipo_pessoa` (`tipo`) VALUES ('MORADOR');

CREATE TABLE `posto` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(50) NOT NULL DEFAULT '0',
	`endereco` INT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_general_ci';

ALTER TABLE `posto`
ADD CONSTRAINT `FK_posto_endereco` FOREIGN KEY (`endereco`) REFERENCES `endereco` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;


CREATE TABLE `posto_pessoas` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`id_pessoa` INT NULL,
	`id_posto` INT NULL,
	`id_tipo` INT NULL,
	PRIMARY KEY (`id`),
	CONSTRAINT `FK_pp_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_pp_posto` FOREIGN KEY (`id_posto`) REFERENCES `posto` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `FK_pp_tipo_pessoa` FOREIGN KEY (`id_tipo`) REFERENCES `tipo_pessoa` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_general_ci';

ALTER TABLE `visita`
ADD CONSTRAINT `FK_visita_posto` FOREIGN KEY (`posto`) REFERENCES `posto` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
