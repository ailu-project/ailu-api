package org.ailu.api.controller;

import org.ailu.api.dto.InformacoesDTO;
import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.entity.InformacoesEntity;
import org.ailu.api.service.InformacoesService;
import org.ailu.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/informacoes")
public class InformacoesController {

    @Autowired
    private InformacoesService informacoesService;

    @Autowired
    private UsuarioService usuarioService;

    private InformacoesDTO mapToDTO(InformacoesEntity informacoesEntity) {
        return new InformacoesDTO(
                informacoesEntity.getId(),
                informacoesEntity.getNome(),
                informacoesEntity.getLogin(),
                informacoesEntity.getInformacao(),
                informacoesEntity.getUsuarioEntity().getId()
        );
    }

    @GetMapping
    public List<InformacoesEntity> findAll() {
        return informacoesService.findAllInformacoes();
    }

    @PostMapping
    public ResponseEntity<InformacoesDTO> criarInformacao(@RequestBody InformacoesDTO dto) {
        InformacoesEntity novaInfo = new InformacoesEntity();
        novaInfo.setNome(dto.getNome());
        novaInfo.setLogin(dto.getLogin());
        novaInfo.setInformacao(dto.getInformacao());

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(dto.getUsuarioId());
        novaInfo.setUsuarioEntity(usuario); // Associa o usuário pelo ID

        InformacoesEntity entidadeCriada = informacoesService.criarInformacao(novaInfo);
        return ResponseEntity.ok(mapToDTO(entidadeCriada));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<InformacoesDTO>> getInformacoesByUsuarioId(@PathVariable Long usuarioId) {
        List<InformacoesEntity> informacoes = informacoesService.getInformacoesByUsuarioId(usuarioId);
        if (informacoes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<InformacoesDTO> informacoesDTOs = informacoes.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(informacoesDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InformacoesDTO> atualizarInformacao(@PathVariable Long id, @RequestBody InformacoesDTO dto) {
        InformacoesEntity infoAtualizada = new InformacoesEntity();
        infoAtualizada.setNome(dto.getNome());
        infoAtualizada.setLogin(dto.getLogin());
        infoAtualizada.setInformacao(dto.getInformacao());
        InformacoesEntity entidadeAtualizada = informacoesService.atualizarInformacao(id, infoAtualizada);
        return ResponseEntity.ok(mapToDTO(entidadeAtualizada));
    }

    // Deletar uma informação
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarInformacao(@PathVariable Long id) {
        informacoesService.deletarInformacao(id);
        return ResponseEntity.noContent().build();
    }
}
