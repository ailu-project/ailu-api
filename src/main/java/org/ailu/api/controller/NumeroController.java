package org.ailu.api.controller;

import org.ailu.api.dto.NumeroDTO;
import org.ailu.api.entity.NumeroEntity;
import org.ailu.api.service.NumeroService;
import org.ailu.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/numeros")
public class NumeroController {

    @Autowired
    private NumeroService numeroService;


    @PostMapping
    public ResponseEntity<NumeroEntity> createNumero(@RequestBody NumeroDTO dto) {
        try {
            NumeroEntity numeroEntity = numeroService.saveNumero(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(numeroEntity);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    public List<NumeroDTO> getAllNumeros() {
        return numeroService.getAllNumeros().stream()
                .map(this::maptoDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NumeroDTO> getNumeroById(@PathVariable Long id) {
        Optional<NumeroEntity> numero = numeroService.getNumeroById(id);
        return numero.map(e -> ResponseEntity.ok(maptoDTO(e))).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<NumeroDTO>> getNumeroByUsuarioId(@PathVariable Long usuarioId) {
        try {
            List<NumeroDTO> numero = numeroService.getNumeroByUsuarioId(usuarioId).stream()
                    .map(this::maptoDTO).toList();
            if (numero.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(numero);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<NumeroDTO> updateNumero(@PathVariable Long id, @RequestBody NumeroDTO dto) {
            NumeroEntity numero = new NumeroEntity();
            numero.setNumero(dto.getNumero());
            numero.setNome(dto.getNome());
            NumeroEntity updateNumero = numeroService.updateNumero(id, new NumeroDTO(numero));
            if (updateNumero != null) {
                return ResponseEntity.ok(maptoDTO(updateNumero));
            }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<NumeroDTO> deleteNumero(@PathVariable Long id) {
        if(numeroService.getNumeroById(id).isPresent()) {
            numeroService.deleteNumero(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();

    }


    private NumeroDTO maptoDTO(NumeroEntity entity) throws IllegalArgumentException {
      NumeroDTO dto = new NumeroDTO();
      dto.setId(entity.getId());
      dto.setNumero(entity.getNumero());
      dto.setNome(entity.getNome());
      if (entity.getUsuarioEntity() != null) {
          dto.setUsuario_id(entity.getUsuarioEntity().getId());
          return dto;
      }
        return null;
    }
}
