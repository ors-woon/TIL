package spring.chap01

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

class HappyNewYear

class HappyNewYearWrapper(val happyNewYear: HappyNewYear)

@Configuration
open class Config {
    @Bean
    open fun happyNewYear(): HappyNewYear = HappyNewYear()

    @Bean
    open fun injectHappy(happyNewYear: HappyNewYear): HappyNewYearWrapper {
        println(happyNewYear)
        return HappyNewYearWrapper(happyNewYear)
    }
}

@Component
class ConfigWithComponent {
    @Bean
    fun happyNewYear1(): HappyNewYear = HappyNewYear()

    @Bean
    fun injectHappy(): HappyNewYearWrapper {
        return HappyNewYearWrapper(happyNewYear1())
    }
}