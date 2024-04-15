package tn.esprit.devops_project.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Livreur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivreur;
    private String nomLivreur;

    public Livreur(Integer idLivreur, String nomLivreur) {
        this.idLivreur = idLivreur;
        this.nomLivreur = nomLivreur;
    }

    public Integer getIdLivreur() {
        return idLivreur;
    }

    public String getNomLivreur() {
        return nomLivreur;
    }

    public void setNomLivreur(String nomLivreur) {
        this.nomLivreur = nomLivreur;
    }
}
