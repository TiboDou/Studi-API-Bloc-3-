package com.bloc3.vitrine_produits_promo.REST;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-controller")
@Tag(name = "Test API", description = "Endpoint pour vérifier rapidement si l'API est bien déployée et accessible")
public class TestController {

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("L'API semble être correctement déployée, bravo !");
    }
}
