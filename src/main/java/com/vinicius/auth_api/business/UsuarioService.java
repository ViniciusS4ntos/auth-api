package com.vinicius.auth_api.business;

import com.vinicius.auth_api.infrastructure.entity.Usuario;
import com.vinicius.auth_api.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    // salvar
    public Usuario salvarUsuario(Usuario user){
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        return usuarioRepository.save(user);
    }

    public List<Usuario> listaUsers(){
        return usuarioRepository.findAll();
    }



}
