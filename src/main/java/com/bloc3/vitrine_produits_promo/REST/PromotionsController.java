package com.bloc3.vitrine_produits_promo.REST;

import com.bloc3.vitrine_produits_promo.Models.Produits;
import com.bloc3.vitrine_produits_promo.Models.Promotions;
import com.bloc3.vitrine_produits_promo.REST.Services.ProduitsServices;
import com.bloc3.vitrine_produits_promo.REST.Services.PromotionsServices;
import com.bloc3.vitrine_produits_promo.util.SiExiste;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Promotions", description = "Endpoint pour la gestion des promotions")
public class PromotionsController {

    @Autowired
    public PromotionsServices promoServices;

    @Autowired
    public ProduitsServices prodServices;

    @GetMapping("/produits/{no_produit}/promotions")
    @Operation(summary = "Permet de récupérer toutes les promotions d'un produit")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des promotions récupérée"),
            @ApiResponse(responseCode = "404", description = "Le produit n'a pas été trouvé")
    })
    public ResponseEntity<List<Promotions>> findAllOfProduits(@PathVariable("no_produit") int no_produit) {
        Produits produit = prodServices.findById(no_produit);
        if (produit != null) {
            List<Promotions> promotions = promoServices.findAllOfProduits(no_produit);
            return ResponseEntity.ok(promoServices.findAllOfProduits(no_produit));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/promotions/{no_promotion}")
    @Operation(summary = "Permet de récupérer une promotion via son ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La promotion a été trouvée"),
            @ApiResponse(responseCode = "404", description = "La promotion n'a pas été trouvée")
    })
    public ResponseEntity<Promotions> findById(@PathVariable("no_promotion") int no_promotion) {
        Promotions reponse = promoServices.findById(no_promotion);
        if (reponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(reponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reponse);
        }
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
