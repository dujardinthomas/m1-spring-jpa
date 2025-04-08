package ynov_m1.post.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentsDTO {
    private Long id;
    private Long postId;

    @NotNull(message = "Le commentaire ne doit pas être vide")
    private String body;

    @NotNull(message = "L'email ne doit pas être vide")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.net$", message = "l'email doit respecter l'extension .net ")
    private String email;

    @NotNull(message = "Le nom ne doit pas être vide")
    @Size(min = 4, message = "Le nom doit être supérieur à 3 caractères")
    private String name;


    private LocalDate createdAt;
}
