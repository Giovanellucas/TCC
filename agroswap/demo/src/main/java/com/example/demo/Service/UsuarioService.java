package com.example.demo.Service;

import com.example.demo.Repository.UsuarioRepository;
import com.example.demo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {
        Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());
        if (existente.isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Optional<Usuario> atualizar(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    if (!usuarioExistente.getSenha().equals(usuarioAtualizado.getSenha())) {
                        usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha()));
                    }
                    return usuarioRepository.save(usuarioExistente);
 });
    }

    public Optional<Usuario> login(String email, String senha) {
        Optional<Usuario> clienteOptional = usuarioRepository.findByEmail(email);
        if (clienteOptional.isPresent()) {
            Usuario cliente = clienteOptional.get();
            if (passwordEncoder.matches(senha, cliente.getSenha())) {
                return clienteOptional;
            }
        }
        return Optional.empty();
    }
}
