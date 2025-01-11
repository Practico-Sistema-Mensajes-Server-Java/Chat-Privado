package org.main_java.chatprivado.service;

import jakarta.transaction.Transactional;
import org.main_java.chatprivado.domain.SalaChatPrivado;
import org.main_java.chatprivado.domain.Usuario;
import org.main_java.chatprivado.model.SalaChatPrivadoDTO;
import org.main_java.chatprivado.repos.SalaChatPrivadoRepository;
import org.main_java.chatprivado.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaChatPrivadoService {

    @Autowired
    private SalaChatPrivadoRepository salaChatPrivadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public SalaChatPrivado create (SalaChatPrivadoDTO salaChatPrivadoDTO, Long idUsuario, Long id_receptor) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Usuario receptor = usuarioRepository.findById(id_receptor)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!usuario.getRol().equals("administrador")) {
            throw new IllegalArgumentException("El usuario no tiene permisos para crear salas");
        }

        SalaChatPrivado salaChatPrivado = mapToEntity(salaChatPrivadoDTO);
        salaChatPrivado.getUsuarios().add(usuario);
        salaChatPrivado.getUsuarios().add(receptor);
        usuario.getSalas().add(salaChatPrivado);
        receptor.getSalas().add(salaChatPrivado);

        return salaChatPrivadoRepository.save(salaChatPrivado);
    }

    @Transactional
    public String delete (Long idSala, Long idUsuario1, Long idUsuario2) {
        SalaChatPrivado salaChatPrivado = salaChatPrivadoRepository.findById(idSala)
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada"));

        Usuario usuario = usuarioRepository.findById(idUsuario1)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        Usuario receptor = usuarioRepository.findById(idUsuario2)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (!salaChatPrivado.getUsuarios().contains(usuario) || !salaChatPrivado.getUsuarios().contains(receptor)) {
            throw new IllegalArgumentException("Uno de los usuarios no pertenece a la sala");
        }

        salaChatPrivado.getUsuarios().remove(usuario);
        usuario.getSalas().remove(salaChatPrivado);
        receptor.getSalas().remove(salaChatPrivado);

        salaChatPrivadoRepository.delete(salaChatPrivado);

        return "Usuarios eliminados de la sala y sala eliminada";
    }

    @Transactional
    public SalaChatPrivadoDTO getById(Long id) {
        SalaChatPrivado salaChatPrivado = salaChatPrivadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada"));

        return mapToDTO(salaChatPrivado);
    }

    public List<SalaChatPrivadoDTO> findAll() {
        List<SalaChatPrivado> salas = salaChatPrivadoRepository.findAll();
        return salas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public List<SalaChatPrivadoDTO> findByUsuario(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        List<SalaChatPrivado> salas = usuario.getSalas();
        return salas.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private SalaChatPrivado mapToEntity(SalaChatPrivadoDTO salaChatDTO) {

        SalaChatPrivado salaChat = new SalaChatPrivado();

        salaChat.setId(salaChatDTO.getId());
        salaChat.setClave(salaChatDTO.getClave());
        salaChat.setMensajesPrivados(salaChatDTO.getMensajesPrivados());
        salaChat.setUsuarios(salaChatDTO.getUsuarios());

        return salaChat;
    }

    private SalaChatPrivadoDTO mapToDTO(SalaChatPrivado salaChat) {

        SalaChatPrivadoDTO salaChatDTO = new SalaChatPrivadoDTO();

        salaChatDTO.setId(salaChat.getId());
        salaChatDTO.setClave(salaChat.getClave());
        salaChatDTO.setMensajesPrivados(salaChat.getMensajesPrivados());
        salaChatDTO.setUsuarios(salaChat.getUsuarios());

        return salaChatDTO;
    }
}
