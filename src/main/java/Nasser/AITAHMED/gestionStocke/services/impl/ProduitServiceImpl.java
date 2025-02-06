package Nasser.AITAHMED.gestionStocke.services.impl;

import Nasser.AITAHMED.gestionStocke.exceptions.DuplicateEntityException;
import Nasser.AITAHMED.gestionStocke.exceptions.EntityNotFoundException;
import Nasser.AITAHMED.gestionStocke.mappers.ProduitMapper;
import Nasser.AITAHMED.gestionStocke.models.dto.response.ProduitResponse;
import Nasser.AITAHMED.gestionStocke.models.entity.Categorie;
import Nasser.AITAHMED.gestionStocke.models.entity.Produit;
import Nasser.AITAHMED.gestionStocke.models.dto.request.ProduitRequest;
import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;
import Nasser.AITAHMED.gestionStocke.repositories.CategorieRepository;
import Nasser.AITAHMED.gestionStocke.repositories.ProduitRepository;
import Nasser.AITAHMED.gestionStocke.services.CategoryService;
import Nasser.AITAHMED.gestionStocke.services.ProduitService;
import Nasser.AITAHMED.gestionStocke.specifications.ProduitSpecifications;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitMapper produitMapper;
    private final ProduitRepository produitRepository;
    private final CategorieRepository categorieRepository;
    private final CategoryService categoryService;


    @Override
    public ProduitResponse createProduct(ProduitRequest produitRequest) {

        Optional<Produit> existingProduit = produitRepository.findByNom(produitRequest.getNom());
        if (existingProduit.isPresent()) {
            throw new DuplicateEntityException("Produit existe déja",ErrorCodes.DUPLICATE_PRODUCT_NAME);
        }

        Categorie category = Optional.ofNullable(produitRequest.getCategorieId())
                .map(categoryService::getCategorieById)
                .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée", ErrorCodes.CATEGORY_NOT_FOUND));

        Produit produit = produitMapper.toEntity(produitRequest);
        produit.setCategorie(category);
        log.info("Création d'un nouveau produit avec le nom: {}", produitRequest.getNom());

        Produit savedProduit = produitRepository.save(produit);
        return produitMapper.toDTO(savedProduit);
    }

    @Override
    public Page<ProduitResponse> getAllProducts(Pageable pageable) {
        Page<Produit> produitsPage = produitRepository.findAll(pageable);
        return produitsPage.map(produitMapper::toDTO);
    }

    @Override
    public ProduitResponse getProductById(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'id : " + id, ErrorCodes.PRODUCT_NOT_FOUND));
        return produitMapper.toDTO(produit);
    }

    public Page<ProduitResponse> searchProducts(String nom, Double prixMin, Double prixMax, String categorie, Pageable pageable) {
        log.info("Recherche produits avec les paramètres : nom={}, prixMin={}, prixMax={}, categorie={}", nom, prixMin, prixMax, categorie);

        Specification<Produit> spec = Specification
                .where(ProduitSpecifications.nomSpecification(nom))
                .and(ProduitSpecifications.prixSpecification(prixMin, prixMax))
                .and(ProduitSpecifications.categorieSpecification(categorie));

        Page<Produit> produitsPage = produitRepository.findAll(spec, pageable);
        log.info("Résultats de la recherche : " + produitsPage.getContent());
        return produitsPage.map(produitMapper::toDTO);
    }


    @Override
    public ProduitResponse updateProduct(Long id, ProduitRequest produitRequest) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID du produit ne peut pas être nul");
        }

        Produit existingProduit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'id " + id, ErrorCodes.PRODUCT_NOT_FOUND));

        existingProduit.setNom(produitRequest.getNom());
        existingProduit.setDescription(produitRequest.getDescription());
        existingProduit.setPrix(produitRequest.getPrix());
        existingProduit.setQuantite(produitRequest.getQuantite());

        if (!existingProduit.getCategorie().getId().equals(produitRequest.getCategorieId())) {
            Categorie category = categorieRepository.findById(produitRequest.getCategorieId())
                    .orElseThrow(() -> new EntityNotFoundException("Catégorie non trouvée", ErrorCodes.CATEGORY_NOT_FOUND));
            existingProduit.setCategorie(category);
        }

        return produitMapper.toDTO(produitRepository.save(existingProduit));
    }

    @Override
    public void deleteProduct(Long id) {
        Produit produit = produitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produit non trouvé avec l'id " + id, ErrorCodes.PRODUCT_NOT_FOUND));
        produitRepository.delete(produit);
        log.info("Produit avec l'id {} supprimé", id);
    }

}

