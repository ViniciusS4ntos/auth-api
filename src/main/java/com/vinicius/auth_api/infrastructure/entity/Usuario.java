package com.vinicius.auth_api.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", length = 90)
    private String email;

    @Column(name = "senha")
    private String senha;

}
