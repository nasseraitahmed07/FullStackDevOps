package Nasser.AITAHMED.gestionStocke.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "error_message", length = 500)
    @Size(max = 500, message = "Le message d'erreur ne doit pas dépasser 500 caractères.")
    private String errorMessage;

    @Column(name = "execution_time", nullable = false)
    @NotNull(value = "Le temps d'exécution est obligatoire.")
    private Long executionTime;

    @Column(name = "method_name", nullable = false, length = 255)
    @NotBlank(message = "Le nom de la méthode est obligatoire.")
    @Size(max = 255, message = "Le nom de la méthode ne doit pas dépasser 255 caractères.")
    private String methodName;

    @Column(name = "request", length = 1000)
    @Size(max = 1000, message = "La requête ne doit pas dépasser 1000 caractères.")
    private String request;

    @Column(name = "response", length = 2000)
    @Size(max = 2000, message = "La réponse ne doit pas dépasser 2000 caractères.")
    private String response;

    @Column(name = "execution_date", nullable = false, updatable = false)
    @NotNull(value = "Le timestamp est obligatoire.")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateTime;

    @Column(name = "username", nullable = false, length = 255)
    @NotBlank(message = "Le nom d'utilisateur est obligatoire.")
    @Size(max = 255, message = "Le nom d'utilisateur ne doit pas dépasser 255 caractères.")
    private String user;


}