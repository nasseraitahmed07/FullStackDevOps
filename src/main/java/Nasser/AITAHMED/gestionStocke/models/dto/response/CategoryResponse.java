package Nasser.AITAHMED.gestionStocke.models.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CategoryResponse {
    private Long id;
    private String nom;

}
