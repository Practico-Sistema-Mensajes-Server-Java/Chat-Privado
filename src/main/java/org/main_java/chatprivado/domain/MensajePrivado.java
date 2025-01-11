package org.main_java.chatprivado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "MensajePrivado")
@Getter
@Setter
public class MensajePrivado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensaje")
    private String contendoCifrado;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_sala")
    private SalaChatPrivado salaChatPrivado;

    public MensajePrivado() {
    }

    public MensajePrivado(String contendoCifrado, LocalDateTime fecha, Usuario usuario, SalaChatPrivado salaChatPrivado) {
        this.contendoCifrado = contendoCifrado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.salaChatPrivado = salaChatPrivado;
    }
}
