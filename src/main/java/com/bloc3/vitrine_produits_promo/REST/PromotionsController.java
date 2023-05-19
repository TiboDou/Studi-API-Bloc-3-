package com.bloc3.vitrine_produits_promo.REST;

import com.bloc3.vitrine_produits_promo.Models.Promotions;
import com.bloc3.vitrine_produits_promo.REST.Services.ProduitsServices;
import com.bloc3.vitrine_produits_promo.REST.Services.PromotionsServices;
import com.bloc3.vitrine_produits_promo.util.SiExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins ="https://studi-bloc3-front-td.herokuapp.com")
public class PromotionsController {

    @Autowired
    public PromotionsServices promoServices;

    @Autowired
    public ProduitsServices prodServices;

    @GetMapping("/produits/{no_produit}/promotions")
    public List<Promotions> findAllOfProduits(@PathVariable("no_produit") int no_produit) {
        SiExiste.checkFound(prodServices.findById(no_produit));
        return promoServices.findAllOfProduits(no_produit);
    }

    @GetMapping("/promotions/{no_promotion}")
    public Promotions findById(@PathVariable("no_promotion") int no_promotion) {
        Promotions reponse = promoServices.findById(no_promotion);
        SiExiste.checkFound(reponse);
        return reponse;
    }

    @PostMapping("/produits/{no_produit}/promotions")
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@PathVariable("no_produit") int no_produit,@RequestBody Promotions promotion) {
        SiExiste.checkFound(prodServices.findById(no_produit));
        return promoServices.create(no_produit, promotion);
    }

    @PutMapping("/promotions/{no_promotion}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("no_promotion") int no_promotion, @RequestBody Promotions promotions) {
        SiExiste.checkFound(promoServices.findById(no_promotion));

        promoServices.update(no_promotion, promotions);
    }

    @PatchMapping("/promotions/{no_promotion}")
    @ResponseStatus(code = HttpStatus.OK)
    public void partialUpdate(@PathVariable("no_promotion") int no_promotion, @RequestBody Map<String, Object> updates) {
        SiExiste.checkFound(promoServices.findById(no_promotion));

        promoServices.partialUpdate(no_promotion, updates);
    }

    @DeleteMapping("/produits/{no_produit}/promotions/{no_promotion}")
    public void delete(@PathVariable("no_produit") int no_produit, @PathVariable("no_promotion") int no_promotion) {
        SiExiste.checkFound(prodServices.findById(no_produit));
        SiExiste.checkFound(promoServices.findById(no_promotion));

        promoServices.deleteById(no_produit, no_promotion);
    }
}
