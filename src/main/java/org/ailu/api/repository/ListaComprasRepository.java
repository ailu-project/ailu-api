package org.ailu.api.repository;


import org.ailu.api.entity.ListaComprasEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaComprasRepository extends JpaRepository<ListaComprasEntity, Long> {
    List<ListaComprasEntity> findByUsuarioEntity_Id(Long id);
}
