package Nasser.AITAHMED.gestionStocke.exceptions;

import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class InvalidEntityException extends RuntimeException {

    private final ErrorCodes errorCode;
    private final List<String> errors;

    public InvalidEntityException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.errors = new ArrayList<>();
    }

    public InvalidEntityException(String message, ErrorCodes errorCode, List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }

    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errors = new ArrayList<>();
    }
}


