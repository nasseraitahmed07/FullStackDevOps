package Nasser.AITAHMED.gestionStocke.services;

import Nasser.AITAHMED.gestionStocke.models.dto.response.CategoryResponse;
import Nasser.AITAHMED.gestionStocke.models.entity.Categorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CategoryService {

    Page<CategoryResponse> getAllCategories(Pageable pageable);

    Categorie getCategorieById(Long categorieId);
}
