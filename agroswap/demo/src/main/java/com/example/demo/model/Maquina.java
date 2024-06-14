package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nome;

    @Column(name = "FOTO", nullable = false, columnDefinition = "LONGTEXT")
    private String imagem;

    @NotEmpty
    private String localizacao;

    @NotEmpty
    private String preco;

    @NotEmpty
    private String marca;

    @NotEmpty
    private String especificacoes;

    @NotEmpty
    private String contato;

    private String notifications;

}