package org.ailu.api.service.usuario;

import org.ailu.api.entity.usuario.Usuario;
import org.ailu.api.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario saveUser(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario getUserByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario getUserByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public Usuario updateUser(Long id, Usuario usuarioDetails) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.update(usuarioDetails);
        return usuarioRepository.save(usuario);
    }

    public void deleteUser(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void desativarUser(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }
}
