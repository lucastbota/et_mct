package br.com.bota.exceptions.rule

import java.lang.RuntimeException

class CustomerNotFoundException(override val message: String = "Nenhum Recurso encontrado.") : RuntimeException() {
}