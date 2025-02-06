package Nasser.AITAHMED.gestionStocke.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* Nasser.AITAHMED.gestionStocke.services.impl.*.*(..))")
    public void logMethodCall(JoinPoint joinPoint) {
        log.info("Appel de la méthode : {} avec les paramètres : {}",
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "execution(* Nasser.AITAHMED.gestionStocke.services.impl.*.*(..))", returning = "result")
    public void logMethodReturn(JoinPoint joinPoint, Object result) {
        log.info("Méthode : {} a retourné : {}", joinPoint.getSignature().getName(), result);
    }

    @AfterThrowing(value = "execution(* Nasser.AITAHMED.gestionStocke.services.impl.*.*(..))", throwing = "exception")
    public void logMethodException(JoinPoint joinPoint, Throwable exception) {
        log.error("Une exception a été lancée dans la méthode : {} avec l'exception : {}",
                joinPoint.getSignature().getName(), exception.getMessage());
    }
}

