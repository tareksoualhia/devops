package tn.esprit.devops_project.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.controllers.dtos.LivreurDTO;
import tn.esprit.devops_project.entities.Livreur;
import tn.esprit.devops_project.services.LivreurServiceImpl;
import tn.esprit.devops_project.repositories.LivreurRepository;


import java.util.List;
@RestController
@RequestMapping("/livreur")
public class LivreurController {



    @Autowired
    private LivreurServiceImpl LivreurService;
    private final LivreurRepository LivreurRepository;

    @Autowired
    public LivreurController(LivreurServiceImpl LivreurService, LivreurRepository LivreurRepository) {
        this.LivreurService = LivreurService;
        this.LivreurRepository = LivreurRepository;
    }

    @GetMapping("/retrieve-all-Livreurs")
    @ResponseBody
    public List<Livreur> getLivreurs() {
        return LivreurService.retrieveAllLivreurs();
    }

    @GetMapping("/Livreurs/{id}")
    public ResponseEntity<Livreur> retrieveLivreur(@PathVariable int id) {
        Livreur Livreur = LivreurService.retrieveLivreur(id);
        return ResponseEntity.ok(Livreur); // Utilisez le statut approprié ici
    }


    // http://localhost:8089/Kaddem/Livreur/add-Livreur
    @PostMapping
    public ResponseEntity<Livreur> addLivreur(@RequestBody LivreurDTO LivreurDTO) {
        Livreur Livreur = convertDTOToLivreur(LivreurDTO);

        Livreur addedLivreur = LivreurService.addLivreur(Livreur);

        return new ResponseEntity<>(addedLivreur, HttpStatus.CREATED);
    }

    private Livreur convertDTOToLivreur(LivreurDTO LivreurDTO) {
        Livreur Livreur = new Livreur();
        Livreur.setNomLivreur(LivreurDTO.getNomLivreur());
        // Ajoutez d'autres champs en fonction de votre modèle Livreur

        return Livreur;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livreur> updateLivreur(@PathVariable Long id, @RequestBody LivreurDTO LivreurDTO) {
        // Logique de mise à jour de l'Livreurersité avec l'identifiant id
        Livreur Livreur = convertDTOToLivreur(LivreurDTO);
        Livreur updatedLivreur = LivreurService.updateLivreur(id, Livreur);
        return ResponseEntity.ok(updatedLivreur);
    }

    @DeleteMapping("/delete-Livreur/{Livreur-id}")
    public ResponseEntity<Void> deleteLivreur(@PathVariable("Livreur-id") Integer LivreurId) {
        try {
            LivreurService.deleteLivreur(LivreurId);
            return ResponseEntity.ok().build(); // 200 OK
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error
        }
    }
}
