package Nasser.AITAHMED.gestionStocke.repositories;

import Nasser.AITAHMED.gestionStocke.models.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface ProduitRepositoryCustom {
    Page<Produit> findByCriteria(Specification<Produit> spec, Pageable pageable);
}