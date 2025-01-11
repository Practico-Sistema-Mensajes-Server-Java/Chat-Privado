package org.main_java.chatprivado.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Column(name = "clave")
    private String clave;

    @ManyToMany
    @JoinColumn(name = "id_usuario")
    @Size(max = 2, message = "Una sala de chat privado solo puede tener un máximo de 2 usuarios")
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
