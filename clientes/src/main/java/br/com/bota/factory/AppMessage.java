package br.com.bota.factory;

import io.micronaut.context.MessageSource;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.i18n.ResourceBundleMessageSource;

@Factory
public class AppMessage {
    @Bean
    public MessageSource messageSource() {
        return new ResourceBundleMessageSource("i18.messages_pt_BR");
    }
}
