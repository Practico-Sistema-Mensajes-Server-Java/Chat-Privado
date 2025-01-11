package org.main_java.chatprivado.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.main_java.chatprivado.domain.SalaChatPrivado;
import org.main_java.chatprivado.domain.Usuario;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class MensajePrivadoDTO {

    private Long id;

    @Size(min = 1, max = 255)
    @NotNull
    private String contendoCifrado;

    @NotNull
    private Usuario usuario;

    @NotNull
    private SalaChatPrivado salaChatPrivado;

    private LocalDateTime fecha;
}
