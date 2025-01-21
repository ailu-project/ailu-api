package org.ailu.api.service;

import org.ailu.api.dto.NumeroDTO;
import org.ailu.api.entity.NumeroEntity;
import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.repository.NumeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NumeroService {

    @Autowired
    private NumeroRepository numeroRepository;

    @Autowired
    private UsuarioService usuarioService;

    public NumeroEntity saveNumero(NumeroDTO dto) {
        Optional<UsuarioEntity> optionalUsuario = usuarioService.getUserById(dto.getUsuario_id());
        if (optionalUsuario.isEmpty()) {
            throw new IllegalArgumentException("Usuário com ID " + dto.getUsuario_id() + " não foi encontrado.");
        }
        UsuarioEntity usuarioEntity = optionalUsuario.get();
        NumeroEntity numeroEntity = new NumeroEntity();
        numeroEntity.setNumero(dto.getNumero());
        numeroEntity.setUsuarioEntity(usuarioEntity);
        numeroEntity.setNome(dto.getNome());
        return numeroRepository.save(numeroEntity);
    }

    public List<NumeroEntity> getAllNumeros() {
        return numeroRepository.findAll();
    }
    public Optional<NumeroEntity> getNumeroById(Long id) {
        return numeroRepository.findById(id);
    }

    public List<NumeroEntity> getNumeroByUsuarioId(Long usuarioId) {
        return numeroRepository.findByUsuarioEntity_Id(usuarioId);
    }

    public NumeroEntity updateNumero(Long id, NumeroDTO dto) {
        NumeroEntity numeroEntity = numeroRepository.findById(id).orElseThrow(() -> new RuntimeException("Número não encontrado"));
        if(numeroEntity != null){
            if(dto.getNumero() != null){
                numeroEntity.setNumero(dto.getNumero());
            }
            if(dto.getNome() != null){
                numeroEntity.setNome(dto.getNome());
            }
            return numeroRepository.save(numeroEntity);
        }
        return null;
    }
    public void deleteNumero(Long id) {
        numeroRepository.deleteById(id);
    }
}
