package br.com.bota.lojalib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO implements Serializable {
    private UUID id;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidade;
    private LocalDateTime criadoEm;
    private LocalDateTime modificadoEm;
}
