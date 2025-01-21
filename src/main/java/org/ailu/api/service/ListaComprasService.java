package org.ailu.api.service;

import jakarta.transaction.Transactional;
import org.ailu.api.dto.AdicionaItensDTO;
import org.ailu.api.dto.ListaComprasDTO;
import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.entity.ItemCompraEntity;
import org.ailu.api.entity.ListaComprasEntity;
import org.ailu.api.repository.ListaComprasRepository;
import org.ailu.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  ListaComprasService {

    @Autowired
    private ListaComprasRepository listaComprasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ListaComprasEntity criaListaCompras(ListaComprasDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        ListaComprasEntity listaComprasEntity = new ListaComprasEntity();
        listaComprasEntity.setNome(dto.getNome());
        listaComprasEntity.setUsuarioEntity(usuario);
        listaComprasEntity.setItens(List.of());

        // Salvar a lista no banco
        return listaComprasRepository.save(listaComprasEntity);
    }

    public List<ListaComprasEntity> findAllListaCompras() {
        return listaComprasRepository.findAll();
    }

    public Optional<ListaComprasEntity> buscaListaComprasPorId(Long id) {
        return listaComprasRepository.findById(id);
    }

    public List<ListaComprasEntity> getListasComprasByUsuarioId(Long usuarioId) {
        return listaComprasRepository.findByUsuarioEntity_Id(usuarioId);
    }
    @Transactional
    public ListaComprasEntity adicionaItens(Long listaId, AdicionaItensDTO dto) {
        ListaComprasEntity listaComprasEntity = listaComprasRepository.findById(listaId)
                .orElseThrow(() -> new IllegalArgumentException("Lista de compras não encontrada com ID: " + listaId));
        List<ItemCompraEntity> novosItens = dto.getItens().stream()
                .map(itemDTO -> {
                    ItemCompraEntity itemCompraEntity = new ItemCompraEntity();
                    itemCompraEntity.setComprado(itemDTO.isComprado());
                    itemCompraEntity.setNome(itemDTO.getNome());
                    return itemCompraEntity;
                }).toList();
        listaComprasEntity.getItens().addAll(novosItens);

        return listaComprasRepository.save(listaComprasEntity);
    }
    public void deleteListaCompras(Long id) {
        if (!listaComprasRepository.existsById(id)) {
            throw new IllegalArgumentException("Lista de compras não encontrada com ID: " + id);
        }
        listaComprasRepository.deleteById(id);
    }

}

