package com.bloc3.vitrine_produits_promo.REST.Services;

import com.bloc3.vitrine_produits_promo.Models.Promotions;

import java.util.List;
import java.util.Map;

public interface PromotionsServices {

    public List<Promotions> findAllOfProduits(int no_produit);

    public Promotions findById(int noPromotion);

    public int create(int noProduit, Promotions promotion);

    public void update(int noPromotion, Promotions promotions);

    public void partialUpdate(int noPromotion, Map<String, Object> updates);

    public void deleteById(int noProduit, int noPromotion);
}
