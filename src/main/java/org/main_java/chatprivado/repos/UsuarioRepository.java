package org.main_java.chatprivado.repos;

import org.main_java.chatprivado.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
