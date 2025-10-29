package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.model.entity.Emprunt;
import fr.hiit.pretapreter.service.EmpruntService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    // --- Créer un nouvel emprunt ---
    @PostMapping(value = "/{utilisateurId}/{materielId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmpruntDto> createEmprunt(
            @PathVariable Long utilisateurId,
            @PathVariable Long materielId,
            @RequestBody EmpruntDto empruntDto) {

        EmpruntDto created = empruntService.createEmprunt(utilisateurId, materielId, empruntDto);
        return ResponseEntity.ok(created);
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

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmpruntDto> updateEmprunt(
            @PathVariable Long id,
            @RequestBody Emprunt emprunt) {

        emprunt.setId(id);

        EmpruntDto updated = empruntService.updateEmprunt(EmpruntDto.toDto(emprunt));

        return ResponseEntity.ok(updated);
    }


    // --- Supprimer un emprunt ---
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
    }
}
