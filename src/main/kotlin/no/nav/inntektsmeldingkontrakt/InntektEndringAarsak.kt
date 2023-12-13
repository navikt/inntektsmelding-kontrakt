package no.nav.inntektsmeldingkontrakt

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

data class InntektEndringAarsak(
    @JsonProperty("aarsak")
    val aarsak : String,
    @JsonProperty("perioder")
    val perioder : List<Periode>? = null,
    @JsonProperty("gjelderFra")
    val gjelderFra : LocalDate? = null,
    @JsonProperty("bleKjent")
    val bleKjent: LocalDate? = null
)
