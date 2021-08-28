package br.com.bota.lojalib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
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

    public String getEndereco() {
        return localizacao != null ? localizacao.getLatitude()+"-"+localizacao.getLongitude() : "S/E";
    }
}
