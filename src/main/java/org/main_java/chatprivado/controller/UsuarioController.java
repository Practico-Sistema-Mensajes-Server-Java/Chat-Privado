package org.main_java.chatprivado.controller;

import org.main_java.chatprivado.model.UsuarioDTO;
import org.main_java.chatprivado.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.getById(id);
    }

    @PostMapping("/crear")
    public void crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.create(usuarioDTO);
    }

    @PutMapping("/actualizar/{id}")
    public void actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.update(id, usuarioDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
    }
}
