package br.com.bota.dto;

import br.com.bota.entity.Produto;
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

    public static ProdutoDTO toDTO(Produto entity) {
        var dto = new ProdutoDTO();
        dto.setId(entity.getId());
        dto.setDescricao(entity.getDescricao());
        dto.setValor(entity.getValor());
        dto.setQuantidade(entity.getQuantidade());
        dto.setCriadoEm(entity.getCriadoEm());
        dto.setModificadoEm(entity.getModificadoEm());
        return dto;
    }

    public static Produto toEntity(ProdutoDTO dto) {
        var entity = new Produto();
        entity.setId(dto.getId());
        entity.setDescricao(dto.getDescricao());
        entity.setValor(dto.getValor());
        entity.setQuantidade(dto.getQuantidade());
        entity.setCriadoEm(dto.getCriadoEm());
        entity.setModificadoEm(dto.getModificadoEm());
        return entity;
    }
}
