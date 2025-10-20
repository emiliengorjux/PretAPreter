package fr.hiit.pretapreter.service.presentation.controller;

import fr.hiit.pretapreter.service.UtilisateurService;
import fr.hiit.pretapreter.service.repository.entity.Utilisateur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAll() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public Utilisateur getById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @PostMapping
    public Utilisateur create(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.createUtilisateur(utilisateur);
    }

    @PutMapping("/{id}")
    public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        return utilisateurService.updateUtilisateur(id, utilisateur);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        utilisateurService.deleteUtilisateur(id);
    }
}
