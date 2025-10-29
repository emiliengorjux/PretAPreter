package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.MaterielDto;
import fr.hiit.pretapreter.model.entity.Materiel;
import fr.hiit.pretapreter.service.MaterielService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/materiels")
public class MaterielController {

    private final MaterielService materielService;

    public MaterielController(MaterielService materielService) {
        this.materielService = materielService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MaterielDto> getAllMateriel() {
        return materielService.findAllMateriels();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public MaterielDto getById(@PathVariable Long id) {
        return materielService.findMaterielById(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MaterielDto creeMateriel(@RequestBody Materiel materiel) {
        return materielService.creeMateriel(MaterielDto.toDto(materiel));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MaterielDto> update(@PathVariable Long id, @RequestBody Materiel materiel) {

        // On s'assure que l'ID de l'URL prime sur celui du corps JSON
        materiel.setId(id);

        // On appelle le service pour faire la mise à jour
        MaterielDto updated = materielService.updateMateriel(MaterielDto.toDto(materiel));

        // On renvoie une réponse HTTP 200 OK avec le corps JSON
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public void delete(@PathVariable Long id) {
        materielService.deleteMateriel(id);
    }
}
