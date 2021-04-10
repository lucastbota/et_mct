package br.com.bota.dto;

import br.com.bota.entity.Localizacao;
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
public class LocalizacaoDTO implements Serializable {
    private BigDecimal latitude;
    private BigDecimal longitude;


    public static LocalizacaoDTO toDTO(Localizacao entidade) {
        var dto = new LocalizacaoDTO();
        if (entidade != null) {
            dto.setLatitude(entidade.getLatitude());
            dto.setLongitude(entidade.getLongitude());
        }
        return dto;
    }

    public static Localizacao toEntity(LocalizacaoDTO dto) {
        Localizacao entity = null;
        if (dto != null) {
            entity = new Localizacao();
            entity.setLatitude(dto.getLatitude());
            entity.setLongitude(dto.getLongitude());
        }
        return entity;
    }
}
