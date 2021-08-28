package br.com.bota.converter

import br.com.bota.entity.Localizacao
import br.com.bota.lojalib.dto.LocalizacaoDTO

class LocalizacaoConverter {
    companion object {
        fun toDTO(entity: Localizacao): LocalizacaoDTO =
            LocalizacaoDTO.builder().latitude(entity.latitude).longitude(entity.longitude).build()

        fun toEntity(dto: LocalizacaoDTO): Localizacao = Localizacao(latitude=dto.latitude, longitude= dto.longitude)
    }
}