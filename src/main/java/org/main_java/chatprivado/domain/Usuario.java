package org.main_java.chatprivado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "rol")
    private String rol;

    @OneToMany
    @JoinColumn(name = "mensajes")
    private List<MensajePrivado> mensajes;

    @ManyToMany
    @JoinColumn(name = "salas")
    private List<SalaChatPrivado> salas;

    public Usuario() {
    }

    public Usuario(String nombre, String rol, List<MensajePrivado> mensajes, List<SalaChatPrivado> salas) {
        this.nombre = nombre;
        this.rol = rol;
        this.mensajes = mensajes;
        this.salas = salas;
    }
}
