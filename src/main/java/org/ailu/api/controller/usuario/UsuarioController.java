package org.ailu.api.controller.usuario;

import org.ailu.api.dto.usuario.UsuarioDTO;
import org.ailu.api.entity.usuario.Usuario;
import org.ailu.api.service.usuario.UsuarioService;
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
    public Usuario createUser(@RequestBody UsuarioDTO dto) {
        return usuarioService.saveUser(new Usuario(dto));
    }

    @GetMapping
    public List<Usuario> getAllUsers() {
        return usuarioService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUserById(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> getUserByEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.getUserByEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Usuario> getUserByCpf(@PathVariable String cpf) {
        Usuario usuario = usuarioService.getUserByCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUser(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        Usuario updatedUser = usuarioService.updateUser(id, new Usuario(dto));
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/desativar/{id}")
    public ResponseEntity<Usuario> desativarUser(@PathVariable Long id) {
        usuarioService.desativarUser(id);
        Optional<Usuario> usuario = usuarioService.getUserById(id);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
