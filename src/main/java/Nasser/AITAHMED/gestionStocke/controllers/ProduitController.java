package Nasser.AITAHMED.gestionStocke.controllers;

import Nasser.AITAHMED.gestionStocke.models.dto.request.ProduitRequest;
import Nasser.AITAHMED.gestionStocke.models.dto.response.ProduitResponse;
import Nasser.AITAHMED.gestionStocke.services.ProduitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import static Nasser.AITAHMED.gestionStocke.utils.Constants.APP_ROOT;
import static Nasser.AITAHMED.gestionStocke.utils.Constants.PRODUCT_ROOT;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(APP_ROOT)
@Tag(name = "Produits", description = "API de gestion des produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @PostMapping(PRODUCT_ROOT)
    @Operation(
            summary = "Créer un nouveau produit",
            description = "Cette méthode permet d'ajouter un nouveau produit à la base de données.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produit créé avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProduitResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Données invalides fournies",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<ProduitResponse> createProduit(@Valid @RequestBody ProduitRequest produitRequest) {
        ProduitResponse produit = produitService.createProduct(produitRequest);
        return new ResponseEntity<>(produit, HttpStatus.CREATED);
    }

    @GetMapping(PRODUCT_ROOT)
    @Operation(
            summary = "Lister tous les produits",
            description = "Cette méthode permet de récupérer la liste des produits disponibles.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des produits récupérée avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<Page<ProduitResponse>> getAllProduits(Pageable pageable) {
        return ResponseEntity.ok(produitService.getAllProducts(pageable));
    }

    @GetMapping(PRODUCT_ROOT + "/{id}")
    @Operation(
            summary = "Récupérer un produit par ID",
            description = "Cette méthode permet de récupérer un produit spécifique à partir de son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produit trouvé",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProduitResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<ProduitResponse> getProduitById(@PathVariable Long id) {
        return ResponseEntity.ok(produitService.getProductById(id));
    }

    @GetMapping(PRODUCT_ROOT + "/search")
    @Operation(
            summary = "Rechercher des produits",
            description = "Permet de rechercher des produits selon plusieurs critères optionnels.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Résultats de la recherche récupérés avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProduitResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Requête invalide",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    public ResponseEntity<Page<ProduitResponse>> searchProducts(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Double prixMin,
            @RequestParam(required = false) Double prixMax,
            @RequestParam(required = false) String categorie,
            Pageable pageable) {

        Page<ProduitResponse> produits = produitService.searchProducts(nom, prixMin, prixMax, categorie, pageable);
        return ResponseEntity.ok(produits);
    }


    @PutMapping(PRODUCT_ROOT + "/{id}")
    @Operation(
            summary = "Mettre à jour un produit",
            description = "Cette méthode permet de modifier un produit existant.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Produit mis à jour avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProduitResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<ProduitResponse> updateProduit(@PathVariable Long id, @RequestBody ProduitRequest produitRequest) {
        return ResponseEntity.ok(produitService.updateProduct(id, produitRequest));
    }

    @DeleteMapping(PRODUCT_ROOT + "/{id}")
    @Operation(
            summary = "Supprimer un produit",
            description = "Cette méthode permet de supprimer un produit de la base de données.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
                    @ApiResponse(responseCode = "404", description = "Produit non trouvé",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}


