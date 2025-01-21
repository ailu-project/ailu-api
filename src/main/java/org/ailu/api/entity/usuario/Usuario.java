package org.ailu.api.entity.usuario;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.ailu.api.dto.usuario.UsuarioDTO;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Builder
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String image;
    private boolean ativo = true;
    @Column(nullable = false)
    private String senha;
    @Column(unique = true, nullable = false)
    private String email;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Usuario(UsuarioDTO usuario) {
        BeanUtils.copyProperties(usuario, this);
    }
    public  Usuario(){}
    public void update(Usuario usuario) {
        if(usuario.getCpf() != null) {
            this.cpf = usuario.getCpf();
        }
        if(usuario.getNome() != null) {
            this.nome = usuario.getNome();
        }
        if(usuario.getDataNascimento() != null) {
            this.dataNascimento = usuario.getDataNascimento();
        }
        if(usuario.getImage() != null) {
            this.image = usuario.getImage();
        }
        if(usuario.getSenha() != null) {
            this.senha = usuario.getSenha();
        }
        if(usuario.getEmail() != null) {
            this.email = usuario.getEmail();
        }
        this.updatedAt = LocalDateTime.now();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
