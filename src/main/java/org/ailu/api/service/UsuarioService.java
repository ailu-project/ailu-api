package org.ailu.api.service;

import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioEntity saveUser(UsuarioEntity usuarioEntity) {
            return usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> getAllUsers() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioEntity> getUserById(Long id) {
        return usuarioRepository.findById(id);
    }

    public UsuarioEntity getUserByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioEntity getUserByCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf);
    }

    public UsuarioEntity updateUser(Long id, UsuarioEntity usuarioEntityDetails) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioEntity.update(usuarioEntityDetails);
        return usuarioRepository.save(usuarioEntity);
    }

    public void deleteUser(Long id) {
        usuarioRepository.deleteById(id);
    }

    public void desativarUser(Long id) {
        UsuarioEntity usuarioEntity = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioEntity.setAtivo(false);
        usuarioRepository.save(usuarioEntity);
    }
}
