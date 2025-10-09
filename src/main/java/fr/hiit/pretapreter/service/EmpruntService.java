package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.service.repository.EmpruntRepository;


public class EmpruntService {
    private final MaterielService materielService;
    private final EmpruntRepository empruntRepository;

    public EmpruntService(MaterielService materielService,  EmpruntRepository empruntRepository) {
        this.materielService = materielService;
        this.empruntRepository = empruntRepository;
    }

    public EmpruntRepository getEmpruntRepository() {
        return empruntRepository;
    }

    public MaterielService getMaterielService() {
        return materielService;
    }
}
