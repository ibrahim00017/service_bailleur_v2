package com.bootcamp.service;

import com.bootcamp.entities.Bailleur;
import com.bootcamp.service.crud.BailleurCRUD;

import java.util.List;

public class BailleurFS {
    public static Bailleur findByName(String name){
        if(BailleurCRUD.exist(name))
            return BailleurCRUD.read(name);
        return null;
    }

    public static Bailleur getById(Long id){
        if(BailleurCRUD.exist(id))
            return BailleurCRUD.read(id);
        return null;
    }

    public static boolean create(Bailleur bailleur){
        String nom = bailleur.getNom();

        if(BailleurCRUD.exist(bailleur.getNom()))
            return false;
        return BailleurCRUD.create(bailleur);
    }

    public static boolean delete(Long id){
        if(BailleurCRUD.exist(id)) {
          return BailleurCRUD.remove(BailleurCRUD.read(id));
        }
        return false;
    }

    public static boolean update(Bailleur bailleur){
        if(BailleurCRUD.exist(bailleur.getId()))
            return BailleurCRUD.update(bailleur);
        return false;
    }

    public static List<Bailleur> readAll(){
        return BailleurCRUD.readAll();

    }


}
