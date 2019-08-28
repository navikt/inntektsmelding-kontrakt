package no.nav.inntektsmelding.kontrakt.serde

import com.fasterxml.jackson.databind.ObjectMapper
import no.nav.inntektsmeldingkontrakt.Arbeidsgivertype
import no.nav.inntektsmeldingkontrakt.Inntektsmelding
import no.nav.inntektsmeldingkontrakt.Refusjon
import no.nav.inntektsmeldingkontrakt.Status
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDate

internal class JacksonJsonConfigTest {

    val objectMapper: ObjectMapper = JacksonJsonConfig.opprettObjectMapper()

    @Test
    fun skal_deserialisere_dato() {
        val inntektsmelding = Inntektsmelding(
                inntektsmeldingId = "ENLANGIDENTIFIKATOR",
                foersteFravaersdag = LocalDate.of(2019, 1, 1),
                arbeidstakerFnr = "00000000000",
                arbeidstakerAktorId = "00000000000",
                refusjon = Refusjon(),
                endringIRefusjoner = emptyList(),
                opphoerAvNaturalytelser = emptyList(),
                gjenopptakelseNaturalytelser = emptyList(),
                status = Status.GYLDIG,
                arbeidsgivertype = Arbeidsgivertype.VIRKSOMHET
        )
        val serialisertInntektsmelding = objectMapper.writeValueAsString(inntektsmelding)
        assertTrue(serialisertInntektsmelding.contains("""
            foersteFravaersdag":"2019-01-01"
        """.trimIndent()))
    }

}