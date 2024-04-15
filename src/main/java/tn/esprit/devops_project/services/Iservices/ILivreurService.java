package tn.esprit.devops_project.services.Iservices;

import tn.esprit.devops_project.entities.Livreur;

import java.util.List;

public interface ILivreurService {
    List<Livreur> retrieveAllLivreurs();

    Livreur addLivreur (Livreur  u);


    Livreur updateLivreur(Long id, Livreur Livreur);

    Livreur retrieveLivreur (Integer idLivreur);

    public  void deleteLivreur(Integer idLivreur);

}
