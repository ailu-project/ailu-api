package org.ailu.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(name = "medicamentos")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MedicamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private int alarmeEntity;
    private String descricao;
    private Date fimDaMedicacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "usuario_id",
            foreignKey = @ForeignKey(
                    name = "fk_calendario_usuario",
                    foreignKeyDefinition = "FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE"
            )
    )
    private UsuarioEntity usuarioEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAlarmeEntity() {
        return alarmeEntity;
    }

    public void setAlarmeEntity(int alarmeEntity) {
        this.alarmeEntity = alarmeEntity;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getFimDaMedicacao() {
        return fimDaMedicacao;
    }

    public void setFimDaMedicacao(Date fimDaMedicacao) {
        this.fimDaMedicacao = fimDaMedicacao;
    }

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }
}