package Nasser.AITAHMED.gestionStocke.repositories;

import Nasser.AITAHMED.gestionStocke.models.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>, JpaSpecificationExecutor<Produit> {

    Optional<Produit> findByNom(String nom);
}
