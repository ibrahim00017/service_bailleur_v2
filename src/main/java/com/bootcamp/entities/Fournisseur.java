package com.bootcamp.entities;

import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="tp_fournisseur")
@DiscriminatorValue("FOURNISSEUR")
@ApiModel(value="Fournisseur Model",description="Forunisseur Model witch is a Personne Model")
public class Fournisseur extends Personne {


    public Fournisseur() {
        super();
    }
    public Fournisseur(String nom) {
        super(nom);
    }
    public Fournisseur(String nom,String pays) {
        super(nom,pays);

    }
}
