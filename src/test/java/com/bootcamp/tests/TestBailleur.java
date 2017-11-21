package com.bootcamp.tests;


import com.bootcamp.constants.AppConstants;
import com.bootcamp.entities.Bailleur;
import com.bootcamp.entities.Fournisseur;
import com.bootcamp.resources.BailleurService;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;


public class TestBailleur {
    protected BailleurService bs;
    @BeforeSuite
    public void generateTables(){
        bs =new BailleurService();
		Persistence.createEntityManagerFactory(AppConstants.PERSISTENCE_UNIT, new Properties() {});
	}

	@Test
	public void createBailleurs() {
        Bailleur bailleur;
        List<Bailleur> bailleurs = new ArrayList<Bailleur>();
        String noms[] = {"abladon", "Moudjib", "gerauld", "faroud", "mariam", "barriath",
                "bachiroudine", "fatai", "mansour", "JP"};
        String[] pays = {"Algerie", "Afganistan", "Allmangne", "Belgique", "Benin", "Burkina-Faso",
                "Canada", "Malie", "France", "Londre", "Togo", "Ghana", "Niger", "Nigeria"};
        for (String name : noms) {
            bailleur = new Bailleur();
            System.out.println("bailleur " + name);
            Random rand = new Random();
            int n = rand.nextInt(pays.length - 1) + 1;
            bailleur.setNom(name);
            //System.out.println("bailleur name "+bailleur.getNom());
            bailleur.setPays(pays[n]);

            Assert.assertTrue(bs.create(bailleur).hasEntity());
            System.out.println("bailleur name after insertion " + bailleur.getNom());


        }
    }



	/*@Test
	public void update(){
		try {
			Bailleur bailleur = bailleurRepository.findByPropertyUnique("id",9);
			bailleur.setNom("Aliou");
			bailleurRepository.update(bailleur);
		} catch (SQLException e) {
			Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
		}

	}

	@Test
	public void readAll(){
		try {
			List<Bailleur> bailleurs = bailleurRepository.findAll();
			for(Bailleur current : bailleurs)
				System.out.println("name : "+current.getNom()+" pays: "+current.getPays());
		} catch (SQLException e) {
			Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
		}
	}*/
}
