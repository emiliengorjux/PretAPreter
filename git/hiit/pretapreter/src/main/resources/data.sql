INSERT INTO emprunt (id, materiel_id, emprunteur, date_emprunt, retour_prevu, retour_effectif, suivi_etat_materiel, commentaire)
VALUES
(1, 1, 'Didier', '2025-10-07', '2025-10-10', '2025-10-11', 'RAS', 'RAS'),
(2, 2, 'Yves', '2025-10-07', '2025-10-10', '2025-10-11', 'Bon', 'Matériel en bon état'),
(3, 3, 'Guy', '2025-10-07', '2025-10-10', '2025-10-11', 'Mauvais', 'Défectueux'),
(4, 4, 'Michel', '2025-10-07', '2025-10-10', '2025-10-11', 'RAS', 'RAS');


INSERT INTO materiel (id, nom, reference, etat_materiel, commentaire, categorie, date_ajout)
VALUES
(1, 'Ordinateur portable', 'REF001', 'Disponible', 'Bon état', 'Informatique', CURRENT_TIMESTAMP()),
(2, 'Écran 24 pouces', 'REF002', 'Disponible', 'RAS', 'Informatique', CURRENT_TIMESTAMP()),
(3, 'Clavier mécanique', 'REF003', 'Disponible', 'Bon état', 'Informatique', CURRENT_TIMESTAMP()),
(4, 'Souris sans fil', 'REF004', 'Disponible', 'RAS', 'Informatique', CURRENT_TIMESTAMP());