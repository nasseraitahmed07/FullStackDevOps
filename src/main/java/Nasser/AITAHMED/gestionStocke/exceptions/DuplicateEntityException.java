package Nasser.AITAHMED.gestionStocke.exceptions;

import lombok.Getter;
import java.util.List;
import java.util.ArrayList;

import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;

@Getter
public class DuplicateEntityException extends RuntimeException {

    private final ErrorCodes errorCode; 
    private final List<String> errors;

    public DuplicateEntityException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errors = new ArrayList<>();
    }

    public DuplicateEntityException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public DuplicateEntityException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errors = new ArrayList<>();
    }

}
