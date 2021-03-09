package br.com.bota.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
