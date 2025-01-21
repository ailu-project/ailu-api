package org.ailu.api.repository;

import org.ailu.api.entity.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Long> {
    // Buscar medicamentos por `usuario_id`
    List<MedicamentoEntity> findByUsuarioEntity_Id(Long usuarioId);
}
