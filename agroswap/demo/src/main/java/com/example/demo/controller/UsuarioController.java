package com.example.demo.controller;

import com.example.demo.Service.UsuarioService;
import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<Object> registrar(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.salvar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodosUsuarios() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.buscarTodos());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizar(id, usuarioAtualizado)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUsuario(@RequestBody Usuario usuarioCliente) {
        String email = usuarioCliente.getEmail();
        String senha = usuarioCliente.getSenha();
        Optional<Usuario> clienteOptional = usuarioService.login(email, senha);
        if (clienteOptional.isPresent()) {
            Usuario cliente = clienteOptional.get();
            return ResponseEntity.ok(Map.of(
                    "clienteId", cliente.getId(),
                    "message", "Login bem-sucedido!"
            ));
        } else {
            System.err.println("Email ou senha incorretos para o email: " + email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos.");
        }
    }
}
