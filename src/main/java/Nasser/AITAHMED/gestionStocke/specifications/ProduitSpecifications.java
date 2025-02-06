package Nasser.AITAHMED.gestionStocke.specifications;

import Nasser.AITAHMED.gestionStocke.models.entity.Produit;
import org.springframework.data.jpa.domain.Specification;

public class ProduitSpecifications {

    public static Specification<Produit> nomSpecification(String nom) {
        return (root, query, criteriaBuilder) -> {
            if (nom == null || nom.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("nom")), "%" + nom.trim().toLowerCase() + "%");
        };
    }

    public static Specification<Produit> prixSpecification(Double prixMin, Double prixMax) {
        return (root, query, criteriaBuilder) -> {
            if (prixMin != null && prixMax != null) {
                return criteriaBuilder.between(root.get("prix"), prixMin, prixMax);
            } else if (prixMin != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("prix"), prixMin);
            } else if (prixMax != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("prix"), prixMax);
            }
            return null;
        };
    }

    public static Specification<Produit> categorieSpecification(String categorie) {
        return (root, query, criteriaBuilder) -> {
            if (categorie == null || categorie.isEmpty()) {
                return null;
            }
            return criteriaBuilder.equal(root.get("categorie").get("id"), Long.parseLong(categorie));
        };
    }

}
