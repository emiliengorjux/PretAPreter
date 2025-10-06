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
                new Materiel("REF001", "Ordinateur portable", "Informatique", "Disponible", "Dell - Bon état"),
                new Materiel("REF002", "Écran 24 pouces", "Informatique", "Disponible", "Samsung - Bon état"),
                new Materiel("REF003", "Clavier mécanique", "Informatique", "Disponible", "Logitech - Bon état"),
                new Materiel("REF004", "Souris sans fil", "Informatique", "Disponible", "HP - Bon état")
        );

        repository.saveAll(materiels);
        log.info("🧩 Base de données initialisée avec {} matériels.", repository.count());
    }
}
