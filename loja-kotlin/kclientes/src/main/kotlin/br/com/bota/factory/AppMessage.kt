package br.com.bota.factory

import io.micronaut.context.MessageSource
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.context.i18n.ResourceBundleMessageSource


@Factory
open class AppMessage {
    @Bean
    open fun messageSource() : MessageSource = ResourceBundleMessageSource("i18.messages_pt_BR")
}