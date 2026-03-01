package com.vinicius.auth_api.controller;

import com.vinicius.auth_api.business.RedisService;
import com.vinicius.auth_api.business.UsuarioService;
import com.vinicius.auth_api.infrastructure.entity.Usuario;
import com.vinicius.auth_api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RedisService redisService;


    //  salvar user
    @PostMapping
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuario));
    }

    // logar para ganhar o token
    @PostMapping("/login")
    public ResponseEntity<String> salvarUser(@RequestBody Usuario user){

        // 1. Confere email e senha no banco
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(), user.getSenha()
                )
        );

        String token = jwtUtil.generateToken(authentication.getName());
        redisService.salvarToken(authentication.getName(), token);

        return ResponseEntity.ok("Bearer " + token);
    }



    @GetMapping
    public ResponseEntity<List<Usuario>> buscarUsuarioPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(usuarioService.listaUsers());
    }
}
