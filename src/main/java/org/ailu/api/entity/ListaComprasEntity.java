package org.ailu.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ListaComprasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "usuario_id",
            foreignKey = @ForeignKey(
                    name = "fk_compras_usuario",
                    foreignKeyDefinition = "FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE"
            )
    )
    private UsuarioEntity usuarioEntity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemCompraEntity> itens;

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

    public UsuarioEntity getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public List<ItemCompraEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemCompraEntity> itens) {
        this.itens = itens;
    }
}