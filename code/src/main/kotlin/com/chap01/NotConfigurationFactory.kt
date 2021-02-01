package com.chap01

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class NotConfigurationFactory {

   // @Bean
    fun happyNewYear(): HappyNewYear = HappyNewYear()

    @Bean
    fun fun1(): Map<String, HappyNewYear> = mapOf("1" to happyNewYear())

    @Bean
    fun fun2(): Map<String, HappyNewYear> = mapOf("1" to happyNewYear())

}


@Service
class NotConfigurationInject(val notConfigurationFactory: NotConfigurationFactory){

    fun test1():HappyNewYear = notConfigurationFactory.happyNewYear()

    fun test2():HappyNewYear = notConfigurationFactory.happyNewYear()
}