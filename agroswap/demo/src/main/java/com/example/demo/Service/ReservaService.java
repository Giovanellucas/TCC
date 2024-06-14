package com.example.demo.Service;

import com.example.demo.model.Reserva;
import com.example.demo.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva salvar(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> buscarTodos() {
        return reservaRepository.findAll();
    }
}