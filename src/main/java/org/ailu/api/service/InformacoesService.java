package org.ailu.api.service;

import org.ailu.api.entity.InformacoesEntity;

import org.ailu.api.repository.InformacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class InformacoesService {

    @Autowired
    private InformacoesRepository informacoesRepository;

    public List<InformacoesEntity> getInformacoesByUsuarioId(Long usuarioId) {
        return informacoesRepository.findByUsuarioEntity_Id(usuarioId);
    }


    public InformacoesEntity criarInformacao(InformacoesEntity info) {
        return informacoesRepository.save(info);
    }

    public void deletarInformacao(Long infoId) {
        if (!informacoesRepository.existsById(infoId)) {
            throw new IllegalArgumentException("Informação com ID " + infoId + " não encontrada.");
        }
        informacoesRepository.deleteById(infoId);
    }

    public InformacoesEntity atualizarInformacao(Long infoId, InformacoesEntity informacoesEntityAtualizadas) {
        InformacoesEntity informacaoExistente = informacoesRepository.findById(infoId)
                .orElseThrow(() -> new IllegalArgumentException("Informação com ID " + infoId + " não encontrada."));
        if (informacoesEntityAtualizadas.getNome() != null && !informacoesEntityAtualizadas.getNome().isEmpty()) {
            informacaoExistente.setNome(informacoesEntityAtualizadas.getNome());
        }
        if(informacoesEntityAtualizadas.getInformacao() != null && !informacoesEntityAtualizadas.getInformacao().isEmpty()) {
            informacaoExistente.setInformacao(informacoesEntityAtualizadas.getInformacao());

        }
        if(informacoesEntityAtualizadas.getLogin() != null) {
            informacaoExistente.setLogin(informacoesEntityAtualizadas.getLogin());
        }
        return informacoesRepository.save(informacaoExistente);
    }

    public List<InformacoesEntity> findAllInformacoes() {
        return informacoesRepository.findAll();
    }
}
