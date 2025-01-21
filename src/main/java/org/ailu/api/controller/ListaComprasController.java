package org.ailu.api.controller;

import org.ailu.api.dto.AdicionaItensDTO;
import org.ailu.api.dto.ListaComprasDTO;
import org.ailu.api.entity.listaCompras.ListaCompras;
import org.ailu.api.service.ListaComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lista-compras")
public class ListaComprasController {

    @Autowired
    private ListaComprasService listaComprasService;


    @PostMapping
    public ResponseEntity<ListaCompras> criaLista(@RequestBody ListaComprasDTO dto) {
        try {
            ListaCompras novaLista = listaComprasService.criaListaCompras(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaLista);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    @GetMapping
    public List<ListaCompras> allListas() {
        return listaComprasService.findAllListaCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListaCompras> buscaLista(@PathVariable Long id) {
        return ResponseEntity.ok(listaComprasService.buscaListaComprasPorId(id).orElse(null));
    }

    @PutMapping("/{listaId}/itens")
    public ResponseEntity<ListaCompras> adicionaItens(@PathVariable Long listaId, @RequestBody AdicionaItensDTO dto) {
        try {
            ListaCompras listaAtualizada = listaComprasService.adicionaItens(listaId, dto);
            return ResponseEntity.ok(listaAtualizada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ListaCompras>> getListasByUsuarioId(@PathVariable Long usuarioId) {
        List<ListaCompras> listas = listaComprasService.getListasComprasByUsuarioId(usuarioId);

        if (listas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(listas);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListaCompras(@PathVariable Long id) {
        try {
            listaComprasService.deleteListaCompras(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}



