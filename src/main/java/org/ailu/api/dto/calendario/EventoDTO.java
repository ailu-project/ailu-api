package org.ailu.api.dto.calendario;

import lombok.Data;
import org.ailu.api.entity.calendario.Evento;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
public class EventoDTO {
        private Long id;
        private Long usuario_id;
        private String titulo;
        private String descricao;
        private LocalDateTime inicio;
        private LocalDateTime fim;
        public EventoDTO(Evento evento) {
            BeanUtils.copyProperties(evento, this);
        }

    public EventoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
}
