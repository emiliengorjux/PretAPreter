package fr.hiit.pretapreter.config;

import java.time.LocalDateTime;
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
        // repository.deleteAll();

        List<Materiel> materiels = List.of(
            new Materiel("1", "Ordinateur portable", "Dell", "Disponible", "Bon état", LocalDateTime.now(), "Description"),
            new Materiel("2", "Écran 24 pouces", "Samsung", "Disponible", "Bon état", LocalDateTime.now(), "Description"),
            new Materiel("3", "Clavier mécanique", "Logitech", "Disponible", "Bon état", LocalDateTime.now(), "Description"),
            new Materiel("4", "Souris sans fil", "HP", "Disponible", "Bon état", LocalDateTime.now(), "Description")
        );

        repository.saveAll(materiels);

        log.info("Base de données initialisée avec {} matériels.", repository.count());
        repository.findAll().forEach(m -> log.info("➡️ {}", m));
    }
}
