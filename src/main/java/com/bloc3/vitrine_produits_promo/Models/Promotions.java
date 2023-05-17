package com.bloc3.vitrine_produits_promo.Models;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Promotions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no_promotion;

    @ManyToOne
    @JoinColumn(name ="no_produit")
    private Produits produit;

    private float pourcentage;
    private String date_debut;
    private String date_fin;

    public int getNo_promotion() {
        return no_promotion;
    }

    public void setNo_promotion(int no_promotion) {
        this.no_promotion = no_promotion;
    }

    public Produits getProduits() {
        return produit;
    }

    public void setProduits(Produits produit) {
        this.produit = produit;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_Fin() {
        return date_fin;
    }

    public void setDate_Fin(String date_Fin) {
        this.date_fin = date_Fin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promotions that = (Promotions) o;
        return Float.compare(that.pourcentage, pourcentage) == 0 && Objects.equals(date_debut, that.date_debut) && Objects.equals(date_fin, that.date_fin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pourcentage, date_debut, date_fin);
    }


}
