package com.bloc3.vitrine_produits_promo.DAO;

import com.bloc3.vitrine_produits_promo.Models.Promotions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionsRepository extends CrudRepository<Promotions, Integer> {
}
