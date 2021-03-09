package br.com.bota.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntregaDTO implements Serializable {
    private String produto;
    private Integer quantidade;
    private BigDecimal valor;
}
