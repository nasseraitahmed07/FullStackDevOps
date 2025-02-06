package Nasser.AITAHMED.gestionStocke.mappers;

import Nasser.AITAHMED.gestionStocke.models.dto.response.ProduitResponse;
import Nasser.AITAHMED.gestionStocke.models.entity.Produit;
import Nasser.AITAHMED.gestionStocke.models.dto.request.ProduitRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {
    @Mapping(source = "categorie.id", target = "categorieId")
    ProduitResponse toDTO(Produit produit);
    Produit toEntity(ProduitRequest produitRequest);
}