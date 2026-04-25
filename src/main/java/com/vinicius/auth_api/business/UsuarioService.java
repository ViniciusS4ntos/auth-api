package com.vinicius.auth_api.business;

import com.vinicius.auth_api.controller.DTO.UsuarioDTO;
import com.vinicius.auth_api.controller.converte.UsuarioConverte;
import com.vinicius.auth_api.infrastructure.entity.PasswordResetToken;
import com.vinicius.auth_api.infrastructure.entity.Usuario;
import com.vinicius.auth_api.infrastructure.exception.EmailExistenteException;
import com.vinicius.auth_api.infrastructure.repository.PasswordResetTokenRepository;
import com.vinicius.auth_api.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverte usuarioConverte;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;


    @Value("${app.url}")
    private String appUrl;

    // salvar
    public UsuarioDTO salvarUsuario(Usuario user){
        user.setSenha(passwordEncoder.encode(user.getSenha()));

        emailExistente(user.getEmail());

        return usuarioConverte.paraDTO(usuarioRepository.save(user));
    }

    // listar
    public List<UsuarioDTO> listaUsers(){
        return usuarioConverte.paraListaDTO(usuarioRepository.findAll());
    }




    public void forgotPassword(String email) {

        String token = UUID.randomUUID().toString();

        PasswordResetToken prt = new PasswordResetToken();
        prt.setToken(token);
        prt.setEmail(email);
        prt.setExpiracao(LocalDateTime.now().plusMinutes(30));

        passwordResetTokenRepository.save(prt);

        String link = appUrl + "/pages/reset-password.html?token=" + token;

        emailService.enviarEmail(
                email,
                "Redefinição de senha",
                "Olá! Recebemos uma solicitação para redefinir a senha da sua conta.\n\n" +
                        "Clique no link abaixo para criar uma nova senha:\n" +
                        link + "\n\n" +
                        "Este link expira em 10 minutos.\n\n" +
                        "Se você não solicitou a redefinição, ignore este email."
        );
    }














    // verificar email
    public Boolean verificarEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    // ver se email existe
    public void emailExistente(String email){
        try{
            boolean existe = verificarEmailExistente(email);
            if (existe){
                throw new EmailExistenteException("Email ja cadastrado");
            }
        } catch (EmailExistenteException e){
            throw new EmailExistenteException("Email ja cadastrado " + e);
        }
    }


    public void resetPassword(String token, String novaSenha) {

        validarToken(token);

        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalido!"));

        String email = prt.getEmail();

        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Email nao encontrado! "));

        user.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(user);
    
    }

    public void validarToken(String token) {

        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        prt.setExpiracao(LocalDateTime.now().plusMinutes(30));

        if (prt.getExpiracao().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }
    }
}
