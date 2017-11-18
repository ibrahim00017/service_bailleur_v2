/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author Ibrahim
 */
@Entity
@Table(name="tp_bailleur")
@DiscriminatorValue("BAILLEUR")
@ApiModel(value="Bailleur Model",description="Bailleur Model witch is a Personne Model")
public class Bailleur extends Personne {

	private static final long serialVersionUID = 1L;

	
	public Bailleur() {
		super();
	}
	public Bailleur(String nom) {
		super(nom);
	}
	public Bailleur(String nom,String pays) {
		super(nom,pays);

	}




/*  @OneToMany
  private List<Bailleur_has_Programme> bailleur_has_programme = new ArrayList<Bailleur_has_Programme>();
  @OneToMany
 private List<Bailleur_has_Projet> bailleurHasProjet = new ArrayList<Bailleur_has_Projet>();*/
  







}
