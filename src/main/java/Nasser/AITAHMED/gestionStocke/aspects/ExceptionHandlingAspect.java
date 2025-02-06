package Nasser.AITAHMED.gestionStocke.aspects;

import Nasser.AITAHMED.gestionStocke.exceptions.EntityNotFoundException;
import Nasser.AITAHMED.gestionStocke.exceptions.InvalidEntityException;
import Nasser.AITAHMED.gestionStocke.exceptions.InvalidOperationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlingAspect {

    @Around("execution(* Nasser.AITAHMED.gestionStocke.services.impl.*.*(..))")
    public Object handleException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (EntityNotFoundException ex) {
            log.error("Exception dans {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
            throw new EntityNotFoundException("Erreur de l'entité : " + ex.getMessage(), ex.getErrorCode());
        } catch (InvalidOperationException ex) {
            log.error("Erreur d'opération dans {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
            throw new InvalidOperationException("Opération invalide : " + ex.getMessage(), ex.getErrorCode());
        } catch (InvalidEntityException ex) {
            log.error("Erreur d'entité invalide dans {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
            throw new InvalidEntityException("Entité invalide : " + ex.getMessage(), ex.getErrorCode(), ex.getErrors());
        } catch (Exception ex) {
            log.error("Exception générale dans {}: {}", joinPoint.getSignature().getName(), ex.getMessage());
            throw new RuntimeException("Une erreur est survenue dans la méthode " + joinPoint.getSignature().getName(), ex);
        }
    }
}

