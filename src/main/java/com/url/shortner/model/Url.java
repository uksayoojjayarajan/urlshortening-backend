package com.url.shortner.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String originalUrl;

    @Column(nullable = false, unique = true, length = 10)
    private String shortUrl;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = true, length = 50)
    private String category;
}
