package org.main_java.chatprivado.service;

import jakarta.transaction.Transactional;
import org.main_java.chatprivado.domain.Usuario;
import org.main_java.chatprivado.model.UsuarioDTO;
import org.main_java.chatprivado.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    // Convertir entidad Usuario a DTO
    private UsuarioDTO mapToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setRol(usuario.getRol());
        dto.setMensajes(usuario.getMensajes());
        dto.setSalas(usuario.getSalas());

        return dto;
    }

    // Convertir DTO a entidad Usuario
    private Usuario mapToEntity(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setRol(dto.getRol());
        usuario.setMensajes(dto.getMensajes());
        usuario.setSalas(dto.getSalas());

        return usuario;
    }

    // CRUD: Obtener todos los usuarios
    public List<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // CRUD: Obtener un usuario por ID
    public UsuarioDTO getById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return mapToDTO(usuario);
    }


    @Transactional
    public Usuario create(UsuarioDTO dto) {
        Usuario usuario = mapToEntity(dto);
        return usuarioRepository.save(usuario);
    }

    // CRUD: Actualizar un usuario y sus credenciales
    @Transactional
    public void update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuario.setNombre(dto.getNombre());
        usuario.setMensajes(dto.getMensajes());
        usuario.setSalas(dto.getSalas());
        usuario.setRol(dto.getRol());

        usuarioRepository.save(usuario);
    }

    // CRUD: Eliminar un usuario y sus credenciales
    @Transactional
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}