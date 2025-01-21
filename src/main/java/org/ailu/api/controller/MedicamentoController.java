package org.ailu.api.controller;

import org.ailu.api.dto.MedicamentoDTO;
import org.ailu.api.entity.MedicamentoEntity;
import org.ailu.api.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;


    @PostMapping
    public ResponseEntity<MedicamentoDTO> criarMedicamento(@RequestBody MedicamentoDTO medicamentoDTO) {
        MedicamentoDTO criado = medicamentoService.criarMedicamento(medicamentoDTO);
        return ResponseEntity.ok(criado);
    }


    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MedicamentoDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        List<MedicamentoDTO> medicamentos = medicamentoService.buscarPorUsuario(usuarioId);
        if (medicamentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(medicamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDTO> buscarPorId(@PathVariable Long id) {
        MedicamentoDTO medicamento = medicamentoService.buscarPorId(id);
        return ResponseEntity.ok(medicamento);
    }

    // Deletar medicamento por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMedicamento(@PathVariable Long id) {
        medicamentoService.deletarMedicamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoEntity>> listarMedicamentos() {
        return ResponseEntity.ok(medicamentoService.buscarTodas());
    }

}
