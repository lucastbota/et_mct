package br.com.bota.lojalib.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public abstract class ItemDTO implements Serializable {
    private Integer quantidade;
    private BigDecimal valor;
}
