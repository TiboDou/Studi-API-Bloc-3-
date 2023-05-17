package com.bloc3.vitrine_produits_promo.REST.Services.Impl;

import com.bloc3.vitrine_produits_promo.DAO.ProduitsRepository;
import com.bloc3.vitrine_produits_promo.DAO.PromotionsRepository;
import com.bloc3.vitrine_produits_promo.Models.Produits;
import com.bloc3.vitrine_produits_promo.Models.Promotions;
import com.bloc3.vitrine_produits_promo.REST.Services.PromotionsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PromotionsServiceImpl implements PromotionsServices {

    @Autowired
    private ProduitsRepository prodRepository;

    @Autowired
    public PromotionsRepository promoRepository;
    @Override
    public List<Promotions> findAllOfProduits(int no_produit) {
        return prodRepository.findById(no_produit).get().getPromotions();
    }

    @Override
    public Promotions findById(int no_promotion) {
        if(promoRepository.findById(no_promotion).isPresent()) {
            return promoRepository.findById(no_promotion).get();
        }
        return null;
    }

    @Override
    public int create(int noProduit, Promotions promotion) {
        Produits prodEntity = prodRepository.findById(noProduit).get();
        prodEntity.getPromotions().add(promotion);
        prodRepository.save(prodEntity);
        Promotions promoEntity = prodEntity.getPromotions().stream().filter(p -> p.equals(promotion)).findFirst().get();
        return promoEntity.getNo_promotion();
    }

    @Override
    public void update(int noPromotion, Promotions promotions) {
        promotions.setNo_promotion(noPromotion);
        promoRepository.save(promotions);
    }

    @Override
    public void partialUpdate(int noPromotion, Map<String, Object> updates) {
        Promotions promoToUpdate = promoRepository.findById(noPromotion).get();

        for(String key : updates.keySet()) {
            switch (key) {
                case "pourcentage" : {
                    double d = (double) updates.get(key);
                    float f = (float) d;
                    promoToUpdate.setPourcentage(f);
                    break;
                }
                case "date_debut" : {
                    promoToUpdate.setDate_debut((String) updates.get(key));
                    break;
                }
                case "date_fin" : {
                    promoToUpdate.setDate_Fin((String) updates.get(key));
                    break;
                }
            }
        }
        promoRepository.save(promoToUpdate);
    }

    @Override
    public void deleteById(int noProduit, int noPromotion) {
        Produits prodToUpdate = prodRepository.findById(noProduit).get();
        List<Promotions> promotions = prodToUpdate.getPromotions();
        //String stringNoPromo = Integer.toString(noPromotion);
        Promotions promotion = promotions.stream().filter(m -> Objects.equals(m.getNo_promotion(), noPromotion)).findFirst().get(); //Bon ça a l'air de fonctionner
        promotions.remove(promotion); //On delete la relation entre le produit et la promo
        prodToUpdate.setPromotions(promotions); //On re update la liste de promotions
        prodRepository.save(prodToUpdate);
        promoRepository.delete(promotion); //On delete la promo
    }


}
