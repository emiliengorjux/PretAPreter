package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.repository.MaterielRepository;
import fr.hiit.pretapreter.service.repository.entity.Materiel;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class MaterielService {

    private final EmpruntService empruntService;
    private final MaterielRepository materielRepository;

    public MaterielService(EmpruntService empruntService,  MaterielRepository materielRepository) {
        this.empruntService = empruntService;
        this.materielRepository = materielRepository;
    }

    public MaterielRepository getMaterielRepository() {
        return materielRepository;
    }

    public EmpruntService getEmpruntService() {
        return empruntService;
    }



}
