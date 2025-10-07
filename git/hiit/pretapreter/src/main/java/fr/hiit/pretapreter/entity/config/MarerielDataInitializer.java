package fr.hiit.pretapreter.entity.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import fr.hiit.pretapreter.entity.Materiel;
import fr.hiit.pretapreter.entity.repository.MaterielRepository;


@Component
public class MarerielDataInitializer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MarerielDataInitializer.class);
    private final MaterielRepository repository;

    public MarerielDataInitializer(MaterielRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            log.info("Base initialisée ({} matériels)", repository.count());
            return;
        }

        List<Materiel> materiels = List.of(

                new Materiel("Ordinateur portable", "Dell", "Bon", "RAS", "Informatique"),
                new Materiel("Écran 24 pouces", "LG", "Moyen", "Quelques rayures", "Informatique"),
                new Materiel("Clavier mécanique", "Logitech", "Mauvais", "Reparation necessaire", "Informatique"),
                new Materiel("Switch", "Nintendo", "Bon", "RAS", "Console de jeu")
        );

        repository.saveAll(materiels);
        log.info("Base de données initialisée avec {} matériels.", repository.count());
    }
}
