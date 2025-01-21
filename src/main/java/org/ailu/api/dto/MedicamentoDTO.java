package org.ailu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
public class MedicamentoDTO {
    private Long id;
    private String nome;
    private int alarme;
    private String descricao;
    private Date fimDaMedicacao;
    private Long usuarioId;

    public MedicamentoDTO() {
    }

    public MedicamentoDTO(Long id, String nome, int alarme, String descricao, Date fimDaMedicacao, Long usuarioId) {
        this.id = id;
        this.nome = nome;
        this.alarme = alarme;
        this.descricao = descricao;
        this.fimDaMedicacao = fimDaMedicacao;
        this.usuarioId = usuarioId;
    }

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

    public int getAlarme() {
        return alarme;
    }

    public void setAlarme(int alarme) {
        this.alarme = alarme;
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

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
