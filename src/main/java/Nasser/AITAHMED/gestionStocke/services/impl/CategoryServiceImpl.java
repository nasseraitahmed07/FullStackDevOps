package Nasser.AITAHMED.gestionStocke.services.impl;


import Nasser.AITAHMED.gestionStocke.exceptions.EntityNotFoundException;
import Nasser.AITAHMED.gestionStocke.mappers.CategoryMapper;
import Nasser.AITAHMED.gestionStocke.models.dto.response.CategoryResponse;
import Nasser.AITAHMED.gestionStocke.models.entity.Categorie;
import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;
import Nasser.AITAHMED.gestionStocke.repositories.CategorieRepository;
import Nasser.AITAHMED.gestionStocke.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategorieRepository categorieRepository;

    @Override
    public Page<CategoryResponse> getAllCategories(Pageable pageable) {
        Page<Categorie> categoriesPage = categorieRepository.findAll(pageable);
        return categoriesPage.map(categoryMapper::toDTO);
    }
    @Override
    public Categorie getCategorieById(Long categorieId) {
        return categorieRepository.findById(categorieId)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée avec l'id : " + categorieId, ErrorCodes.CATEGORY_NOT_FOUND));
    }

}
