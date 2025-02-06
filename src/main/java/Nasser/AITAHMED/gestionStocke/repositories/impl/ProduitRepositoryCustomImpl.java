package Nasser.AITAHMED.gestionStocke.repositories.impl;

import Nasser.AITAHMED.gestionStocke.models.entity.Produit;
import Nasser.AITAHMED.gestionStocke.repositories.ProduitRepository;
import Nasser.AITAHMED.gestionStocke.repositories.ProduitRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProduitRepositoryCustomImpl implements ProduitRepositoryCustom {

    private final ProduitRepository produitRepository;

    @Override
    public Page<Produit> findByCriteria(Specification<Produit> spec, Pageable pageable) {
        return produitRepository.findAll(spec, pageable);
    }
}
