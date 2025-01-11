package org.main_java.chatprivado.model;

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

    private List<Usuario> usuarios;

    private List<MensajePrivado> mensajesPrivados;
}
