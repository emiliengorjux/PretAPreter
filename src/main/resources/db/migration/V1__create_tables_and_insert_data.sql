-- Création des tables
CREATE TABLE materiel (
    id BIGINT PRIMARY KEY,
    nom VARCHAR(255),
    reference VARCHAR(255),
    etat_materiel VARCHAR(255),
    commentaire VARCHAR(255),
    categorie VARCHAR(255),
    date_ajout TIMESTAMP
);

CREATE TABLE emprunt (
    id BIGINT PRIMARY KEY,
    materiel_id BIGINT,
    emprunteur VARCHAR(255),
    date_emprunt DATE,
    retour_prevu DATE,
    retour_effectif DATE,
    suivi_etat_materiel VARCHAR(255),
    commentaire VARCHAR(255),
    CONSTRAINT fk_materiel FOREIGN KEY (materiel_id) REFERENCES materiel(id)
);

-- Insertion des données
INSERT INTO materiel (id, nom, reference, etat_materiel, commentaire, categorie, date_ajout)
VALUES
(1, 'Ordinateur portable', 'ASUS', 'Bon état', 'RAS', 'Informatique', CURRENT_TIMESTAMP()),
(2, 'Ecran 24 pouces', 'LG', 'Moyen', 'Quelques rayures', 'Informatique', CURRENT_TIMESTAMP()),
(3, 'Clavier mécanique', 'Logitech', 'Mauvais', 'A envoyer en réparation', 'Bureautique', CURRENT_TIMESTAMP()),
(4, 'Switch', 'Nintendo', 'Parfait état', 'RAS', 'Console de jeu', CURRENT_TIMESTAMP());

INSERT INTO emprunt (id, materiel_id, emprunteur, date_emprunt, retour_prevu, retour_effectif, suivi_etat_materiel, commentaire)
VALUES
(1, 1, 'Didier', '2025-10-07', '2025-10-10', '2025-10-11', 'RAS', 'RAS'),
(2, 2, 'Yves', '2025-10-07', '2025-10-10', '2025-10-11', 'Bon', 'Matériel en bon état'),
(3, 3, 'Guy', '2025-10-07', '2025-10-10', '2025-10-11', 'Mauvais', 'Défectueux'),
(4, 4, 'Michel', '2025-10-07', '2025-10-10', '2025-10-11', 'RAS', 'RAS');
