package Nasser.AITAHMED.gestionStocke.services;

import Nasser.AITAHMED.gestionStocke.models.dto.request.ProduitRequest;
import Nasser.AITAHMED.gestionStocke.models.dto.response.ProduitResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProduitService {

    ProduitResponse createProduct(ProduitRequest produitRequest);

    Page<ProduitResponse> getAllProducts(Pageable pageable);

    ProduitResponse getProductById(Long id);

    ProduitResponse updateProduct(Long id, ProduitRequest produitRequest);

    Page<ProduitResponse> searchProducts(String nom, Double prixMin, Double prixMax, String categorie, Pageable pageable);

    void deleteProduct(Long id);


}
