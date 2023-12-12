package no.nav.inntektsmeldingkontrakt

import java.time.LocalDate

data class InntektEndringAarsak(
    val aarsak : String,
    val perioder : List<Periode>? = null,
    val gjelderFra : LocalDate? = null,
    val bleKjent: LocalDate? = null
)
