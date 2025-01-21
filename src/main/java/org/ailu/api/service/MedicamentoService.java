package org.ailu.api.service;

import org.ailu.api.dto.MedicamentoDTO;
import org.ailu.api.entity.AlarmeEntity;
import org.ailu.api.entity.MedicamentoEntity;
import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.repository.AlarmeRepository;
import org.ailu.api.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private AlarmeRepository alarmeRepository;

    public MedicamentoDTO criarMedicamento(MedicamentoDTO medicamentoDTO) {
        MedicamentoEntity medicamento = new MedicamentoEntity();
        medicamento.setNome(medicamentoDTO.getNome());
        medicamento.setDescricao(medicamentoDTO.getDescricao());
        medicamento.setFimDaMedicacao(medicamentoDTO.getFimDaMedicacao());

        // Criar o alarme, se fornecido
        if (medicamentoDTO.getAlarme() != null) {
            AlarmeEntity alarme = new AlarmeEntity();
            alarme.setHora(medicamentoDTO.getAlarme().getHora());
            alarme = alarmeRepository.save(alarme);
            medicamento.setAlarmeEntity(alarme);
        }
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(medicamentoDTO.getUsuarioId());
        medicamento.setUsuarioEntity(usuario);
        MedicamentoEntity salvo = medicamentoRepository.save(medicamento);
        return mapToDTO(salvo);
    }



    public List<MedicamentoDTO> buscarPorUsuario(Long usuarioId) {
        List<MedicamentoEntity> medicamentos = medicamentoRepository.findByUsuarioEntity_Id(usuarioId);
        return medicamentos.stream().map(this::mapToDTO).collect(Collectors.toList());
    }


    public MedicamentoDTO buscarPorId(Long id) {
        MedicamentoEntity medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Medicamento não encontrado com ID: " + id));
        return mapToDTO(medicamento);
    }


    public void deletarMedicamento(Long id) {
        if (!medicamentoRepository.existsById(id)) {
            throw new IllegalArgumentException("Medicamento não encontrado com ID: " + id);
        }
        medicamentoRepository.deleteById(id);
    }
    public List<MedicamentoEntity> buscarTodas() {
        return medicamentoRepository.findAll();
    }

    private MedicamentoDTO mapToDTO(MedicamentoEntity entity) {
        return new MedicamentoDTO(
                entity.getId(),
                entity.getNome(),
                entity.getAlarmeEntity(),
                entity.getDescricao(),
                entity.getFimDaMedicacao(),
                entity.getUsuarioEntity() != null ? entity.getUsuarioEntity().getId() : null
        );
    }
}
