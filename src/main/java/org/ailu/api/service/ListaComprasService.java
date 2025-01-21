package org.ailu.api.service;

import jakarta.transaction.Transactional;
import org.ailu.api.dto.AdicionaItensDTO;
import org.ailu.api.dto.ListaComprasDTO;
import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.entity.listaCompras.ItemCompra;
import org.ailu.api.entity.listaCompras.ListaCompras;
import org.ailu.api.repository.ListaComprasRepository;
import org.ailu.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class  ListaComprasService {

    @Autowired
    private ListaComprasRepository listaComprasRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public ListaCompras criaListaCompras(ListaComprasDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado com ID: " + dto.getUsuarioId()));

        ListaCompras listaCompras = new ListaCompras();
        listaCompras.setNome(dto.getNome());
        listaCompras.setUsuarioEntity(usuario);
        listaCompras.setItens(List.of());

        // Salvar a lista no banco
        return listaComprasRepository.save(listaCompras);
    }

    public List<ListaCompras> findAllListaCompras() {
        return listaComprasRepository.findAll();
    }

    public Optional<ListaCompras> buscaListaComprasPorId(Long id) {
        return listaComprasRepository.findById(id);
    }

    public List<ListaCompras> getListasComprasByUsuarioId(Long usuarioId) {
        return listaComprasRepository.findByUsuarioEntity_Id(usuarioId);
    }
    @Transactional
    public ListaCompras adicionaItens(Long listaId, AdicionaItensDTO dto) {
        ListaCompras listaCompras = listaComprasRepository.findById(listaId)
                .orElseThrow(() -> new IllegalArgumentException("Lista de compras não encontrada com ID: " + listaId));
        List<ItemCompra> novosItens = dto.getItens().stream()
                .map(itemDTO -> {
                    ItemCompra itemCompra = new ItemCompra();
                    itemCompra.setComprado(itemDTO.isComprado());
                    itemCompra.setNome(itemDTO.getNome());
                    return itemCompra;
                }).toList();
        listaCompras.getItens().addAll(novosItens);

        return listaComprasRepository.save(listaCompras);
    }
    public void deleteListaCompras(Long id) {
        if (!listaComprasRepository.existsById(id)) {
            throw new IllegalArgumentException("Lista de compras não encontrada com ID: " + id);
        }
        listaComprasRepository.deleteById(id);
    }

}

