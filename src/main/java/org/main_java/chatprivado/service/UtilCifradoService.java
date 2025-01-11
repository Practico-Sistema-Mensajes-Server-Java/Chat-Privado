package org.main_java.chatprivado.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class UtilCifradoService {

    private static final String ALGORITMO = "AES";

    /**
     * Cifra un mensaje utilizando AES.
     *
     * @param mensaje texto a cifrar.
     * @param clave   clave AES en formato String.
     * @return mensaje cifrado en Base64.
     * @throws Exception si ocurre un error durante el cifrado.
     */
    public String cifrarMensaje(String mensaje, String clave) throws Exception {
        // Convertimos la clave de Base64 a bytes y creamos una clave secreta
        SecretKeySpec claveSecreta = new SecretKeySpec(Base64.getDecoder().decode(clave), ALGORITMO);

        // Inicializamos el cifrador en modo ENCRYPT_MODE
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);

        // Ciframos el mensaje y lo devolvemos en formato Base64
        byte[] mensajeCifrado = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(mensajeCifrado);
    }

    /**
     * Descifra un mensaje cifrado utilizando AES.
     *
     * @param mensajeCifrado mensaje cifrado en formato Base64.
     * @param clave          clave AES en formato String.
     * @return mensaje descifrado.
     * @throws Exception si ocurre un error durante el descifrado.
     */
    public String descifrarMensaje(String mensajeCifrado, String clave) throws Exception {
        // Convertimos la clave de Base64 a bytes y creamos una clave secreta
        SecretKeySpec claveSecreta = new SecretKeySpec(Base64.getDecoder().decode(clave), ALGORITMO);

        // Inicializamos el cifrador en modo DECRYPT_MODE
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, claveSecreta);

        // Desciframos el mensaje y lo devolvemos como texto
        byte[] mensajeDescifrado = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
        return new String(mensajeDescifrado);
    }
}

