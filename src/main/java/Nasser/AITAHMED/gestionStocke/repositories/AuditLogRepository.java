package Nasser.AITAHMED.gestionStocke.repositories;

import Nasser.AITAHMED.gestionStocke.models.entity.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}
