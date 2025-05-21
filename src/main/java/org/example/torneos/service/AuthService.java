package org.example.torneos.service;
import org.example.torneos.model.Usuario;

import org.example.torneos.repository.UsuarioRepository;
import org.example.torneos.util.SHA1Util;


public class AuthService {
    private UsuarioRepository usuarioRepository = new UsuarioRepository();

    public Usuario authenticate(String username, String password) {
        // Encriptar la contraseña ingresada para comparar con la almacenada
        String encryptedPassword = SHA1Util.encrypt(password);

        // Buscar usuario en la base de datos
        Usuario usuario = usuarioRepository.findByCredentials(username, encryptedPassword);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        return usuario;
    }
}
