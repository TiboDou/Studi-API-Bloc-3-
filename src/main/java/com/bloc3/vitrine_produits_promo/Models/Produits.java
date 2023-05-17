package com.bloc3.vitrine_produits_promo.Models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Entity //L'annotation @Entity indique qu'une classe représente une table dans une base de données
public class Produits extends RepresentationModel<Produits> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no_produit;
    private int no_categorie;
    private String libelle;
    private String description;
    private float prix;
    private String url_img;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Promotions> promotions;

    public int getNo_produit() {
        return no_produit;
    }

    public void setNo_produit(int no_produit) {
        this.no_produit = no_produit;
    }

    public int getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(int no_categorie) {
        this.no_categorie = no_categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getUrl_img() {
        return url_img;
    }

    public void setImg_url(String url_img) {
        this.url_img = url_img;
    }


    public List<Promotions> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotions> promotions) {
        this.promotions = promotions;
    }
}
