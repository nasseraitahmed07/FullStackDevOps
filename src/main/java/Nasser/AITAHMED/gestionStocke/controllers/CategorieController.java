package Nasser.AITAHMED.gestionStocke.controllers;

import Nasser.AITAHMED.gestionStocke.models.dto.response.CategoryResponse;
import Nasser.AITAHMED.gestionStocke.models.entity.Categorie;
import Nasser.AITAHMED.gestionStocke.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static Nasser.AITAHMED.gestionStocke.utils.Constants.APP_ROOT;
import static Nasser.AITAHMED.gestionStocke.utils.Constants.CATEGORY_ROOT;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(APP_ROOT)
@Tag(name = "Catégories", description = "API de gestion des catégories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategoryService categoryService;

    @GetMapping(CATEGORY_ROOT)
    @Operation(
            summary = "Lister toutes les catégories",
            description = "Cette méthode permet de récupérer la liste des catégories disponibles.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Liste des catégories récupérée avec succès",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
                    @ApiResponse(responseCode = "500", description = "Erreur interne du serveur",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<Page<CategoryResponse>> getAllCategories(Pageable pageable) {
        return ResponseEntity.ok(categoryService.getAllCategories(pageable));
    }


    @GetMapping(CATEGORY_ROOT + "/{id}")
    @Operation(
            summary = "Récupérer une catégorie par ID",
            description = "Cette méthode permet de récupérer une catégorie spécifique à partir de son ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Catégorie trouvée",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CategoryResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée",
                            content = @Content(mediaType = "application/json"))
            }
    )
    public ResponseEntity<Categorie> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategorieById(id));
    }

}
