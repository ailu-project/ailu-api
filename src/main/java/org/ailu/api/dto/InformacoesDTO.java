package org.ailu.api.dto;


import lombok.Data;

@Data
public class InformacoesDTO {
    private Long id;
    private String nome;
    private String login;
    private String informacao;
    private Long usuarioId;

    public InformacoesDTO() {
    }

    public InformacoesDTO(Long id, String nome, String login, String informacao, Long usuarioId) {
        this.id = id;
        this.nome = nome;

        this.login = login;
        this.informacao = informacao;
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



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}

