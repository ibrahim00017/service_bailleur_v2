package com.bootcamp.tests;
import com.bootcamp.constants.AppConstants;
import com.bootcamp.entities.Fournisseur;
import com.bootcamp.repository.FournisseurRepository;
import org.testng.annotations.Test;

import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestFournisseur {
    private FournisseurRepository fournisseurRepository = new FournisseurRepository(AppConstants.unitPersistence);

    @Test
    public void generateTables(){

        Persistence.createEntityManagerFactory(AppConstants.unitPersistence, new Properties() {});
    }

    @Test
    public void createFournisseurs() {
        Fournisseur fournisseur;
        String noms[]= {"fournisseur 1","fournisseur 2","fournisseur 3","fournisseur 4","fournisseur 5","fournisseur 6",
                "fournisseur 7","fournisseur 8","fournisseur 9","fournisseur 10"};
        String[] pays= {"Algerie","Afganistan","Allmangne","Belgique","Benin","Burkina-Faso",
                "Canada","Malie","France","Londre","Togo","Ghana","Niger","Nigeria"};
        for(String name:noms) {
            fournisseur=new Fournisseur();
            System.out.println("fournisseur "+name);
            Random rand = new Random();
            int  n = rand.nextInt(pays.length-1) + 1;
            fournisseur.setNom(name);
            fournisseur.setPays(pays[n]);
            try {
                fournisseurRepository.create(fournisseur);
                System.out.println("fournisseur name after insertion "+fournisseur.getNom());
            } catch (SQLException e) {
                Logger.getLogger(Fournisseur.class.getName()).log(Level.SEVERE, null, e);

            }
        }

    }


    @Test
    public void update(){
        try {
            Fournisseur fournisseur = fournisseurRepository.findByPropertyUnique("id",9);
            fournisseur.setNom("fournisseur 99");
            fournisseurRepository.update(fournisseur);
        } catch (SQLException e) {
            Logger.getLogger(Fournisseur.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    @Test
    public void readAll(){
        try {
            List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
            for(Fournisseur current : fournisseurs)
                System.out.println("name : "+current.getNom()+" pays: "+current.getPays());
        } catch (SQLException e) {
            Logger.getLogger(Fournisseur.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
