-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Dim 26 Juin 2016 à 22:34
-- Version du serveur :  5.5.42
-- Version de PHP :  7.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `helpy`
--

-- --------------------------------------------------------

--
-- Structure de la table `admin`
--

CREATE TABLE `admin` (
  `email` varchar(100) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `motDePasse` varchar(100) DEFAULT NULL,
  `date_inscription` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `admin`
--

INSERT INTO `admin` (`email`, `nom`, `prenom`, `motDePasse`, `date_inscription`) VALUES
('youssef.safi.95@gmail.com', 'safi', 'youssef', 'admin', '2016-05-14');

-- --------------------------------------------------------

--
-- Structure de la table `conference`
--

CREATE TABLE `conference` (
  `id` int(10) NOT NULL,
  `idMatiere` int(10) DEFAULT NULL,
  `idEleve` int(10) NOT NULL,
  `idProf` int(10) NOT NULL,
  `titre` varchar(100) NOT NULL,
  `description` varchar(250) NOT NULL,
  `date_debut` date NOT NULL,
  `heure_debut` time NOT NULL,
  `prix` float DEFAULT NULL,
  `cadeau20Min` tinyint(4) DEFAULT '0',
  `duree` int(10) DEFAULT NULL,
  `statut` int(11) DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `conference`
--

INSERT INTO `conference` (`id`, `idMatiere`, `idEleve`, `idProf`, `titre`, `description`, `date_debut`, `heure_debut`, `prix`, `cadeau20Min`, `duree`, `statut`) VALUES
(8, 3, 6, 4, 'test', 'desc 1', '2016-06-21', '00:50:00', 60, 1, 60, 2),
(9, 4, 6, 4, 'test', 'desc 2', '2016-06-23', '09:30:00', 31.4775, 0, 135, 2),
(10, 4, 6, 4, 'orthographe', '<p>desc 3</p>', '2016-06-30', '12:00:00', 27.98, 0, 120, 1),
(11, 5, 6, 5, 'test', '<p>desc 4</p>', '2015-09-15', '02:00:00', 60, 0, 60, 1),
(12, 5, 6, 5, 'rr', '<p>desc 5</p>', '2016-06-19', '03:00:00', 60, 0, 60, 1);

-- --------------------------------------------------------

--
-- Structure de la table `config`
--

CREATE TABLE `config` (
  `id` int(11) NOT NULL,
  `url` varchar(200) NOT NULL,
  `salt` varchar(200) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mdpEmail` varchar(100) NOT NULL,
  `mailHost` varchar(100) NOT NULL,
  `mailFrom` varchar(100) NOT NULL,
  `mailPort` int(11) NOT NULL,
  `urlSite` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `config`
--

INSERT INTO `config` (`id`, `url`, `salt`, `email`, `mdpEmail`, `mailHost`, `mailFrom`, `mailPort`, `urlSite`) VALUES
(1, 'http://163.172.41.215/bigbluebutton/', '7df2a40ea46dce8b2e616552ffc18b7f', 'youssef.safi.gi@gmail.com', 'S@fi1234', 'smtp.gmail.com', 'HelpyAcademy', 465, 'http://localhost:8080/HelpyAcademy/');

-- --------------------------------------------------------

--
-- Structure de la table `diplome`
--

CREATE TABLE `diplome` (
  `id` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `diplome` varchar(200) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `diplome`
--

INSERT INTO `diplome` (`id`, `idProf`, `diplome`) VALUES
(2, 4, 'DUT'),
(4, 4, 'LP'),
(5, 4, 'Master'),
(7, 5, 'DUT'),
(8, 6, 'hello'),
(9, 6, 'test'),
(10, 6, 'cc'),
(15, 9, 'D.U.T ');

-- --------------------------------------------------------

--
-- Structure de la table `enseigner`
--

CREATE TABLE `enseigner` (
  `idMatiere` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `description` text NOT NULL,
  `prixHeure` float NOT NULL,
  `cadeau20Min` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Contenu de la table `enseigner`
--

INSERT INTO `enseigner` (`idMatiere`, `idProf`, `description`, `prixHeure`, `cadeau20Min`) VALUES
(3, 4, 'arab desc ...', 60, 1),
(4, 4, 'test gg\r\n<b>salut</b>', 13.99, 0),
(5, 5, '<p>test</p>', 60, 1),
(6, 4, '<p></p><ul><li>option 1<br></li><li>option 2</li></ul><p></p>', 30, 0);

-- --------------------------------------------------------

--
-- Structure de la table `etudiant`
--

CREATE TABLE `etudiant` (
  `id` int(11) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `ville` varchar(40) DEFAULT NULL,
  `genre` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `solde` float NOT NULL,
  `niveau` int(11) NOT NULL,
  `date_inscription` date NOT NULL,
  `compte_active` tinyint(4) NOT NULL DEFAULT '0',
  `token` varchar(100) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `nom`, `prenom`, `tel`, `adresse`, `ville`, `genre`, `email`, `mdp`, `solde`, `niveau`, `date_inscription`, `compte_active`, `token`) VALUES
(5, 'SAFI', 'YOUSSEF', '', '', 'Fes', 'Etudiant', 'youssef@gmail.com', 'admin', 0, 2, '2016-05-19', 1, NULL),
(6, 'SAFI', 'YOUSSEF', NULL, NULL, NULL, 'Etudiant', 'youssef.safi@gmail.com', 'adminadmin', 0, 3, '2016-06-07', 1, NULL),
(11, 'Kermouss', 'Med', NULL, NULL, NULL, 'Etudiant', 'MKERMOUSS@gmail.com', 'admin', 0, 4, '2016-06-26', 0, '[B@41ab94ce'),
(16, 'SAFI', 'YOUSSEF', NULL, NULL, NULL, 'Etudiant', 'youssef.safi.95@gmail.com', 'admin', 0, 4, '2016-06-26', 0, '[B@614925b6');

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` int(11) NOT NULL,
  `idNiveau` int(11) NOT NULL,
  `matiere` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`id`, `idNiveau`, `matiere`) VALUES
(3, 1, 'Arabe'),
(4, 2, 'Francais'),
(5, 4, 'Math'),
(6, 4, 'PC'),
(7, 3, 'Informatique');

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

CREATE TABLE `message` (
  `id` int(11) NOT NULL,
  `idEleve` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `message` varchar(200) NOT NULL,
  `date_env` datetime NOT NULL,
  `lu` tinyint(1) DEFAULT NULL,
  `emetteur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `niveau`
--

CREATE TABLE `niveau` (
  `id` int(11) NOT NULL,
  `niveau` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `niveau`
--

INSERT INTO `niveau` (`id`, `niveau`) VALUES
(4, '1er annee bac SM - A'),
(1, '1ère année collège'),
(2, '2ème année collège'),
(3, '3ème année collège');

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `idEleve` int(11) NOT NULL,
  `idConf` int(11) NOT NULL,
  `note` int(11) DEFAULT '0',
  `commentaire` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `idPage` int(11) NOT NULL,
  `titre` varchar(100) NOT NULL,
  `date_notification` date NOT NULL,
  `espace` enum('e','a','p') NOT NULL,
  `type` varchar(100) NOT NULL,
  `vu` enum('0','1') NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `notification`
--

INSERT INTO `notification` (`id`, `idUtilisateur`, `idPage`, `titre`, `date_notification`, `espace`, `type`, `vu`) VALUES
(2, 4, 8, 'Vous avez recu une commande de l''etudiant <b>SAFI YOUSSEF</b>', '2016-06-08', 'p', 'cmd', '1'),
(3, 4, 9, 'Vous avez recu une commande de l''etudiant <b>SAFI YOUSSEF</b>', '2016-06-09', 'p', 'cmd', '1'),
(4, 4, 9, 'l''Etudiant <b>SAFI YOUSSEF</b> a annulé sa commande', '2016-06-09', 'p', 'cmdAnnuler', '1'),
(5, 6, 9, 'le professeur <b>SAFI YOUSSEF</b> a accepté votre commande', '2016-06-09', 'e', 'cmd', '0'),
(6, 4, 9, 'l''Etudiant <b>SAFI YOUSSEF</b> a annulé sa commande', '2016-06-09', 'p', 'cmdAnnuler', '1'),
(7, 4, 10, 'Vous avez recu une commande de l''Etudiant <b>SAFI YOUSSEF</b>', '2016-06-11', 'p', 'cmd', '1'),
(8, 6, 10, 'le professeur <b>SAFI YOUSSEF</b> a accepté votre commande', '2016-06-11', 'e', 'cmd', '0'),
(9, 5, 11, 'Vous avez recu une commande de l''Etudiant <b>SAFI YOUSSEF</b>', '2016-06-19', 'p', 'cmd', '0'),
(10, 5, 12, 'Vous avez recu une commande de l''Etudiant <b>SAFI YOUSSEF</b>', '2016-06-19', 'p', 'cmd', '0');

-- --------------------------------------------------------

--
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` int(11) NOT NULL,
  `civilite` varchar(10) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `ville` varchar(30) DEFAULT NULL,
  `tel` varchar(20) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `situation_pro` varchar(100) DEFAULT NULL,
  `niv_etude` varchar(50) DEFAULT NULL,
  `disponibilite` int(11) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `compte_active` enum('0','1','2') DEFAULT '0',
  `date_inscription` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `professeur`
--

INSERT INTO `professeur` (`id`, `civilite`, `nom`, `prenom`, `adresse`, `ville`, `tel`, `email`, `mdp`, `date_naissance`, `situation_pro`, `niv_etude`, `disponibilite`, `token`, `compte_active`, `date_inscription`) VALUES
(4, 'M', 'Ahmed', 'Bouhadda', 'Dhar El mahrez', 'Fes', '0626759424', 'Ahmed.bouhadda@gmail.com', 'admin', '2016-03-11', 'Professeur', 'BAC +4', 0, NULL, '2', '2016-05-19'),
(5, 'M', 'SAFI', 'YOUSSEF', NULL, NULL, '0626759424', 'youssef.joseph.ys@gmail.com', 'admin', '2016-06-09', NULL, NULL, 0, NULL, '2', '2016-06-07'),
(6, 'M', 'SAFI', 'YOUSSEF', NULL, NULL, '0626759424', 'youssef.safi.95@gmail.com', 'admin', '2016-07-01', NULL, NULL, 0, NULL, '2', '2016-06-25'),
(9, 'M', 'Kermouss', 'Med', NULL, NULL, '', 'MKERMOUSS@gmail.com', 'admin', '2016-06-19', NULL, NULL, 0, '[B@50ab08b2', '0', '2016-06-26');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`email`);

--
-- Index pour la table `conference`
--
ALTER TABLE `conference`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idMatiere` (`idMatiere`),
  ADD KEY `idEleve` (`idEleve`),
  ADD KEY `idProf` (`idProf`);

--
-- Index pour la table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `diplome`
--
ALTER TABLE `diplome`
  ADD PRIMARY KEY (`id`),
  ADD KEY `diplome_ibfk_1` (`idProf`);

--
-- Index pour la table `enseigner`
--
ALTER TABLE `enseigner`
  ADD PRIMARY KEY (`idMatiere`,`idProf`);

--
-- Index pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `niveau` (`niveau`);

--
-- Index pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idNiveau` (`idNiveau`);

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idEleve` (`idEleve`),
  ADD KEY `idProf` (`idProf`);

--
-- Index pour la table `niveau`
--
ALTER TABLE `niveau`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `niveau` (`niveau`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idEleve` (`idEleve`),
  ADD KEY `idProf` (`idProf`),
  ADD KEY `idConf` (`idConf`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `professeur`
--
ALTER TABLE `professeur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `niv_etude` (`niv_etude`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `conference`
--
ALTER TABLE `conference`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `diplome`
--
ALTER TABLE `diplome`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `conference`
--
ALTER TABLE `conference`
  ADD CONSTRAINT `conference_ibfk_1` FOREIGN KEY (`idMatiere`) REFERENCES `matiere` (`id`),
  ADD CONSTRAINT `conference_ibfk_2` FOREIGN KEY (`idEleve`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `conference_ibfk_3` FOREIGN KEY (`idProf`) REFERENCES `professeur` (`id`);

--
-- Contraintes pour la table `diplome`
--
ALTER TABLE `diplome`
  ADD CONSTRAINT `diplome_ibfk_1` FOREIGN KEY (`idProf`) REFERENCES `professeur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `etudiant`
--
ALTER TABLE `etudiant`
  ADD CONSTRAINT `etudiant_ibfk_1` FOREIGN KEY (`niveau`) REFERENCES `niveau` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `matiere`
--
ALTER TABLE `matiere`
  ADD CONSTRAINT `matiere_ibfk_1` FOREIGN KEY (`idNiveau`) REFERENCES `niveau` (`id`);

--
-- Contraintes pour la table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`idEleve`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`idProf`) REFERENCES `professeur` (`id`);

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `note_ibfk_1` FOREIGN KEY (`idEleve`) REFERENCES `etudiant` (`id`),
  ADD CONSTRAINT `note_ibfk_2` FOREIGN KEY (`idProf`) REFERENCES `professeur` (`id`),
  ADD CONSTRAINT `note_ibfk_3` FOREIGN KEY (`idConf`) REFERENCES `conference` (`id`);
