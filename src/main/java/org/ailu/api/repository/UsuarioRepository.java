package org.ailu.api.repository;

import org.ailu.api.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
        UsuarioEntity findByEmail(String email);
        UsuarioEntity findByCpf(String cpf);
}
