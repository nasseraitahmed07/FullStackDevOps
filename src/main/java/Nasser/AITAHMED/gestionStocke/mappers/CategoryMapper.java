package Nasser.AITAHMED.gestionStocke.mappers;

import Nasser.AITAHMED.gestionStocke.models.dto.response.CategoryResponse;
import Nasser.AITAHMED.gestionStocke.models.entity.Categorie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toDTO(Categorie categorie);
}
