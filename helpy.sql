-- phpMyAdmin SQL Dump
-- version 4.4.10
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Jeu 19 Mai 2016 à 22:10
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
  `statut` enum('0','1','2','3') DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `diplome`
--

CREATE TABLE `diplome` (
  `id` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `diplome` varchar(200) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `diplome`
--

INSERT INTO `diplome` (`id`, `idProf`, `diplome`) VALUES
(2, 4, 'DUT ');

-- --------------------------------------------------------

--
-- Structure de la table `enseigner`
--

CREATE TABLE `enseigner` (
  `idMatiere` int(11) NOT NULL,
  `idProf` int(11) NOT NULL,
  `prixHeure` float NOT NULL,
  `cadeau20Min` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `etudiant`
--

INSERT INTO `etudiant` (`id`, `nom`, `prenom`, `tel`, `adresse`, `ville`, `genre`, `email`, `mdp`, `solde`, `niveau`, `date_inscription`, `compte_active`, `token`) VALUES
(5, 'SAFI', 'YOUSSEF', NULL, NULL, NULL, 'Etudiant', 'youssef.safi.95@gmail.com', 'admin', 0, 1, '2016-05-19', 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `matiere`
--

CREATE TABLE `matiere` (
  `id` int(11) NOT NULL,
  `idNiveau` int(11) NOT NULL,
  `matiere` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `matiere`
--

INSERT INTO `matiere` (`id`, `idNiveau`, `matiere`) VALUES
(3, 1, 'Arabe'),
(4, 2, 'Francais');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `niveau`
--

INSERT INTO `niveau` (`id`, `niveau`) VALUES
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
-- Structure de la table `professeur`
--

CREATE TABLE `professeur` (
  `id` int(11) NOT NULL,
  `civilite` varchar(10) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `adresse` varchar(100) DEFAULT NULL,
  `ville` varchar(30) DEFAULT NULL,
  `tel` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mdp` varchar(100) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `situation_pro` varchar(100) DEFAULT NULL,
  `niv_etude` varchar(50) DEFAULT NULL,
  `disponibilite` int(11) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `compte_active` enum('0','1','2') DEFAULT '0',
  `date_inscription` date NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Contenu de la table `professeur`
--

INSERT INTO `professeur` (`id`, `civilite`, `nom`, `prenom`, `adresse`, `ville`, `tel`, `email`, `mdp`, `date_naissance`, `situation_pro`, `niv_etude`, `disponibilite`, `token`, `compte_active`, `date_inscription`) VALUES
(4, 'M', 'SAFI', 'YOUSSEF', NULL, NULL, '0626759424', 'youssef.safi.95@gmail.com', 'admin', NULL, NULL, NULL, 0, NULL, '1', '2016-05-19');

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
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `diplome`
--
ALTER TABLE `diplome`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `matiere`
--
ALTER TABLE `matiere`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `niveau`
--
ALTER TABLE `niveau`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `note`
--
ALTER TABLE `note`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `professeur`
--
ALTER TABLE `professeur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
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
