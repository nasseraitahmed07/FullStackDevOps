package Nasser.AITAHMED.gestionStocke.models.dto;

import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;
import lombok.Builder;

import java.util.List;

@Builder
public  record ErrorDto (
        Integer httpCode,
        ErrorCodes code,
        String message,
        List<String> errors

) {}
