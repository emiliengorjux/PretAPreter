package fr.hiit.pretapreter.entity.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.hiit.pretapreter.entity.Materiel;
import fr.hiit.pretapreter.entity.repository.MaterielRepository;


@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final MaterielRepository repository;

    public DataInitializer(MaterielRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.info("✅ Base déjà initialisée ({} matériels)", repository.count());
            return;
        }

        List<Materiel> materiels = List.of(
                new Materiel("Ordinateur portable", "Dell", "Bon", "RAS", "Informatique"),
                new Materiel("Écran 24 pouces", "REF2", "Moyen", "Quelques rayures", "Informatique"),
                new Materiel("Clavier mécanique", "REF3", "Mauvais", "Reparation necessaire", "Informatique"),
                new Materiel("Switch", "REF4", "Bon", "RAS", "Console de jeu")
        );

        repository.saveAll(materiels);
        log.info("🧩 Base de données initialisée avec {} matériels.", repository.count());
    }
}
