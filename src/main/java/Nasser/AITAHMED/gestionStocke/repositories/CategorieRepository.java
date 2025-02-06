package Nasser.AITAHMED.gestionStocke.repositories;

import Nasser.AITAHMED.gestionStocke.models.entity.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}
