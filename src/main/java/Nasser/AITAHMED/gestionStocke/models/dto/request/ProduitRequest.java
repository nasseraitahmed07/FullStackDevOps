package Nasser.AITAHMED.gestionStocke.models.dto.request;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProduitRequest {
    private String nom;
    private String description;
    private Double prix;
    private Integer quantite;
    private Long categorieId;

}
