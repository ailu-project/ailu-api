package org.ailu.api.controller;

import org.ailu.api.dto.EventoDTO;
import org.ailu.api.entity.EventoEntity;
import org.ailu.api.service.EventoService;
import org.springframework.beans.NullValueInNestedPathException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping
    public EventoEntity createEvento(@RequestBody EventoDTO dto) {

        return eventoService.saveEvento(dto);
    }

    @GetMapping
    public List<EventoDTO> getAllEventos() {
        return eventoService.getAllEventos().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        Optional<EventoEntity> evento = eventoService.getEventoById(id);
        return evento.map(e -> ResponseEntity.ok(mapToDTO(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<EventoDTO>> getEventosByUsuarioId(@PathVariable Long usuarioId) {
        try {
            List<EventoDTO> eventos = eventoService.getEventosByUsuarioId(usuarioId).stream()
                    .map(this::mapToDTO)
                    .toList();
            if (eventos.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(eventos);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        EventoEntity eventoEntityDetails = new EventoEntity();
        eventoEntityDetails.setTitulo(eventoDTO.getTitulo());
        eventoEntityDetails.setDescricao(eventoDTO.getDescricao());
        eventoEntityDetails.setInicio(eventoDTO.getInicio());
        eventoEntityDetails.setFim(eventoDTO.getFim());
        EventoEntity updatedEventoEntity = eventoService.updateEvento(id, new EventoDTO(eventoEntityDetails));
       if (updatedEventoEntity != null) {
           return ResponseEntity.ok(mapToDTO(updatedEventoEntity));
       }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
    private EventoDTO mapToDTO(EventoEntity eventoEntity) {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setId(eventoEntity.getId());
        eventoDTO.setUsuario_id(eventoEntity.getUsuarioEntity().getId()); ;
        eventoDTO.setTitulo(eventoEntity.getTitulo());
        eventoDTO.setDescricao(eventoEntity.getDescricao());
        eventoDTO.setInicio(eventoEntity.getInicio());
        eventoDTO.setFim(eventoEntity.getFim());

        return eventoDTO;
    }
}

