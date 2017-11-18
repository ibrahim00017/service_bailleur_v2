package com.bootcamp.tests;


import com.bootcamp.constants.AppConstants;
import com.bootcamp.entities.Bailleur;
import com.bootcamp.entities.Fournisseur;
import com.bootcamp.repository.BailleurRepository;
import org.testng.annotations.Test;

import javax.persistence.Persistence;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestBailleur {
	private BailleurRepository bailleurRepository = new BailleurRepository(AppConstants.unitPersistence);

	@Test
	public void generateTables(){

		Persistence.createEntityManagerFactory(AppConstants.unitPersistence, new Properties() {});
	}

	@Test
	public void createBailleurs() {
		Bailleur bailleur;
		List<Bailleur> bailleurs= new ArrayList<Bailleur>();
		String noms[]= {"abladon","Moudjib","gerauld","faroud","mariam","barriath",
				"bachiroudine","fatai","mansour","JP"};
		String[] pays= {"Algerie","Afganistan","Allmangne","Belgique","Benin","Burkina-Faso",
				"Canada","Malie","France","Londre","Togo","Ghana","Niger","Nigeria"};
		for(String name:noms) {
			bailleur=new Bailleur();
			System.out.println("bailleur "+name);
			Random rand = new Random();
			int  n = rand.nextInt(pays.length-1) + 1;
			bailleur.setNom(name);
			//System.out.println("bailleur name "+bailleur.getNom());
			bailleur.setPays(pays[n]);
			try {
				bailleurRepository.create(bailleur);
				System.out.println("bailleur name after insertion "+bailleur.getNom());
			} catch (SQLException e) {
				Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
			}
		}

	}



	@Test
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
	}
}
