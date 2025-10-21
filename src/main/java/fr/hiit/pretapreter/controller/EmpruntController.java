package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.service.EmpruntService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    // --- Créer un nouvel emprunt ---
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmpruntDto createEmprunt(@RequestBody EmpruntDto empruntDto) {
        return empruntService.createEmprunt(
                empruntDto.getUtilisateurId(),
                empruntDto.getMaterielId(),
                empruntDto.getDateEmprunt(),
                empruntDto.getRetourPrevu()
        );
    }

    // --- Récupérer un emprunt par son ID ---
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmpruntDto getEmprunt(@PathVariable Long id) {
        return empruntService.findEmpruntById(id);
    }

    // --- Récupérer tous les emprunts ---
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmpruntDto> getAllEmprunts() {
        return empruntService.findAllEmprunts();
    }

    // --- Récupérer les emprunts d'un utilisateur spécifique ---
    @GetMapping(value = "/utilisateur/{utilisateurId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmpruntDto> getEmpruntsByUtilisateur(@PathVariable Long utilisateurId) {
        return empruntService.findAllByUtilisateurId(utilisateurId);
    }

    // --- Récupérer les emprunts d'un matériel spécifique ---
    @GetMapping(value = "/materiel/{materielId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmpruntDto> getEmpruntsByMateriel(@PathVariable Long materielId) {
        return empruntService.findAllByMaterielId(materielId);
    }

    // --- Mettre à jour un emprunt ---
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmpruntDto updateEmprunt(@PathVariable Long id, @RequestBody EmpruntDto empruntDto) {
        return empruntService.updateEmprunt(
                empruntService.updateEmprunt(empruntDto)
        );
    }

    // --- Supprimer un emprunt ---
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
    }
}
