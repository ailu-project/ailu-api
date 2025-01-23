package org.ailu.api.controller;

import org.ailu.api.dto.UsuarioDTO;
import org.ailu.api.entity.UsuarioEntity;
import org.ailu.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioEntity> createUser(@RequestBody UsuarioDTO dto) {
        try{
            return ResponseEntity.ok(usuarioService.saveUser(new UsuarioEntity(dto)));
        }
       catch(IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
       }
    }

    @GetMapping
    public List<UsuarioEntity> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> getUserById(@PathVariable Long id) {
        Optional<UsuarioEntity> usuario = usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UsuarioEntity> getUserByEmail(@PathVariable String email) {
        UsuarioEntity usuarioEntity = usuarioService.getUserByEmail(email);
        return ResponseEntity.ok(usuarioEntity);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioEntity> getUserByCpf(@PathVariable String cpf) {
        UsuarioEntity usuarioEntity = usuarioService.getUserByCpf(cpf);
        return ResponseEntity.ok(usuarioEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> updateUser(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        UsuarioEntity updatedUser = usuarioService.updateUser(id, new UsuarioEntity(dto));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desativar/{id}")
    public ResponseEntity<UsuarioEntity> desativarUser(@PathVariable Long id) {
        usuarioService.desativarUser(id);
        Optional<UsuarioEntity> usuario = usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/login")
    public ResponseEntity<Boolean> login(@RequestParam String email, @RequestParam String password) {
        if (usuarioService.loginUser(email, password)) {
            return ResponseEntity.ok(true);
        }
        else return ResponseEntity.notFound().build();
    }
}
