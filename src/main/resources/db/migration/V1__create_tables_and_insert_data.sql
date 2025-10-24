-- Suppression des anciennes tables (ordre pour respecter les contraintes)
DROP TABLE IF EXISTS emprunt;
DROP TABLE IF EXISTS utilisateur;
DROP TABLE IF EXISTS materiel;

-- Table MATERIEL
CREATE TABLE materiel
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom           VARCHAR(255) NOT NULL,
    reference     VARCHAR(255),
    etat_materiel VARCHAR(255) NOT NULL,
    commentaire   VARCHAR(255),
    categorie     VARCHAR(255) NOT NULL,
    date_ajout    DATETIME     NOT NULL
);

-- Table UTILISATEUR
CREATE TABLE utilisateur
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom    VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    email  VARCHAR(255) NOT NULL UNIQUE
);

-- Table EMPRUNT
CREATE TABLE emprunt
(
    id                  BIGINT AUTO_INCREMENT PRIMARY KEY,
    materiel_id         BIGINT       NOT NULL,
    utilisateur_id      BIGINT       NOT NULL,
    date_emprunt        DATE         NOT NULL,
    retour_prevu        DATE         NOT NULL,
    retour_effectif     DATE,
    suivi_etat_materiel VARCHAR(255) NOT NULL,
    commentaire         VARCHAR(255),
    CONSTRAINT fk_emprunt_materiel FOREIGN KEY (materiel_id) REFERENCES materiel (id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_emprunt_utilisateur FOREIGN KEY (utilisateur_id) REFERENCES utilisateur (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- Données de test

-- Matériels
INSERT INTO materiel (nom, reference, etat_materiel, commentaire, categorie, date_ajout)
VALUES ('Ordinateur portable', 'ASUS', 'Bon état', 'RAS', 'Informatique', NOW()),
       ('Écran 24 pouces', 'LG', 'Moyen', 'Quelques rayures', 'Informatique', NOW()),
       ('Clavier mécanique', 'Logitech', 'Mauvais', 'À envoyer en réparation', 'Bureautique', NOW()),
       ('Switch', 'Nintendo', 'Parfait état', 'RAS', 'Console de jeu', NOW());

-- Utilisateurs
INSERT INTO utilisateur (nom, prenom, email)
VALUES ('Dupont', 'Didier', 'didier@exemple.com'),
       ('Martin', 'Yves', 'yves@exemple.com'),
       ('Durand', 'Guy', 'guy@exemple.com'),
       ('Petit', 'Michel', 'michel@exemple.com');

-- Emprunts
INSERT INTO emprunt (materiel_id, utilisateur_id, date_emprunt, retour_prevu, retour_effectif, suivi_etat_materiel, commentaire)
VALUES (1, 1, '2025-10-07', '2025-10-10', '2025-10-11', 'Bon', 'RAS'),
       (2, 2, '2025-10-07', '2025-10-10', '2025-10-11', 'Bon', 'Matériel en bon état'),
       (3, 3, '2025-10-07', '2025-10-10', '2025-10-11', 'Mauvais', 'Défectueux'),
       (4, 4, '2025-10-07', '2025-10-10', '2025-10-11', 'Excellent', 'RAS');
