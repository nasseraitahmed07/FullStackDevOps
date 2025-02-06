package Nasser.AITAHMED.gestionStocke.controllers;

import Nasser.AITAHMED.gestionStocke.exceptions.EntityNotFoundException;
import Nasser.AITAHMED.gestionStocke.models.dto.request.ProduitRequest;
import Nasser.AITAHMED.gestionStocke.models.dto.response.ProduitResponse;
import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;
import Nasser.AITAHMED.gestionStocke.services.ProduitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProduitControllerTest {

    @Mock
    private ProduitService produitService;

    @InjectMocks
    private ProduitController produitController;

    @Test
    void shouldCreateProductAndReturn201() {
        // Given
        ProduitRequest request = ProduitRequest.builder()
                .nom("Test Product")
                .description("Test Description")
                .prix(99.99)
                .quantite(10)
                .categorieId(1L)
                .build();

        ProduitResponse expectedResponse = ProduitResponse.builder()
                .nom("Test Product")
                .description("Test Description")
                .prix(99.99)
                .quantite(10)
                .categorieId(1L)
                .build();

        when(produitService.createProduct(request)).thenReturn(expectedResponse);

        // When
        ResponseEntity<ProduitResponse> response = produitController.createProduit(request);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
        verify(produitService).createProduct(request);
    }

    @Test
    void shouldReturn400WhenCreatingProductWithMissingFields() {
        // Given
        ProduitRequest invalidRequest = ProduitRequest.builder()
                .description("Test Description")
                .prix(99.99)
                .build();

        when(produitService.createProduct(invalidRequest))
                .thenThrow(new IllegalArgumentException("Missing required fields"));

        // When & Then
        assertThatThrownBy(() -> produitController.createProduit(invalidRequest))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Missing required fields");

        verify(produitService).createProduct(invalidRequest);
    }

    @Test
    void shouldReturnPaginatedListOfProducts() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProduitResponse> mockPage = new PageImpl<>(List.of(new ProduitResponse()));

        when(produitService.getAllProducts(pageable)).thenReturn(mockPage);

        // When
        ResponseEntity<Page<ProduitResponse>> response = produitController.getAllProduits(pageable);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockPage);
    }

    @Test
    void shouldReturnProductById() {
        // Given
        Long productId = 1L;
        ProduitResponse mockProduct = new ProduitResponse("Product1", "Description1", 100.0, 10, 1L);

        when(produitService.getProductById(productId)).thenReturn(mockProduct);

        // When
        ResponseEntity<ProduitResponse> response = produitController.getProduitById(productId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockProduct);
    }


    @Test
    void shouldSearchProductsWithValidParameters() {
        // Given
        String nom = "Product";
        Double prixMin = 50.0;
        Double prixMax = 150.0;
        String categorie = "Category1";
        Pageable pageable = PageRequest.of(0, 10);
        Page<ProduitResponse> mockPage = new PageImpl<>(List.of(new ProduitResponse()));

        when(produitService.searchProducts(nom, prixMin, prixMax, categorie, pageable)).thenReturn(mockPage);

        // When
        ResponseEntity<Page<ProduitResponse>> response = produitController.searchProducts(nom, prixMin, prixMax, categorie, pageable);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(mockPage);
    }

    @Test
    void shouldUpdateProductAndReturnUpdatedData() {
        // Given
        Long productId = 1L;
        ProduitRequest produitRequest = new ProduitRequest( "Updated Product", "Updated Description", 99.99, 10, 1L);
        ProduitResponse expectedResponse = new ProduitResponse("Updated Product", "Updated Description", 99.99, 10, 1L);

        when(produitService.updateProduct(productId, produitRequest)).thenReturn(expectedResponse);

        // When
        ResponseEntity<ProduitResponse> response = produitController.updateProduit(productId, produitRequest);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedResponse);
    }

    @Test
    void shouldDeleteProductAndReturn204() {
        // Given
        Long productId = 1L;
        doNothing().when(produitService).deleteProduct(productId);

        // When
        ResponseEntity<Void> response = produitController.deleteProduit(productId);

        // Then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(response.getBody()).isNull();
    }

    @Test
    void shouldReturn404WhenProductNotFound() {
        // Given
        Long nonExistentId = 999L;
        when(produitService.getProductById(nonExistentId))
                .thenThrow(new EntityNotFoundException("Product not found", ErrorCodes.PRODUCT_NOT_FOUND));

        // When & Then
        assertThatThrownBy(() -> produitController.getProduitById(nonExistentId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product not found")
                .extracting("errorCode")
                .isEqualTo(ErrorCodes.PRODUCT_NOT_FOUND);
    }

}