package br.com.bota.lojalib.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstoqueDTO implements Serializable {
    private UUID produtoId;
    private Integer quantidade;
}
