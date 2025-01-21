package org.ailu.api.service;

import org.ailu.api.dto.EventoDTO;
import org.ailu.api.entity.EventoEntity;
import org.ailu.api.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public EventoEntity saveEvento(EventoDTO dto) {
        if (usuarioService.getUserById(dto.getUsuario_id()).isPresent()) {
            EventoEntity eventoEntity = new EventoEntity();
            eventoEntity.setUsuarioEntity(usuarioService.getUserById(dto.getUsuario_id()).orElse(null));
            eventoEntity.setDescricao(dto.getDescricao());
            eventoEntity.setFim(dto.getFim());
            eventoEntity.setInicio(dto.getInicio());
            eventoEntity.setTitulo(dto.getTitulo());
            return eventoRepository.save(eventoEntity);
        }
        return null;
    }

    public List<EventoEntity> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<EventoEntity> getEventoById(Long id) {

        return eventoRepository.findById(id);
    }

    public List<EventoEntity> getEventosByUsuarioId(Long usuarioId) {
        return eventoRepository.findByUsuarioEntity_Id(usuarioId);
    }

    public EventoEntity updateEvento(Long id, EventoDTO eventoDetails) {
        EventoEntity eventoEntity = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        if (eventoEntity != null) {
            if (eventoDetails.getTitulo() != null) {
                eventoEntity.setTitulo(eventoDetails.getTitulo());
            }
            if (eventoDetails.getDescricao() != null) {
                eventoEntity.setDescricao(eventoDetails.getDescricao());
            }
            if (eventoDetails.getInicio() != null) {
                eventoEntity.setInicio(eventoDetails.getInicio());
            }
            if (eventoDetails.getFim() != null) {
                eventoEntity.setFim(eventoDetails.getFim());
            }
            return eventoRepository.save(eventoEntity);
        }
        return null;
    }


    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
