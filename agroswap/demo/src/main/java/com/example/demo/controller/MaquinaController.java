package com.example.demo.controller;

import com.example.demo.model.Maquina;
import com.example.demo.Service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maquinas")
public class MaquinaController {
    @Autowired
    private MaquinaService maquinaService;

    @PostMapping("/adicionar")
    public Maquina adicionarMaquina(@RequestBody Maquina maquina) {
        return maquinaService.salvar(maquina);
    }

    @GetMapping
    public List<Maquina> listarTodasMaquinas() {
        return maquinaService.buscarTodos();
    }
}
