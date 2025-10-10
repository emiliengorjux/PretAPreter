package fr.hiit.pretapreter.service;

import org.springframework.stereotype.Service;

import fr.hiit.pretapreter.service.repository.EmpruntRepository;
import fr.hiit.pretapreter.service.repository.MaterielRepository;

@Service
public class MaterielService {

    private final EmpruntRepository empruntRepository;
    private final MaterielRepository materielRepository;

    public MaterielService(EmpruntRepository empruntRepository,  MaterielRepository materielRepository) {
        this.empruntRepository = empruntRepository;
        this.materielRepository = materielRepository;
    }

    public MaterielRepository getMaterielRepository() {
        return materielRepository;
    }

    public EmpruntRepository getEmpruntRepository() {
        return empruntRepository;
    }

   



}
