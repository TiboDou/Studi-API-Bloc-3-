package com.bloc3.vitrine_produits_promo.REST.Services.Impl;


import com.bloc3.vitrine_produits_promo.DAO.ProduitsRepository;
import com.bloc3.vitrine_produits_promo.Models.Produits;
import com.bloc3.vitrine_produits_promo.REST.Services.ProduitsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class ProduitsServiceImpl implements ProduitsServices {

    @Autowired
    private ProduitsRepository produitsRepository;

    @Override
    public List<Produits> selectProduits() {
        List<Produits> list = new ArrayList<Produits>();
        produitsRepository.findAll().forEach(list::add);
        return list;
    }



    @Override
    public Produits findById(int no_produit) {
        if(produitsRepository.findById(no_produit).isPresent()) {
            return produitsRepository.findById(no_produit).get();
        }
        return null;
    }

    @Override
    public int create(Produits produit) {
        return produitsRepository.save(produit).getNo_produit();
    }

    @Override
    public void update(int no_produit, Produits produit) {
        produit.setNo_produit(no_produit);
        produitsRepository.save(produit);
    }

    @Override
    public void partialUpdate(int no_produit, Map<String, Object> updates) {
        Produits produitAMaj = produitsRepository.findById(no_produit).get();
        for (String key : updates.keySet()) {
            switch (key) {
                case "no_categorie": {
                    produitAMaj.setNo_categorie((Integer) updates.get(key));
                    break;
                }
                case "libelle": {
                    produitAMaj.setLibelle((String) updates.get(key));
                    break;
                }
                case "description": {
                    produitAMaj.setDescription((String) updates.get(key));
                }
                case "prix": {
                    double d = (double) updates.get(key);
                    float f = (float) d; //J'ai dû caster de cette manière sinon j'avais une erreur style cannot convert java.truc.Double to java.machin.Float
                    produitAMaj.setPrix(f);
                    break;
                }
                case "url_img": {
                    produitAMaj.setImg_url((String) updates.get(key));
                    break;
                }
            }
        }
        produitsRepository.save(produitAMaj);
    }

    @Override
    public void deleteById(int no_produit) {
        produitsRepository.deleteById(no_produit);
    }


}
