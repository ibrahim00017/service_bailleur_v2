package com.bootcamp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.bootcamp.constants.AppConstants;
import com.bootcamp.entities.Bailleur;
import com.bootcamp.repository.BailleurRepository;

public class BailleurCRUD implements AppConstants{
	public static boolean create(Bailleur bailleur) {
		BailleurRepository bailleurRepository = new BailleurRepository(unitPersistence);
		try {
		return 	bailleurRepository.create(bailleur);

		} catch (SQLException e) {
			Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);

		}
		return false;
	}
	
	public static boolean update(Bailleur bailleur) {
		BailleurRepository bailleurRepository = new BailleurRepository(unitPersistence);
		try {
			return bailleurRepository.update(bailleur);
		} catch (SQLException e) {
			Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
		
	}
	
	public static boolean delete(Bailleur bailleur) {
		BailleurRepository bailleurRepository = new BailleurRepository(unitPersistence);
		try {
			bailleurRepository.delete(bailleur);
			return true;
		} catch (SQLException e) {
						Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
			return false;
		}
	}

	public static Bailleur read(Long id){
		BailleurRepository bailleurRepository = new BailleurRepository(unitPersistence);
		try {
			return bailleurRepository.findById(id);

		} catch (SQLException e) {
			Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

	public static List<Bailleur> readAll(){
		BailleurRepository bailleurRepository = new BailleurRepository(unitPersistence);
		try {
			return bailleurRepository.findAll();
		} catch (SQLException e) {
			Logger.getLogger(Bailleur.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}

}
