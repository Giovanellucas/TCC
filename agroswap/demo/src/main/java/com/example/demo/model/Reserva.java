package com.example.demo.model;

import java.time.LocalDateTime;
import com.example.demo.model.Maquina;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Maquina maquina;

    private LocalDateTime horarioReserva;
}
