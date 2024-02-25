ALTER TABLE `usuario`
	CHANGE COLUMN `senha` `senha` VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_general_ci' AFTER `email`;