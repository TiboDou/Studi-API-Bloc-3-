package com.bloc3.vitrine_produits_promo.DAO;

import com.bloc3.vitrine_produits_promo.Models.Produits;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//On étend CrudRepository qui permet d'effectuer les opérations CRUD sur la table passée, Produits dans notre cas
/*Permet donc d'utiliser :
    save() pour enregistrer un nouveau produit ou mettre à jour un produit existant.
    findById() pour rechercher un produit par son identifiant.
    findAll() pour récupérer tous les produits.
    deleteById() pour supprimer un produit par son identifiant, etc.
 */
@Repository
public interface ProduitsRepository extends CrudRepository<Produits, Integer> {

}
