package fr.hiit.pretapreter.service.repository.model;

import fr.hiit.pretapreter.service.UtilisateurService;
import fr.hiit.pretapreter.service.repository.UtilisateurRepository;
import fr.hiit.pretapreter.service.repository.entity.Utilisateur;
import fr.hiit.pretapreter.service.repository.entity.Emprunt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<Utilisateur> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        return utilisateurRepository.findById(id).map(existing -> {
            existing.setNom(utilisateur.getNom());
            existing.setPrenom(utilisateur.getPrenom());
            existing.setEmail(utilisateur.getEmail());

            //  On met à jour les emprunts si nécessaire
            if (utilisateur.getEmprunts() != null) {
                existing.getEmprunts().clear();
                existing.getEmprunts().addAll(utilisateur.getEmprunts());
                for (Emprunt e : existing.getEmprunts()) {
                    e.setUtilisateur(existing); // Maintenir la relation bidirectionnelle
                }
            }

            return utilisateurRepository.save(existing);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'id : " + id));
    }

    @Override
    public void deleteUtilisateur(Long id) {
        utilisateurRepository.deleteById(id);
    }

    @Override
    public List<Utilisateur> getUtilisateursWithEmprunts() {
        // Charge tous les utilisateurs avec leurs emprunts et le matériel associé
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        // Hibernate chargera automatiquement les emprunts et les matériels si FetchType.EAGER ou via JOIN FETCH
        return utilisateurs;
    }
}
