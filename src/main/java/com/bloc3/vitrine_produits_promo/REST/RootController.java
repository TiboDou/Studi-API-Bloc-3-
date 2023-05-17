package com.bloc3.vitrine_produits_promo.REST;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//@HATEOAS
@RestController
public class RootController {

    //Méthode qui renvoie le lien vers la liste des produits
    @GetMapping("/")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void rootUri(final HttpServletRequest request, final HttpServletResponse response) {

        String rootUri = request.getRequestURL().toString(); //On récupère l'URL

        response.addHeader("Lien","<" + rootUri + "produits>; rel=\"produits\"");
    }
}
