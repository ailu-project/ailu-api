package org.ailu.api.service.calendario;

import org.ailu.api.dto.calendario.EventoDTO;
import org.ailu.api.entity.calendario.Evento;
import org.ailu.api.repository.calendario.EventoRepository;
import org.ailu.api.service.usuario.UsuarioService;
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

    public Evento saveEvento(EventoDTO dto) {
        if (usuarioService.getUserById(dto.getUsuario_id()).isPresent()) {
            Evento evento = new Evento();
            evento.setUsuario(usuarioService.getUserById(dto.getUsuario_id()).orElse(null));
            evento.setDescricao(dto.getDescricao());
            evento.setFim(dto.getFim());
            evento.setInicio(dto.getInicio());
            evento.setTitulo(dto.getTitulo());
            return eventoRepository.save(evento);
        }
        return null;
    }

    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> getEventoById(Long id) {

        return eventoRepository.findById(id);
    }

    public List<Evento> getEventosByUsuarioId(Long usuarioId) {
        return eventoRepository.findByUsuarioId(usuarioId);
    }

    public Evento updateEvento(Long id, EventoDTO eventoDetails) {
        Evento evento = eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        if (evento != null) {
            if (eventoDetails.getTitulo() != null) {
                evento.setTitulo(eventoDetails.getTitulo());
            }
            if (eventoDetails.getDescricao() != null) {
                evento.setDescricao(eventoDetails.getDescricao());
            }
            if (eventoDetails.getInicio() != null) {
                evento.setInicio(eventoDetails.getInicio());
            }
            if (eventoDetails.getFim() != null) {
                evento.setFim(eventoDetails.getFim());
            }
            return eventoRepository.save(evento);
        }
        return null;
    }


    public void deleteEvento(Long id) {
        eventoRepository.deleteById(id);
    }
}
