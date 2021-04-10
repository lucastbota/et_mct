package br.com.bota.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
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
    private Optional<LocalizacaoDTO> localizacao;


    public String getEndereco() {
        return localizacao.filter(Objects::nonNull).map(l -> l.getLatitude()+"-"+l.getLongitude()).orElse("");
    }
}
