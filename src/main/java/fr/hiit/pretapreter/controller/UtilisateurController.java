package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.service.UtilisateurService;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UtilisateurDto> getAll() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public UtilisateurDto getById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UtilisateurDto create(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(UtilisateurDto.toDto(utilisateur));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public UtilisateurDto update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, UtilisateurDto.toDto(utilisateur));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
