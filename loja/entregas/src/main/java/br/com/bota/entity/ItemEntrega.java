package br.com.bota.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntrega implements Serializable {
    private String produto;
    private Integer quantidade;
    private String valor;
}
