package com.bootcamp.repository;

import com.bootcamp.entities.Fournisseur;

import java.sql.SQLException;
import java.util.List;

public class FournisseurRepository extends BaseRepository<Fournisseur> {


    public FournisseurRepository(String persistUnit) {
        super(persistUnit, Fournisseur.class);
        // TODO Auto-generated constructor stub
    }

    public Fournisseur findById(Long id) throws SQLException {
        Fournisseur fournisseur = (Fournisseur)this.getEm().createQuery("select e from Fournisseur e where e.id=:id")
                .setParameter("id",id).getSingleResult();
        return fournisseur;

    }

    public Fournisseur findByName(String name) throws SQLException{

        return (Fournisseur)this.getEm().createQuery("select e from Fournisseur e where e.nom=:name")
                .setParameter("name",name).getSingleResult();

    }

    @SuppressWarnings("unchecked")
    public List<Fournisseur> findByPays(String pays) throws SQLException{

        return (List<Fournisseur>)this.getEm().createQuery("select e from Fournisseur e where e.pays=:pays")
                .setParameter("pays",pays).getResultList();

    }


}
