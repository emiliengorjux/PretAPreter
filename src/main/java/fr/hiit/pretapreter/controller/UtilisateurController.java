package fr.hiit.pretapreter.controller;

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
    public List<Utilisateur> getAll() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public Utilisateur getById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
