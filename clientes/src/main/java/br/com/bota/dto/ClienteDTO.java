package br.com.bota.dto;

import br.com.bota.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO implements Serializable {
    private UUID id;
    private String nome;
    private LocalDateTime criadoEm;
    private LocalDateTime modificadoEm;
    private LocalizacaoDTO localizacao;


    public static ClienteDTO toDTO(Cliente entity) {
        var dto = new ClienteDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setModificadoEm(entity.getModificadoEm());
        if (entity.getLocalizacao() != null) {
            dto.setLocalizacao(LocalizacaoDTO.toDTO(entity.getLocalizacao()));
        }
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        var entity = new Cliente();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setCriadoEm(dto.getCriadoEm());
        entity.setModificadoEm(dto.getModificadoEm());
        entity.setLocalizacao(LocalizacaoDTO.toEntity(dto.getLocalizacao()));
        if(entity.getLocalizacao() != null) {
            entity.getLocalizacao().setCliente(entity);
        }
        return entity;
    }
}
