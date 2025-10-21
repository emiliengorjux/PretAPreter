package fr.hiit.pretapreter.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import fr.hiit.pretapreter.model.entity.Utilisateur;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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

        if (utilisateur.getEmprunts() != null && !utilisateur.getEmprunts().isEmpty()) {
            dto.setEmprunts(utilisateur.getEmprunts()
                    .stream()
                    .map(EmpruntDto::toDto)
                    .collect(Collectors.toList()));
        } return dto;
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
