package com.example.demo.Service;

import com.example.demo.model.Maquina;
import com.example.demo.Repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepository;

    public Maquina salvar(Maquina maquina) {
        return maquinaRepository.save(maquina);
    }

    public List<Maquina> buscarTodos() {

        return maquinaRepository.findAll();
    }
}
