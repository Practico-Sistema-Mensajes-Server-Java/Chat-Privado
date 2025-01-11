package org.main_java.chatprivado.repos;

import org.main_java.chatprivado.domain.MensajePrivado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajePrivadoRepository extends JpaRepository<MensajePrivado, Long> {
}
