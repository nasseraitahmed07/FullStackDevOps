package Nasser.AITAHMED.gestionStocke.aspects;

import Nasser.AITAHMED.gestionStocke.models.entity.AuditLog;
import Nasser.AITAHMED.gestionStocke.repositories.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AuditingAspect {

    private final AuditLogRepository auditLogRepository;

    @Around("execution(* Nasser.AITAHMED.gestionStocke.services.impl.*.*(..))")
    public Object auditMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String user = getCurrentUser();
        Object result = null;
        String errorMessage = null;

        try {
            result = joinPoint.proceed();
            return result;
        } catch (Exception e) {
            errorMessage = e.getMessage();
            throw e;
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            AuditLog logEntry = AuditLog.builder()
                    .user(user)
                    .methodName(joinPoint.getSignature().getName())
                    .request(Arrays.toString(joinPoint.getArgs()))
                    .response(result != null ? result.toString() : "null")
                    .errorMessage(errorMessage)
                    .executionTime(executionTime)
                    .dateTime(LocalDateTime.now())
                    .build();

            auditLogRepository.save(logEntry);
            log.info("Audit enregistré : {}", logEntry);
        }
    }

    private String getCurrentUser() {
        // TODO: Aprés authentification
        // TODO: Récuperer l'utilisateur connecté
        return "Testeur";
    }
}
