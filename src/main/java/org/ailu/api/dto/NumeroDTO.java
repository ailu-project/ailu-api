package org.ailu.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.ailu.api.entity.NumeroEntity;
import org.springframework.beans.BeanUtils;

@AllArgsConstructor
@Data
public class NumeroDTO {
        private Long id;
        private String numero;
        private String nome;
        private Long usuario_id;

        public NumeroDTO() {
        }

        public NumeroDTO(NumeroEntity numeroEntity) {
            BeanUtils.copyProperties(numeroEntity, this);
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNumero() {
            return numero;
        }

        public void setNumero(String numero) {
            this.numero = numero;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Long getUsuario_id() {
            return usuario_id;
        }

        public void setUsuario_id(Long usuario_id) {
            this.usuario_id = usuario_id;
        }
}
