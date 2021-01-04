package chap01

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BeanFactory {
    @Bean
    open fun happyNewYear():HappyNewYear = HappyNewYear()
}