package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.service.EmpruntService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emprunts")
public class EmpruntController {

    private final EmpruntService empruntService;

    public EmpruntController(EmpruntService empruntService) {
        this.empruntService = empruntService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmpruntDto createEmprunt(@RequestBody EmpruntDto empruntDto) {
        return empruntService.createEmprunt(
                empruntDto.getUtilisateurId(),
                empruntDto.getMaterielId(),
                empruntDto.getDateEmprunt(),
                empruntDto.getRetourPrevu()
        );
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public EmpruntDto getEmprunt(@PathVariable Long id) {
        return empruntService.findEmpruntById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmpruntDto> getAllEmprunts() {
        return empruntService.findAllEmprunts();
    }

    @GetMapping(value = "/utilisateur/{utilisateurId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmpruntDto> getEmpruntsByUtilisateur(@PathVariable Long utilisateurId) {
        return empruntService.findAllByUtilisateurId(utilisateurId);
    }

    @GetMapping(value = "/materiel/{materielId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EmpruntDto> getEmpruntsByMateriel(@PathVariable Long materielId) {
        return empruntService.findAllByMaterielId(materielId);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public EmpruntDto updateEmprunt(@PathVariable Long id, @RequestBody EmpruntDto empruntDto) {
        return empruntService.updateEmprunt(
                empruntService.updateEmprunt(empruntDto)
        );
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteEmprunt(@PathVariable Long id) {
        empruntService.deleteEmprunt(id);
    }
}
