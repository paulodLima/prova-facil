package com.provafacil.prova_facil.model.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "email")
class EmailProperties {
    lateinit var subject: Subject
    lateinit var body: Body

    class Subject {
        lateinit var accountCreated: String
    }

    class Body {
        lateinit var accountCreated: String
        lateinit var accountReset: String
    }
}
