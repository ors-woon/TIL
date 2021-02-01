package com.chap01

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

class HappyNewYear

@Configuration
open class BeanFactory {
    @Bean
    open fun happyNewYear(): HappyNewYear = HappyNewYear()
}