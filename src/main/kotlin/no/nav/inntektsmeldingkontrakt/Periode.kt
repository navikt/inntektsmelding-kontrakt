package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class Periode @JsonCreator constructor(
        @JsonProperty("fom")
        val fom: LocalDate,
        @JsonProperty("tom")
        val tom: LocalDate
)