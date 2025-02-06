package Nasser.AITAHMED.gestionStocke.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "produit")
public class Produit {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Le nom ne peut pas être vide")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    @Column(name = "nom", nullable = false, unique = true)
    private String nom;


    @NotNull(value = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être supérieur à 0")
    @Column(name = "prix", nullable = false)
    private Double prix;

    @Min(value = 0, message = "La quantité ne peut pas être négative")
    @NotNull(value = "La quantité est obligatoire")
    @Column(name = "quantite")
    private Integer quantite;


    @Size(max = 255, message = "La description ne doit pas dépasser 255 caractères")
    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;


}
