package br.com.bota.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ProdutoDTO implements Serializable {
    private UUID id;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidade;
    private LocalDateTime criadoEm;
    private LocalDateTime modificadoEm;
}
