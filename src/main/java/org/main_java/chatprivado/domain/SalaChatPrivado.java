package org.main_java.chatprivado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "SalaChatPrivado")
@Getter
@Setter
public class SalaChatPrivado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clave", nullable = false)
    private String clave;

    @ManyToMany
    @JoinTable(
            name = "usuario_sala",
            joinColumns = @JoinColumn(name = "sala_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "salaChatPrivado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MensajePrivado> mensajesPrivados;

    public SalaChatPrivado() {}

    public SalaChatPrivado(String clave, List<Usuario> usuarios, List<MensajePrivado> mensajesPrivados) {
        this.clave = clave;
        this.usuarios = usuarios;
        this.mensajesPrivados = mensajesPrivados;
    }
}
