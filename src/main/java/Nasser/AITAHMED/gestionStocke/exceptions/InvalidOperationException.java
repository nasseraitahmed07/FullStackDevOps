package Nasser.AITAHMED.gestionStocke.exceptions;

import Nasser.AITAHMED.gestionStocke.models.enums.ErrorCodes;
import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException {

    private final ErrorCodes errorCode;

    public InvalidOperationException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidOperationException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
