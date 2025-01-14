package org.main_java.chatprivado.service;

import jakarta.transaction.Transactional;
import org.main_java.chatprivado.domain.MensajePrivado;
import org.main_java.chatprivado.domain.SalaChatPrivado;
import org.main_java.chatprivado.domain.Usuario;
import org.main_java.chatprivado.model.MensajePrivadoDTO;
import org.main_java.chatprivado.repos.MensajePrivadoRepository;
import org.main_java.chatprivado.repos.SalaChatPrivadoRepository;
import org.main_java.chatprivado.repos.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensajePrivadoService {

    @Autowired
    private MensajePrivadoRepository mensajePrivadoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UtilCifradoService utilCifradoService;

    private final SalaChatPrivadoRepository salaChatPrivadoRepository;

    public MensajePrivadoService(@Lazy SalaChatPrivadoRepository salaChatPrivadoRepository) {
        this.salaChatPrivadoRepository = salaChatPrivadoRepository;
    }

    public List<MensajePrivado> getMensajesPrivadosPorSala(Long id_sala, Long id_usuario) throws Exception {
        SalaChatPrivado salaChatPrivado = salaChatPrivadoRepository.findById(id_sala)
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada"));

        Usuario usuario = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (salaChatPrivado.getUsuarios() == null || !salaChatPrivado.getUsuarios().contains(usuario)) {
            throw new IllegalArgumentException("El usuario no pertenece a la sala");
        }

        for (int i = 0; i < salaChatPrivado.getMensajesPrivados().size(); i++) {
            salaChatPrivado.getMensajesPrivados().get(i).setContenidoCifrado(
                    utilCifradoService.descifrarMensaje(salaChatPrivado.getMensajesPrivados().get(i).getContenidoCifrado(), salaChatPrivado.getClave()));
        }

        return salaChatPrivado.getMensajesPrivados();
    }

    public List<MensajePrivado> getMensajesPrivados() {
        return mensajePrivadoRepository.findAll();
    }

    public MensajePrivado findMensajePrivadoById(Long id) {
        return mensajePrivadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado"));
    }

    @Transactional
    public void create(Long id_usuario, MensajePrivadoDTO mensajePrivadoDTO) throws Exception {

        Usuario usuario = usuarioRepository.findById(id_usuario)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        if (mensajePrivadoDTO.getUsuario() == null) {
            throw new IllegalArgumentException("El mensaje debe tener un usuario asociado");
        }

        if (mensajePrivadoDTO.getUsuario().getId() != id_usuario) {
            throw new IllegalArgumentException("El mensaje debe tener el mismo usuario que el que lo crea");
        }

        if (mensajePrivadoDTO.getContendoCifrado() == null) {
            throw new IllegalArgumentException("El mensaje debe tener contenido");
        }

        if (mensajePrivadoDTO.getFecha() == null) {
            throw new IllegalArgumentException("El mensaje debe tener fecha");
        }

        salaChatPrivadoRepository.findById(mensajePrivadoDTO.getSalaChatPrivado().getId())
                .orElseThrow(() -> new IllegalArgumentException("Sala no encontrada"));

        if (usuario.getSalas() == null || !usuario.getSalas().contains(mensajePrivadoDTO.getSalaChatPrivado())) {
            throw new IllegalArgumentException("El usuario no pertenece a la sala");
        }

        String mensajeCifrado = utilCifradoService.cifrarMensaje(mensajePrivadoDTO.getContendoCifrado(), mensajePrivadoDTO.getSalaChatPrivado().getClave());
        mensajePrivadoDTO.setContendoCifrado(mensajeCifrado);
        mensajePrivadoRepository.save(mapToEntity(mensajePrivadoDTO));
    }

    @Transactional
    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El mensaje debe tener un id");
        }

        mensajePrivadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mensaje no encontrado"));


        mensajePrivadoRepository.deleteById(id);
    }

    private MensajePrivado mapToEntity(MensajePrivadoDTO mensajePrivadoDTO) {
        MensajePrivado mensajePrivado = new MensajePrivado();

        mensajePrivado.setId(mensajePrivadoDTO.getId());
        mensajePrivado.setContenidoCifrado(mensajePrivadoDTO.getContendoCifrado());
        mensajePrivado.setFecha(mensajePrivadoDTO.getFecha());
        mensajePrivado.setUsuario(mensajePrivadoDTO.getUsuario());
        mensajePrivado.setSalaChatPrivado(mensajePrivadoDTO.getSalaChatPrivado());
        return mensajePrivado;
    }

    private MensajePrivadoDTO mapToDTO(MensajePrivado mensajePrivado) {
        MensajePrivadoDTO mensajePrivadoDTO = new MensajePrivadoDTO();

        mensajePrivadoDTO.setId(mensajePrivado.getId());
        mensajePrivadoDTO.setContendoCifrado(mensajePrivado.getContenidoCifrado());
        mensajePrivadoDTO.setFecha(mensajePrivado.getFecha());
        mensajePrivadoDTO.setUsuario(mensajePrivado.getUsuario());
        mensajePrivadoDTO.setSalaChatPrivado(mensajePrivado.getSalaChatPrivado());
        return mensajePrivadoDTO;
    }
}
