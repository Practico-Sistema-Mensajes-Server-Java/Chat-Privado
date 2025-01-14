package org.main_java.chatprivado.controller;

import org.main_java.chatprivado.domain.MensajePrivado;
import org.main_java.chatprivado.model.MensajePrivadoDTO;
import org.main_java.chatprivado.service.MensajePrivadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mensajes-privados")
public class MensajePrivadoController {

    @Autowired
    private MensajePrivadoService mensajePrivadoService;

    @GetMapping("/sala/{idSala}/usuario/{idUsuario}")
    public List<MensajePrivado> obtenerMensajesPorSala(@PathVariable Long idSala, @PathVariable Long idUsuario) throws Exception {
        return mensajePrivadoService.getMensajesPrivadosPorSala(idSala, idUsuario);
    }

    @GetMapping
    public List<MensajePrivado> obtenerTodosLosMensajes() {
        return mensajePrivadoService.getMensajesPrivados();
    }

    @PostMapping("/crear/usuario/{idUsuario}")
    public void crearMensaje(@PathVariable Long idUsuario, @RequestBody MensajePrivadoDTO mensajePrivadoDTO) throws Exception {
        mensajePrivadoService.create(idUsuario, mensajePrivadoDTO);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarMensaje(@PathVariable Long id) {
        mensajePrivadoService.delete(id);
    }
}