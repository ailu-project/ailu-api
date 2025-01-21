package org.ailu.api.entity.calendario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.ailu.api.dto.calendario.EventoDTO;
import org.ailu.api.entity.usuario.Usuario;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name = "calendario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "usuario_id",
            foreignKey = @ForeignKey(
                    name = "fk_calendario_usuario",
                    foreignKeyDefinition = "FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE"
            )
    )
    private Usuario usuario;
    private String titulo;
    private String descricao;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public Evento(EventoDTO eventoDTO) {
        BeanUtils.copyProperties(eventoDTO, this);
    }

    public Evento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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