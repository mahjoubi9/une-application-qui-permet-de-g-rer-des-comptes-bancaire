-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 10 mars 2022 à 15:07
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_banque_lsi`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `code_client` bigint(20) NOT NULL,
  `nom_client` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`code_client`, `nom_client`) VALUES
(5, 'redwane'),
(7, 'sjhshshs'),
(11, 'moud'),
(12, 'mohammed'),
(16, 'moha'),
(17, 'marown'),
(18, 'ddddd'),
(19, 'sds'),
(20, 'marwan'),
(21, 'sdsssssss'),
(22, 'sddddd'),
(23, 'ahmad'),
(24, 'vbbbb'),
(25, 'asdff'),
(26, 'fatima'),
(28, 'fgdjhj');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `type_cpte` varchar(2) NOT NULL,
  `code_compte` varchar(255) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `solde` double NOT NULL,
  `taux` double DEFAULT NULL,
  `decouvert` double DEFAULT NULL,
  `code_cli` bigint(20) DEFAULT NULL,
  `code_emp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`type_cpte`, `code_compte`, `date_creation`, `solde`, `taux`, `decouvert`, `code_cli`, `code_emp`) VALUES
('CC', '1', '2021-11-22 14:24:17.000000', 1012, NULL, 11, 22, NULL),
('CC', '2', '2021-11-25 22:37:54.000000', 10011, NULL, 11, NULL, 6),
('CE', 'assdddffgrfkj', '2021-11-25 22:56:11.000000', 11, 1, NULL, 5, 6),
('CC', 'jee', '2021-11-25 22:44:52.000000', 11, NULL, 11, 5, 6),
('CC', 'lsi', '2021-11-25 23:52:04.000000', 11, NULL, 11, 28, 7);

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

CREATE TABLE `employe` (
  `code_employe` bigint(20) NOT NULL,
  `nom_employe` varchar(255) DEFAULT NULL,
  `code_emp_sup` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`code_employe`, `nom_employe`, `code_emp_sup`) VALUES
(3, 'mahjoubi', NULL),
(6, 'redwane', NULL),
(7, 'fatima', NULL),
(8, 'rrrr', NULL),
(10, 'mahjoubiiii', NULL),
(11, 'final', 3),
(12, 'project', 3),
(17, 'project', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `emp_gr`
--

CREATE TABLE `emp_gr` (
  `employe_code_employe` bigint(20) NOT NULL,
  `groupes_code_groupe` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `emp_gr`
--

INSERT INTO `emp_gr` (`employe_code_employe`, `groupes_code_groupe`) VALUES
(6, 5),
(6, 1),
(8, 1),
(8, 3),
(11, 5),
(11, 3),
(11, 1),
(17, 3),
(17, 3),
(7, 3),
(7, 2),
(7, 1),
(7, 3),
(3, 1),
(3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `groupe`
--

CREATE TABLE `groupe` (
  `code_groupe` bigint(20) NOT NULL,
  `nom_groupe` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `groupe`
--

INSERT INTO `groupe` (`code_groupe`, `nom_groupe`) VALUES
(1, 'ta'),
(2, ',t'),
(3, 't'),
(4, 'reeeee'),
(5, 'redwane'),
(6, 'redwane');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE `operation` (
  `dtype` varchar(1) NOT NULL,
  `numero_operation` bigint(20) NOT NULL,
  `date_operation` datetime(6) DEFAULT NULL,
  `montant` double NOT NULL,
  `code_cpte` varchar(255) DEFAULT NULL,
  `code_emp` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`dtype`, `numero_operation`, `date_operation`, `montant`, `code_cpte`, `code_emp`) VALUES
('R', 1, '2021-11-22 16:15:44.000000', 11, '2', NULL),
('V', 2, '2021-11-22 16:16:56.000000', 999, '2', 3),
('R', 3, '2021-11-22 16:20:58.000000', 11, '2', 3),
('V', 4, '2021-11-22 16:20:58.000000', 11, '1', 3),
('V', 5, '2021-11-22 16:45:12.000000', 999, '2', 3),
('R', 6, '2021-11-22 19:52:54.000000', 1000, '2', 3),
('V', 7, '2021-11-22 22:11:51.000000', 999, '1', 3),
('R', 8, '2021-11-22 23:00:22.000000', 11, '2', 3),
('R', 9, '2021-11-24 09:27:13.000000', 9, '1', 3),
('V', 10, '2021-11-25 11:59:48.000000', 999, '2', 3),
('V', 11, '2021-11-26 10:26:10.000000', 10000, '2', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`code_client`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`code_compte`),
  ADD KEY `FK2hw4shqsxc782lychpkr52lmv` (`code_cli`),
  ADD KEY `FKbm21kemcgkptyq3x0ge8ciaqu` (`code_emp`);

--
-- Index pour la table `employe`
--
ALTER TABLE `employe`
  ADD PRIMARY KEY (`code_employe`),
  ADD KEY `FKhsh3jgj0ctoiewmr371xmvby5` (`code_emp_sup`);

--
-- Index pour la table `emp_gr`
--
ALTER TABLE `emp_gr`
  ADD KEY `FKh9b18q3rdl1rq8txwr8hx2uox` (`groupes_code_groupe`),
  ADD KEY `FKok7g6c24qy0e7p22le9civkqk` (`employe_code_employe`);

--
-- Index pour la table `groupe`
--
ALTER TABLE `groupe`
  ADD PRIMARY KEY (`code_groupe`);

--
-- Index pour la table `operation`
--
ALTER TABLE `operation`
  ADD PRIMARY KEY (`numero_operation`),
  ADD KEY `FKkr9nfjf0ipqrw5xwcf9jqq6gv` (`code_cpte`),
  ADD KEY `FK88rq579v3pyf0y6v3p9wvhjo7` (`code_emp`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `code_client` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT pour la table `employe`
--
ALTER TABLE `employe`
  MODIFY `code_employe` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT pour la table `groupe`
--
ALTER TABLE `groupe`
  MODIFY `code_groupe` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `operation`
--
ALTER TABLE `operation`
  MODIFY `numero_operation` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `FK2hw4shqsxc782lychpkr52lmv` FOREIGN KEY (`code_cli`) REFERENCES `client` (`code_client`),
  ADD CONSTRAINT `FKbm21kemcgkptyq3x0ge8ciaqu` FOREIGN KEY (`code_emp`) REFERENCES `employe` (`code_employe`);

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `FKhsh3jgj0ctoiewmr371xmvby5` FOREIGN KEY (`code_emp_sup`) REFERENCES `employe` (`code_employe`);

--
-- Contraintes pour la table `emp_gr`
--
ALTER TABLE `emp_gr`
  ADD CONSTRAINT `FKh9b18q3rdl1rq8txwr8hx2uox` FOREIGN KEY (`groupes_code_groupe`) REFERENCES `groupe` (`code_groupe`),
  ADD CONSTRAINT `FKok7g6c24qy0e7p22le9civkqk` FOREIGN KEY (`employe_code_employe`) REFERENCES `employe` (`code_employe`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `FK88rq579v3pyf0y6v3p9wvhjo7` FOREIGN KEY (`code_emp`) REFERENCES `employe` (`code_employe`),
  ADD CONSTRAINT `FKkr9nfjf0ipqrw5xwcf9jqq6gv` FOREIGN KEY (`code_cpte`) REFERENCES `compte` (`code_compte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
