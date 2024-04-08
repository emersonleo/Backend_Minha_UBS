CREATE TABLE `visita` (
	`id` INT NOT NULL,
	`id_familia` INT NULL,
	`agente` INT NULL,
	`data` TIMESTAMP NULL DEFAULT NOW(),
	INDEX `Index 1` (`id`)
)
COLLATE='utf8mb4_general_ci';

ALTER TABLE `visita`
ADD CONSTRAINT `FK_visita_familia` FOREIGN KEY (`id_familia`) REFERENCES `familia` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
ADD CONSTRAINT `FK_visita_pessoa` FOREIGN KEY (`agente`) REFERENCES `pessoa` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION;