package fr.hiit.pretapreter.service;

import org.springframework.stereotype.Service;

import fr.hiit.pretapreter.service.repository.MaterielRepository;

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
