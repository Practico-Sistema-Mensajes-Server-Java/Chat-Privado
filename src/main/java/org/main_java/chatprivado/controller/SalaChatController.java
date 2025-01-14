package org.main_java.chatprivado.controller;

import org.main_java.chatprivado.model.SalaChatPrivadoDTO;
import org.main_java.chatprivado.service.SalaChatPrivadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas-chat-privadas")
public class SalaChatController {

    @Autowired
    private SalaChatPrivadoService salaChatPrivadoService;

    @PostMapping("/crear/usuario/{idUsuario}/receptor/{idReceptor}")
    public SalaChatPrivadoDTO crearSala(@PathVariable Long idUsuario, @PathVariable Long idReceptor, @RequestBody SalaChatPrivadoDTO salaChatPrivadoDTO) {
        return salaChatPrivadoService.create(salaChatPrivadoDTO, idUsuario, idReceptor);
    }

    @DeleteMapping("/eliminar/{idSala}/usuario/{idUsuario}/receptor/{idReceptor}")
    public String eliminarSala(@PathVariable Long idSala, @PathVariable Long idUsuario, @PathVariable Long idReceptor) {
        return salaChatPrivadoService.delete(idSala, idUsuario, idReceptor);
    }

    @GetMapping("/{id}")
    public SalaChatPrivadoDTO obtenerSalaPorId(@PathVariable Long id) {
        return salaChatPrivadoService.getById(id);
    }

    @GetMapping
    public List<SalaChatPrivadoDTO> obtenerTodasLasSalas() {
        return salaChatPrivadoService.findAll();
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<SalaChatPrivadoDTO> obtenerSalasPorUsuario(@PathVariable Long idUsuario) {
        return salaChatPrivadoService.findByUsuario(idUsuario);
    }
}

