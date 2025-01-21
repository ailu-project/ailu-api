package org.ailu.api.dto;
import lombok.Data;
import org.ailu.api.entity.UsuarioEntity;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class UsuarioDTO {
    private String cpf;
    private String nome;
    private Date dataNascimento;
    private String email;
    private String senha;

    public UsuarioDTO(UsuarioEntity usuarioEntity) {
        BeanUtils.copyProperties(usuarioEntity, this);
    }
    public UsuarioDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
