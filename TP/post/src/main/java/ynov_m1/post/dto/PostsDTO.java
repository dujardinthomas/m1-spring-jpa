package ynov_m1.post.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PostsDTO {
    private Long id;

    @NotNull(message = "Le titre ne doit pas être vide")
    @NotBlank(message = "Le titre ne doit pas être vide")
    private String title;

    @NotNull(message = "La description ne doit pas être vide")
    @NotBlank(message = "La description ne doit pas être vide")
    private String description;
    
    private String content;
    private LocalDate createdAt;
}
