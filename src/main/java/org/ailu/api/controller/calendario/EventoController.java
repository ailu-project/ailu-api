package org.ailu.api.controller.calendario;

import org.ailu.api.dto.calendario.EventoDTO;
import org.ailu.api.entity.calendario.Evento;
import org.ailu.api.entity.usuario.Usuario;
import org.ailu.api.service.calendario.EventoService;
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
    public Evento createEvento(@RequestBody EventoDTO dto) {
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
        Optional<Evento> evento = eventoService.getEventoById(id);
        return evento.map(e -> ResponseEntity.ok(mapToDTO(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/usuario/{usuarioId}")
    public List<EventoDTO> getEventosByUsuarioId(@PathVariable Long usuarioId) {
        return eventoService.getEventosByUsuarioId(usuarioId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        Evento eventoDetails = new Evento();
        eventoDetails.setTitulo(eventoDTO.getTitulo());
        eventoDetails.setDescricao(eventoDTO.getDescricao());
        eventoDetails.setInicio(eventoDTO.getInicio());
        eventoDetails.setFim(eventoDTO.getFim());
        Evento updatedEvento = eventoService.updateEvento(id, new EventoDTO(eventoDetails));
        return ResponseEntity.ok(mapToDTO(updatedEvento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long id) {
        eventoService.deleteEvento(id);
        return ResponseEntity.noContent().build();
    }
    private EventoDTO mapToDTO(Evento evento) {
        EventoDTO eventoDTO = new EventoDTO();
        eventoDTO.setId(evento.getId());
        eventoDTO.setUsuario_id(evento.getUsuario().getId()); ;
        eventoDTO.setTitulo(evento.getTitulo());
        eventoDTO.setDescricao(evento.getDescricao());
        eventoDTO.setInicio(evento.getInicio());
        eventoDTO.setFim(evento.getFim());

        return eventoDTO;
    }
}

