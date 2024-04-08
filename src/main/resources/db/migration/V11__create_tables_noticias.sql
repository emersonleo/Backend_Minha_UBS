
CREATE TABLE `noticias` (
	`id` INT NOT NULL AUTO_INCREMENT,
	`noticia` MEDIUMTEXT NOT NULL,
	`id_agente` INT NOT NULL,
	`id_posto` INT NOT NULL,
	`datahora` TIMESTAMP NOT NULL,
	PRIMARY KEY (`id`)
)
COLLATE='utf8mb4_general_ci';

ALTER TABLE `noticias`
ADD CONSTRAINT `FK_noticias_pessoa` FOREIGN KEY (`id_agente`) REFERENCES `pessoa` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
ADD CONSTRAINT `FK_noticias_posto` FOREIGN KEY (`id_posto`) REFERENCES `posto` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;
