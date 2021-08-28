package br.com.bota.lojalib.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalizacaoDTO implements Serializable {
    private UUID id;
    private BigDecimal latitude;
    private BigDecimal longitude;

}
