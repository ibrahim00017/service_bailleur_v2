package com.bootcamp.repository;

import com.bootcamp.entities.Bailleur;

import java.sql.SQLException;
import java.util.List;



public class BailleurRepository extends BaseRepository<Bailleur>{

	public BailleurRepository(String persistUnit) {
		super(persistUnit, Bailleur.class);
		// TODO Auto-generated constructor stub
	}
	
	public Bailleur findById(Long id) throws SQLException {
		Bailleur bailleur = (Bailleur)this.getEm().createQuery("select e from Bailleur e where e.id=:id")
		.setParameter("id",id).getSingleResult();
		return bailleur;
			
	}
	
	public Bailleur findByName(String name) throws SQLException{
				
	  return (Bailleur)this.getEm().createQuery("select e from Bailleur e where e.nom=:name")
							.setParameter("name",name).getSingleResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Bailleur> findByPays(String pays) throws SQLException{
		
		  return (List<Bailleur>)this.getEm().createQuery("select e from Bailleur e where e.pays=:pays")
								.setParameter("pays",pays).getResultList();
			
		}
	

}
