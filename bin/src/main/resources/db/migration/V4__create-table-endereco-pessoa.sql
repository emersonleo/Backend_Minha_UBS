CREATE TABLE IF NOT EXISTS `endereco_pessoa` (
  `id_endereco` int(11) NOT NULL,
  `id_pessoa` int(11) NOT NULL,
  PRIMARY KEY (`id_pessoa`,`id_endereco`),
  KEY `FK_endereco` (`id_endereco`),
  CONSTRAINT `FK_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;