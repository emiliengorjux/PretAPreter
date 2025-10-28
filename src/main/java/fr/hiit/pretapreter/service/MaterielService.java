package fr.hiit.pretapreter.service;

import fr.hiit.pretapreter.dto.MaterielDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MaterielService {
    MaterielDto creeMateriel(MaterielDto materielDto);
    MaterielDto updateMateriel(MaterielDto materielDto);
    void deleteMateriel(Long id);
    MaterielDto findMaterielById(Long id);
    List<MaterielDto> findAllMateriels();
}
