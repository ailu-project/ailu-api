package org.ailu.api.repository;

import org.ailu.api.entity.AlarmeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmeRepository extends JpaRepository<AlarmeEntity, Long> {
}
