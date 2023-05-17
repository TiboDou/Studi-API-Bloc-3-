package com.bloc3.vitrine_produits_promo.REST.Services;
import com.bloc3.vitrine_produits_promo.Models.Produits;

import java.util.List;
import java.util.Map;


public interface ProduitsServices {
    public List<Produits> selectProduits();
    public Produits findById(int no_produit);

    public int create(Produits produit);

    public void update(int noProduit, Produits produit);

    public void partialUpdate(int noProduit, Map<String, Object> updates);

    public void deleteById(int noProduit);
}
