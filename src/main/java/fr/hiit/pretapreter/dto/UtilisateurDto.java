package fr.hiit.pretapreter.dto;


import fr.hiit.pretapreter.model.entity.Utilisateur;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UtilisateurDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private List<EmpruntDto> emprunts;

    public static UtilisateurDto toDto(Utilisateur utilisateur) {
        UtilisateurDto dto = new UtilisateurDto();
        dto.setId(utilisateur.getId());
        dto.setNom(utilisateur.getNom());
        dto.setPrenom(utilisateur.getPrenom());
        dto.setEmail(utilisateur.getEmail());
        return dto;
    }

        public static Utilisateur toEntity (UtilisateurDto dto){
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setId(dto.getId());
            utilisateur.setNom(dto.getNom());
            utilisateur.setPrenom(dto.getPrenom());
            utilisateur.setEmail(dto.getEmail());

            return utilisateur;
        }
    }
