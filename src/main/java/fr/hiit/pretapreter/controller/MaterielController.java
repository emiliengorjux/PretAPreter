package fr.hiit.pretapreter.controller;

import fr.hiit.pretapreter.dto.MaterielDto;
import fr.hiit.pretapreter.model.entity.Materiel;
import fr.hiit.pretapreter.service.MaterielService;
import org.springframework.http.MediaType;
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
        return materielService.getAllMateriel();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public MaterielDto getById(@PathVariable Long id) {
        return materielService.getMaterielById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public MaterielDto create(@RequestBody Materiel materiel) {
        return materielService.creeMateriel(MaterielDto.toDto(materiel));
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public MaterielDto update(@PathVariable Long id, @RequestBody Materiel materiel) {
        return materielService.updateMateriel(id, MaterielDto.toDto(materiel));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public void delete(@PathVariable Long id) {
        materielService.deleteMateriel(id);
    }
}
