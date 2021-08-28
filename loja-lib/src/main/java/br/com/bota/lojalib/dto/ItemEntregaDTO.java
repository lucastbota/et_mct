package br.com.bota.lojalib.dto;


import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntregaDTO extends ItemDTO {
    private String produto;
    private Integer quantidade;
    private BigDecimal valor;
}