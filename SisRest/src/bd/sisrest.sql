-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 02-Fev-2017 às 00:30
-- Versão do servidor: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `sisrest`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `cliente`
--

CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_cliente` varchar(45) NOT NULL,
  `sobrenome_cliente` varchar(45) NOT NULL,
  `cpf_cliente` varchar(20) NOT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

CREATE TABLE IF NOT EXISTS `estoque` (
  `id_estoque` int(11) NOT NULL AUTO_INCREMENT,
  `qtd_estoque` int(11) NOT NULL,
  `data_validade_estoque` date NOT NULL,
  `num_lote_estoque` varchar(20) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  PRIMARY KEY (`id_estoque`,`id_ingrediente`),
  KEY `fk_Estoque_Ingrediente1_idx` (`id_ingrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `ingrediente`
--

CREATE TABLE IF NOT EXISTS `ingrediente` (
  `id_ingrediente` int(11) NOT NULL AUTO_INCREMENT,
  `nome_ingrediente` varchar(45) NOT NULL,
  `preco_ingrediente` double NOT NULL,
  PRIMARY KEY (`id_ingrediente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto`
--

CREATE TABLE IF NOT EXISTS `produto` (
  `id_produto` int(11) NOT NULL AUTO_INCREMENT,
  `nome_produto` varchar(45) NOT NULL,
  `preco_produto` double NOT NULL,
  `id_receita` int(11) NOT NULL,
  PRIMARY KEY (`id_produto`,`id_receita`),
  KEY `fk_Produto_Receita1_idx` (`id_receita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `produto_has_venda`
--

CREATE TABLE IF NOT EXISTS `produto_has_venda` (
  `qtd_prd_vend` int(11) NOT NULL,
  `id_produto` int(11) NOT NULL,
  `id_vendas` int(11) NOT NULL,
  PRIMARY KEY (`id_produto`,`id_vendas`),
  KEY `fk_Produto_has_Pedido_Produto1_idx` (`id_produto`),
  KEY `fk_Produto_has_Venda_Vendas1_idx` (`id_vendas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `receita`
--

CREATE TABLE IF NOT EXISTS `receita` (
  `id_receita` int(11) NOT NULL AUTO_INCREMENT,
  `nome_receita` varchar(45) NOT NULL,
  PRIMARY KEY (`id_receita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estrutura da tabela `receita_has_ingrediente`
--

CREATE TABLE IF NOT EXISTS `receita_has_ingrediente` (
  `receita_ingrediente_qtd` int(11) NOT NULL,
  `id_receita` int(11) NOT NULL,
  `id_ingrediente` int(11) NOT NULL,
  PRIMARY KEY (`id_receita`,`id_ingrediente`),
  KEY `fk_Receita_has_Ingrediente_Ingrediente1_idx` (`id_ingrediente`),
  KEY `fk_Receita_has_Ingrediente_Receita1_idx` (`id_receita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usu` int(11) NOT NULL AUTO_INCREMENT,
  `login_usu` varchar(25) NOT NULL,
  `nome_usu` varchar(45) NOT NULL,
  `sobrenome_usu` varchar(45) NOT NULL,
  `sexo_usu` varchar(1) NOT NULL,
  `nivel_usu` enum('A','G','C','Z') NOT NULL,
  `senha_usu` varchar(45) NOT NULL,
  PRIMARY KEY (`id_usu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usu`, `login_usu`, `nome_usu`, `sobrenome_usu`, `sexo_usu`, `nivel_usu`, `senha_usu`) VALUES
(1, 'admin', 'fulano', 'tal', 'M', 'A', '1234');

-- --------------------------------------------------------

--
-- Estrutura da tabela `vendas`
--

CREATE TABLE IF NOT EXISTS `vendas` (
  `id_vendas` int(11) NOT NULL AUTO_INCREMENT,
  `data_vendas` date NOT NULL,
  `meiopag_vendas` enum('C','D') NOT NULL,
  `status_vendas` enum('Andamento','Pronto') NOT NULL,
  `id_cliente` int(11) NOT NULL,
  PRIMARY KEY (`id_vendas`,`id_cliente`),
  KEY `fk_Vendas_Cliente1_idx` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `estoque`
--
ALTER TABLE `estoque`
  ADD CONSTRAINT `fk_Estoque_Ingrediente1` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingrediente` (`id_ingrediente`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `produto`
--
ALTER TABLE `produto`
  ADD CONSTRAINT `fk_Produto_Receita1` FOREIGN KEY (`id_receita`) REFERENCES `receita` (`id_receita`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `produto_has_venda`
--
ALTER TABLE `produto_has_venda`
  ADD CONSTRAINT `fk_Produto_has_Pedido_Produto1` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id_produto`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Produto_has_Venda_Vendas1` FOREIGN KEY (`id_vendas`) REFERENCES `vendas` (`id_vendas`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `receita_has_ingrediente`
--
ALTER TABLE `receita_has_ingrediente`
  ADD CONSTRAINT `fk_Receita_has_Ingrediente_Receita1` FOREIGN KEY (`id_receita`) REFERENCES `receita` (`id_receita`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_Receita_has_Ingrediente_Ingrediente1` FOREIGN KEY (`id_ingrediente`) REFERENCES `ingrediente` (`id_ingrediente`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limitadores para a tabela `vendas`
--
ALTER TABLE `vendas`
  ADD CONSTRAINT `fk_Vendas_Cliente1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE CASCADE ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
