package tn.esprit.devops_project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.entities.Livreur;
import tn.esprit.devops_project.repositories.LivreurRepository;
import tn.esprit.devops_project.services.Iservices.ILivreurService;

import java.util.List;
import java.util.Optional;
@Service

public class LivreurServiceImpl implements ILivreurService {
    private final LivreurRepository LivreurRepository;

    @Autowired
    public LivreurServiceImpl(LivreurRepository LivreurRepository) {
        this.LivreurRepository = LivreurRepository;
    }

    public List<Livreur> retrieveAllLivreurs() {
        return LivreurRepository.findAll();
    }

    public Livreur addLivreur(Livreur u) {
        return LivreurRepository.save(u);
    }

    @Override
    public Livreur updateLivreur(Long id, Livreur Livreur) {
        // Logique de mise à jour du livreur avec l'identifiant id
        // Assurez-vous de renvoyer l'objet Livreur mis à jour
        return LivreurRepository.save(Livreur);
    }


    public Livreur retrieveLivreur(Integer idLivreur) {
        Optional<Livreur> optionalLivreur = LivreurRepository.findById(idLivreur);

        if (optionalLivreur.isPresent()) {
            return optionalLivreur.get();
        } else {
            // Handle the case where the Livreur is not found
            return null; // or throw an appropriate exception
        }
    }

    public void deleteLivreur(Integer idLivreur) {
        LivreurRepository.delete(retrieveLivreur(idLivreur));
    }
}
