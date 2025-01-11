package org.main_java.chatprivado.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.main_java.chatprivado.domain.MensajePrivado;
import org.main_java.chatprivado.domain.SalaChatPrivado;

import java.util.List;

@Getter
@Setter
@Data
public class UsuarioDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    private String nombre;

    @NotNull
    @Size(min = 1, max = 255)
    private String rol;

    private List<MensajePrivado> mensajes;

    private List<SalaChatPrivado> salas;
}
