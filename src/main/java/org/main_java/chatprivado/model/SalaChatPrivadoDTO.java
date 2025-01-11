package org.main_java.chatprivado.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.main_java.chatprivado.domain.MensajePrivado;
import org.main_java.chatprivado.domain.Usuario;

import java.util.List;

@Getter
@Setter
@Data
public class SalaChatPrivadoDTO {

    private Long id;

    @Size(min = 1, max = 255)
    private String clave;

    @Size(max = 2, message = "Una sala de chat privado solo puede tener un máximo de 2 usuarios")
    private List<Usuario> usuarios;

    private List<MensajePrivado> mensajesPrivados;
}
