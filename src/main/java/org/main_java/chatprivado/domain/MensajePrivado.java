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

    @Column(name = "contenido_cifrado", nullable = false)
    private String contenidoCifrado;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_sala", nullable = false)
    private SalaChatPrivado salaChatPrivado;

    public MensajePrivado() {}

    public MensajePrivado(String contenidoCifrado, LocalDateTime fecha, Usuario usuario, SalaChatPrivado salaChatPrivado) {
        this.contenidoCifrado = contenidoCifrado;
        this.fecha = fecha;
        this.usuario = usuario;
        this.salaChatPrivado = salaChatPrivado;
    }
}
