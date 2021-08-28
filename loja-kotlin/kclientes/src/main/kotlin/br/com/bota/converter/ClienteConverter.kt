package br.com.bota.converter

import br.com.bota.entity.Cliente
import br.com.bota.lojalib.dto.ClienteDTO

open class ClienteConverter {
    companion object {
        fun toDTO(entity: Cliente): ClienteDTO {
            val dto = ClienteDTO()
            dto.id = entity.id
            dto.nome = entity.nome
            dto.criadoEm = entity.criadoEm
            dto.modificadoEm = entity.modificadoEm
            dto.localizacao = entity.localizacao?.let { LocalizacaoConverter.toDTO(it) }
            return dto
        }

        fun toEntity(dto: ClienteDTO): Cliente {
            val entity = Cliente(dto.id, dto.nome, null, dto.criadoEm, dto.modificadoEm)

            dto.localizacao?.let {
                entity.localizacao = LocalizacaoConverter.toEntity(dto.localizacao)
                entity.localizacao!!.cliente = entity
            }
            return entity
        }
    }
}