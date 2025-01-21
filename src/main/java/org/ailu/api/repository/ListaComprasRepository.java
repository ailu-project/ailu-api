package org.ailu.api.repository;


import org.ailu.api.entity.listaCompras.ListaCompras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListaComprasRepository extends JpaRepository<ListaCompras, Long> {
    List<ListaCompras> findByUsuarioEntity_Id(Long id);
}
