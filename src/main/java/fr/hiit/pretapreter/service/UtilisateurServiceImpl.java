package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.EmpruntDto;
import fr.hiit.pretapreter.dto.UtilisateurDto;
import fr.hiit.pretapreter.repository.UtilisateurRepository;
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
    public UtilisateurDto createUtilisateur(UtilisateurDto utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Optional<UtilisateurDto> getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id);
    }

    @Override
    public List<UtilisateurDto> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public UtilisateurDto updateUtilisateur(Long id, UtilisateurDto utilisateur) {
        return utilisateurRepository.findById(id).map(existing -> {
            existing.setNom(utilisateur.getNom());
            existing.setPrenom(utilisateur.getPrenom());
            existing.setEmail(utilisateur.getEmail());

            //  On met à jour les emprunts si nécessaire
            if (utilisateur.getEmprunts() != null) {
                existing.getEmprunts().clear();
                existing.getEmprunts().addAll(utilisateur.getEmprunts());
                for (EmpruntDto e : existing.getEmprunts()) {
                    e.setEmprunteurNom(String.valueOf(existing)); // Maintenir la relation bidirectionnelle
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
    public List<UtilisateurDto> getUtilisateursWithEmprunts() {
        // Charge tous les utilisateurs avec leurs emprunts et le matériel associé
        List<UtilisateurDto> utilisateurs = utilisateurRepository.findAll();
        // Hibernate chargera automatiquement les emprunts et les matériels si FetchType.EAGER ou via JOIN FETCH
        return utilisateurs;
    }
}
