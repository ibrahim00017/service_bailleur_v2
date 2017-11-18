/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author Ibrahim
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name="TYPE_ENTITY")
@DiscriminatorValue("PERSONNE")
@ApiModel(value="Personne Model")
public class Personne implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length=45)
    @NotNull(message="veillez entrer un nom svp")
    private String nom;

    @Column(length=45)
    private String pays;
    
    public Long getId() {
        return id;
    }
    
    public Personne(){
        
    }

    public Personne(String nom){
        this.nom=nom;

    }

    public Personne(String nom,String pays){
        this.nom=nom;
        this.pays=pays;

    }

    @ApiModelProperty(value="The country of the Bailleur")
    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }
    
    @ApiModelProperty(value="the name of the Personne")
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Override
    public String toString() {
        return "com.bootcamp.jpa.Personne[ id=" + id + " ]";
    }
    
}
