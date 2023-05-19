package com.bloc3.vitrine_produits_promo.REST;

import com.bloc3.vitrine_produits_promo.Models.Produits;
import com.bloc3.vitrine_produits_promo.REST.Services.ProduitsServices;
import com.bloc3.vitrine_produits_promo.util.SiExiste;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/produits")
@Tag(name = "Produits", description = "Endpoint pour la gestion des produits")
public class ProduitsController {

    @Autowired //
    private ProduitsServices prodService;

    @GetMapping
    @Operation(summary = "Récupère tous les produits")
    public List<Produits> selectProduits() {
        List<Produits> produits = prodService.selectProduits();
        for (Produits produit : produits) {
            Link selfLink = WebMvcLinkBuilder.linkTo(ProduitsController.class).slash(produit.getNo_produit()).withSelfRel();
            produit.add(selfLink);
        }
        return prodService.selectProduits();
    }

    @GetMapping("/{no_produit}")
    @Operation(summary = "Permet de récupérer un produit via son ID")
    @ApiResponse(responseCode = "200", description = "Le produit a été trouvé", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produits.class)))
    public ResponseEntity<String> findById(@PathVariable("no_produit") int no_produit) {
        Produits reponse =  prodService.findById(no_produit);

        if (reponse != null) {
            return ResponseEntity.ok(String.valueOf(prodService.findById(no_produit)));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Le produit avec l'ID " + no_produit + " n'existe pas");
        }
    }


    @PostMapping
    @Operation(summary = "Permet de créer un nouveau produit")
    @ApiResponse(responseCode = "201", description = "Le produit a été créé", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produits.class)))
    @ResponseStatus(code = HttpStatus.CREATED)
    public int create(@RequestBody Produits produit) {
        return prodService.create(produit);
    }

    @PutMapping("/{no_produit}")
    @Operation(summary = "Permet de mettre à jouer un produit via son ID")
    @ApiResponse(responseCode = "200", description = "Le produit a été modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produits.class)))
    @ResponseStatus(code = HttpStatus.OK)
    public void update(@PathVariable("no_produit") int no_produit, @RequestBody Produits produit) {
        SiExiste.checkFound(prodService.findById(no_produit));
        prodService.update(no_produit, produit);
    }

    @PatchMapping("/{no_produit}")
    @Operation(summary = "Permet de mettre à jour partiellement un produit via son ID")
    @ApiResponse(responseCode = "200", description = "Le produit a été modifié", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produits.class)))
    @ResponseStatus(code = HttpStatus.OK)
    public void partialUpdate(@PathVariable("no_produit") int no_produit, @RequestBody Map<String, Object> updates) {
        SiExiste.checkFound(prodService.findById(no_produit));
        prodService.partialUpdate(no_produit, updates);
    }

    @DeleteMapping("/{no_produit}")
    @Operation(summary = "Permet de supprimer un produit via son ID")
    @ApiResponse(responseCode = "200", description = "Le produit a été supprimé", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Produits.class)))
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteById(@PathVariable("no_produit") int no_produit) {
        SiExiste.checkFound(prodService.findById(no_produit)); //Méthode qui permet de savoir si le produit qu'on cherche existe dans la bdd
        prodService.deleteById(no_produit);
    }
}
