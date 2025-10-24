package fr.hiit.pretapreter.controllertest;

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

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public MaterielDto update(@PathVariable Long id, @RequestBody Materiel materiel) {
        return materielService.updateMateriel(MaterielDto.toDto(materiel));
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public void delete(@PathVariable Long id) {
        materielService.deleteMateriel(id);
    }
}
