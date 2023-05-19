package com.bloc3.vitrine_produits_promo.util;

import com.bloc3.vitrine_produits_promo.Exceptions.ResourceNotFoundException;

//Evite la redondance

public final class SiExiste {

    public static <T> T checkFound(T objet) {
        if(objet == null) {
            throw new ResourceNotFoundException();
        }
        return objet;
    }
}
