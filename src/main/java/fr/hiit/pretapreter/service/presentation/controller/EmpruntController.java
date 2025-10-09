// package fr.hiit.pretapreter.service.presentation.controller;

// import java.time.LocalDate;

// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import fr.hiit.pretapreter.service.EmpruntService;
// import fr.hiit.pretapreter.service.presentation.dto.EmpruntDto;

// @RestController
// @RequestMapping("/api/emprunts")
// public class EmpruntController {

//     private final EmpruntService empruntService;

//     public EmpruntController(EmpruntService empruntService) {
//         this.empruntService = empruntService;
//     }

//     // Créer un emprunt
//     @PostMapping
//     public EmpruntDto createEmprunt(
//             @RequestParam String emprunteur,
//             @RequestParam Long materielId,
//             @RequestParam String dateEmprunt,
//             @RequestParam String retourPrevu
//     ) {
//         return empruntService.createEmprunt(
//                 emprunteur,
//                 materielId,
//                 LocalDate.parse(dateEmprunt),
//                 LocalDate.parse(retourPrevu)
//         );
//     }

//     // // Optionnel : récupérer tous les emprunts
//     // @GetMapping
//     // public java.util.List<EmpruntDto> getAllEmprunts() {
//     //     return empruntService.getAllEmprunts();
//     // }
// }

