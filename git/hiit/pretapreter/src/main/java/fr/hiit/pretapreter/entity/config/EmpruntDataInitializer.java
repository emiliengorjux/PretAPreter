package fr.hiit.pretapreter.entity.config;

import fr.hiit.pretapreter.entity.Emprunt;
import fr.hiit.pretapreter.entity.Materiel;
import fr.hiit.pretapreter.entity.repository.EmpruntRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmpruntDataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EmpruntDataInitializer.class);
    private final EmpruntRepository empruntRepository;

    public EmpruntDataInitializer(EmpruntRepository empruntRepository) {
        this.empruntRepository = empruntRepository;
    }

    @Override
    public void run(String... args) {
        if (empruntRepository.count() > 0) {
            logger.info("Base déjà initialisée ({} emprunts)", empruntRepository.count());
            return;
        }

        List<Emprunt> emprunts = List.of(
                new Emprunt(new Materiel(), "Didier", LocalDate.of(2025, 10, 7), LocalDate.of(2025, 10, 10),"RAS", "RAS"),
                new Emprunt(new Materiel(), "Yves", LocalDate.of(2025, 10, 7), LocalDate.of(2025, 10, 10), "Bon", "Le matériel est bon"),
                new Emprunt(new Materiel(), "Guy", LocalDate.of(2025, 10, 7), LocalDate.of(2025, 10, 10), "Mauvais", "Le matériel est défectueux"),
                new Emprunt(new Materiel(), "Michel", LocalDate.of(2025, 10, 7), LocalDate.of(2025, 10, 10), "RAS", "RAS")
        );

        empruntRepository.saveAll(emprunts);
        logger.info("Base de données initialisée avec {} emprunts.", empruntRepository.count());
    }
}
