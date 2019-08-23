package no.nav.inntektsmelding.kontrakt.serde

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule


class JacksonJsonConfig {


    companion object objectMapperFactory {
        fun opprettObjectMapper(): ObjectMapper {
            val objectMapper = ObjectMapper()
            objectMapper.registerModule(Jdk8Module())
            objectMapper.registerModule(JavaTimeModule())
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper
        }
    }

}


