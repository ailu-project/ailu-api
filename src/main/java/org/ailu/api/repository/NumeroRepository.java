package org.ailu.api.repository;

import org.ailu.api.entity.NumeroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NumeroRepository extends JpaRepository<NumeroEntity, Long> {
    List<NumeroEntity> findByUsuarioEntity_Id(Long id);
}
