package org.ailu.api.repository;

import org.ailu.api.entity.InformacoesEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformacoesRepository extends JpaRepository<InformacoesEntity, Long> {
    List<InformacoesEntity> findByUsuarioEntity_Id(Long usuarioId);
}
