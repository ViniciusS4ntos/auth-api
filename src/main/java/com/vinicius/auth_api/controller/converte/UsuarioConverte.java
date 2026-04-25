package com.vinicius.auth_api.controller.converte;

import com.vinicius.auth_api.controller.DTO.UsuarioDTO;
import com.vinicius.auth_api.infrastructure.entity.Usuario;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UsuarioConverte {

    public UsuarioDTO paraDTO(Usuario entity){
        return UsuarioDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .build();
    }

    public Usuario paraEntity(UsuarioDTO dto){
        return Usuario.builder()
                .id(dto.getId())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .build();
    }

    // Lista DTO
    public List<UsuarioDTO> paraListaDTO(List<Usuario> list){

        List<UsuarioDTO> listDTO = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            listDTO.add(paraDTO(list.get(i)));
        }

        return listDTO;
    }



}
