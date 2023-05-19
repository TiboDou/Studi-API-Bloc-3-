package com.bloc3.vitrine_produits_promo.REST;

import com.bloc3.vitrine_produits_promo.Models.Produits;
import com.bloc3.vitrine_produits_promo.REST.Services.ProduitsServices;
import com.bloc3.vitrine_produits_promo.util.SiExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/produits")
//@CrossOrigin(origins ="https://studi-bloc3-front-td.herokuapp.com")
public class ProduitsController {

    @Autowired //
    private ProduitsServices prodService;

    @GetMapping
    public List<Produits> selectProduits() {
        List<Produits> produits = prodService.selectProduits();

        for (Produits produit : produits) {
            Link selfLink = WebMvcLinkBuilder.linkTo(ProduitsController.class).slash(produit.getNo_produit()).withSelfRel();
            produit.add(selfLink);
        }

        return prodService.selectProduits();
    }

    @GetMapping("/{no_produit}")
    public Produits findById(@PathVariable("no_produit") int no_produit) {
        Produits reponse =  prodService.findById(no_produit);
        SiExiste.checkFound(reponse);
        return reponse;
    }


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@RequestBody Produits produit) {
        
        return prodService.create(produit);
    }

    @PutMapping("/{no_produit}")
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("no_produit") int no_produit, @RequestBody Produits produit) {
        SiExiste.checkFound(prodService.findById(no_produit));
        prodService.update(no_produit, produit);
    }

    @PatchMapping("/{no_produit}")
    @ResponseStatus(code = HttpStatus.OK)
    public void partialUpdate(@PathVariable("no_produit") int no_produit, @RequestBody Map<String, Object> updates) {
        SiExiste.checkFound(prodService.findById(no_produit));
        prodService.partialUpdate(no_produit, updates);
    }

    @DeleteMapping("/{no_produit}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable("no_produit") int no_produit) {
        SiExiste.checkFound(prodService.findById(no_produit)); //Méthode qui permet de savoir si le produit qu'on cherche existe dans la bdd
        prodService.deleteById(no_produit);
    }
}
