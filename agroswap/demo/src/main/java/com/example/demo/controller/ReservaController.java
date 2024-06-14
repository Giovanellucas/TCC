package com.example.demo.controller;
import com.example.demo.model.Reserva;
import com.example.demo.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {
    @Autowired
    private ReservaService reservaService;

    @PostMapping("/reservar")
    public Reserva reservarMaquina(@RequestBody Reserva reserva) {
        return reservaService.salvar(reserva);
    }

    @GetMapping
    public List<Reserva> listarTodasReservas() {
        return reservaService.buscarTodos();
    }
}
