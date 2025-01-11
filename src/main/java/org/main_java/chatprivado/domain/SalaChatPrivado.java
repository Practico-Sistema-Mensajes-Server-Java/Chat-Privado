package org.main_java.chatprivado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SalaChatPrivado")
@Getter
@Setter
public class SalaChatPrivado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinColumn(name = "id_usuario")
    private List<Usuario> usuarios;

    @OneToMany(mappedBy = "salaChatPrivado")
    private List<MensajePrivado> mensajesPrivados;

    public SalaChatPrivado() {
    }

    public SalaChatPrivado(List<Usuario> usuarios, List<MensajePrivado> mensajesPrivados) {
        this.usuarios = usuarios;
        this.mensajesPrivados = mensajesPrivados;
    }
}
